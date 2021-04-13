/*
 * Copyright (c) 2020 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.util.reflection;

import javax.annotation.Nullable;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodCall;
import org.mockito.exceptions.base.MockitoInitializationException;
import org.mockito.plugins.MemberAccessor;

import java.lang.instrument.Instrumentation;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.*;
import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.mockito.internal.util.StringUtil.join;

class InstrumentationMemberAccessor implements MemberAccessor {

    private static final Map<Class<?>, Class<?>> WRAPPERS = new HashMap<>();

    private static final Instrumentation INSTRUMENTATION;
    private static final Dispatcher DISPATCHER;

    private static final Throwable INITIALIZATION_ERROR;

    static {
        WRAPPERS.put(boolean.class, Boolean.class);
        WRAPPERS.put(byte.class, Byte.class);
        WRAPPERS.put(short.class, Short.class);
        WRAPPERS.put(char.class, Character.class);
        WRAPPERS.put(int.class, Integer.class);
        WRAPPERS.put(long.class, Long.class);
        WRAPPERS.put(float.class, Float.class);
        WRAPPERS.put(double.class, Double.class);
        Instrumentation instrumentation;
        Dispatcher dispatcher;
        Throwable throwable;
        try {
            instrumentation = ByteBuddyAgent.install();
            // We need to generate a dispatcher instance that is located in a distinguished class
            // loader to create a unique (unnamed) module to which we can open other packages to.
            // This way, we assure that classes within Mockito's module (which might be a shared,
            // unnamed module) do not face escalated privileges where tests might pass that would
            // otherwise fail without Mockito's opening.
            dispatcher =
                    new ByteBuddy()
                            .subclass(Dispatcher.class)
                            .method(named("getLookup"))
                            .intercept(MethodCall.invoke(MethodHandles.class.getMethod("lookup")))
                            .method(named("getModule"))
                            .intercept(
                                    MethodCall.invoke(Class.class.getMethod("getModule"))
                                            .onMethodCall(
                                                    MethodCall.invoke(
                                                            Object.class.getMethod("getClass"))))
                            .method(named("setAccessible"))
                            .intercept(
                                    MethodCall.invoke(
                                                    AccessibleObject.class.getMethod(
                                                            "setAccessible", boolean.class))
                                            .onArgument(0)
                                            .withArgument(1))
                            .make()
                            .load(
                                    InstrumentationMemberAccessor.class.getClassLoader(),
                                    ClassLoadingStrategy.Default.WRAPPER)
                            .getLoaded()
                            .getConstructor()
                            .newInstance();
            throwable = null;
        } catch (Throwable t) {
            instrumentation = null;
            dispatcher = null;
            throwable = t;
        }
        INSTRUMENTATION = instrumentation;
        DISPATCHER = dispatcher;
        INITIALIZATION_ERROR = throwable;
    }

    private final MethodHandle getModule, isOpen, redefineModule, privateLookupIn;

    InstrumentationMemberAccessor() {
        if (INITIALIZATION_ERROR != null) {
            throw new MockitoInitializationException(
                    join(
                            "Could not initialize the Mockito instrumentation member accessor",
                            "",
                            "This is unexpected on JVMs from Java 9 or later - possibly, the instrumentation API could not be resolved"),
                    INITIALIZATION_ERROR);
        }
        try {
            Class<?> module = Class.forName("java.lang.Module");
            getModule =
                    MethodHandles.publicLookup()
                            .findVirtual(Class.class, "getModule", MethodType.methodType(module));
            isOpen =
                    MethodHandles.publicLookup()
                            .findVirtual(
                                    module,
                                    "isOpen",
                                    MethodType.methodType(boolean.class, String.class, module));
            redefineModule =
                    MethodHandles.publicLookup()
                            .findVirtual(
                                    Instrumentation.class,
                                    "redefineModule",
                                    MethodType.methodType(
                                            void.class,
                                            module,
                                            Set.class,
                                            Map.class,
                                            Map.class,
                                            Set.class,
                                            Map.class));
            privateLookupIn =
                    MethodHandles.publicLookup()
                            .findStatic(
                                    MethodHandles.class,
                                    "privateLookupIn",
                                    MethodType.methodType(
                                            MethodHandles.Lookup.class,
                                            Class.class,
                                            MethodHandles.Lookup.class));
        } catch (Throwable t) {
            throw new MockitoInitializationException(
                    "Could not resolve instrumentation invoker", t);
        }
    }

    @Override
    public Object newInstance(Constructor<?> constructor, Object... arguments)
            throws InstantiationException, InvocationTargetException {
        if (Modifier.isAbstract(constructor.getDeclaringClass().getModifiers())) {
            throw new InstantiationException(
                    "Cannot instantiate abstract " + constructor.getDeclaringClass().getTypeName());
        }
        assureArguments(constructor, null, null, arguments, constructor.getParameterTypes());
        try {
            Object module = getModule.bindTo(constructor.getDeclaringClass()).invokeWithArguments();
            String packageName = constructor.getDeclaringClass().getPackage().getName();
            assureOpen(module, packageName);
            MethodHandle handle =
                    ((MethodHandles.Lookup)
                                    privateLookupIn.invokeExact(
                                            constructor.getDeclaringClass(),
                                            DISPATCHER.getLookup()))
                            .unreflectConstructor(constructor);
            try {
                return handle.invokeWithArguments(arguments);
            } catch (Throwable t) {
                throw new InvocationTargetException(t);
            }
        } catch (InvocationTargetException e) {
            throw e;
        } catch (Throwable t) {
            throw new IllegalStateException(
                    "Could not construct "
                            + constructor
                            + " with arguments "
                            + Arrays.toString(arguments),
                    t);
        }
    }

    @Override
    public Object invoke(Method method, Object target, Object... arguments)
            throws InvocationTargetException {
        assureArguments(
                method,
                Modifier.isStatic(method.getModifiers()) ? null : target,
                method.getDeclaringClass(),
                arguments,
                method.getParameterTypes());
        try {
            Object module = getModule.bindTo(method.getDeclaringClass()).invokeWithArguments();
            String packageName = method.getDeclaringClass().getPackage().getName();
            assureOpen(module, packageName);
            MethodHandle handle =
                    ((MethodHandles.Lookup)
                                    privateLookupIn.invokeExact(
                                            method.getDeclaringClass(), DISPATCHER.getLookup()))
                            .unreflect(method);
            if (!Modifier.isStatic(method.getModifiers())) {
                handle = handle.bindTo(target);
            }
            try {
                return handle.invokeWithArguments(arguments);
            } catch (Throwable t) {
                throw new InvocationTargetException(t);
            }
        } catch (InvocationTargetException e) {
            throw e;
        } catch (Throwable t) {
            throw new IllegalStateException(
                    "Could not invoke "
                            + method
                            + " on "
                            + target
                            + " with arguments "
                            + Arrays.toString(arguments),
                    t);
        }
    }

    @Override
    public Object get(Field field, Object target) {
        assureArguments(
                field,
                Modifier.isStatic(field.getModifiers()) ? null : target,
                field.getDeclaringClass(),
                new Object[0],
                new Class<?>[0]);
        try {
            Object module = getModule.bindTo(field.getDeclaringClass()).invokeWithArguments();
            String packageName = field.getDeclaringClass().getPackage().getName();
            assureOpen(module, packageName);
            MethodHandle handle =
                    ((MethodHandles.Lookup)
                                    privateLookupIn.invokeExact(
                                            field.getDeclaringClass(), DISPATCHER.getLookup()))
                            .unreflectGetter(field);
            if (!Modifier.isStatic(field.getModifiers())) {
                handle = handle.bindTo(target);
            }
            return handle.invokeWithArguments();
        } catch (Throwable t) {
            throw new IllegalStateException("Could not read " + field + " on " + target, t);
        }
    }

    @Override
    public void set(Field field, Object target, Object value) throws IllegalAccessException {
        assureArguments(
                field,
                Modifier.isStatic(field.getModifiers()) ? null : target,
                field.getDeclaringClass(),
                new Object[] {value},
                new Class<?>[] {field.getType()});
        boolean illegalAccess = false;
        try {
            Object module = getModule.bindTo(field.getDeclaringClass()).invokeWithArguments();
            String packageName = field.getDeclaringClass().getPackage().getName();
            assureOpen(module, packageName);
            // Method handles do not allow setting final fields where setAccessible(true)
            // is required before unreflecting.
            boolean isFinal;
            if (Modifier.isFinal(field.getModifiers())) {
                isFinal = true;
                try {
                    DISPATCHER.setAccessible(field, true);
                } catch (Throwable ignored) {
                    illegalAccess =
                            true; // To distinguish from propagated illegal access exception.
                    throw new IllegalAccessException(
                            "Could not make final field " + field + " accessible");
                }
            } else {
                isFinal = false;
            }
            try {
                MethodHandle handle =
                        ((MethodHandles.Lookup)
                                        privateLookupIn.invokeExact(
                                                field.getDeclaringClass(), DISPATCHER.getLookup()))
                                .unreflectSetter(field);
                if (!Modifier.isStatic(field.getModifiers())) {
                    handle = handle.bindTo(target);
                }
                handle.invokeWithArguments(value);
            } finally {
                if (isFinal) {
                    DISPATCHER.setAccessible(field, false);
                }
            }
        } catch (Throwable t) {
            if (illegalAccess) {
                throw (IllegalAccessException) t;
            } else {
                throw new IllegalStateException("Could not read " + field + " on " + target, t);
            }
        }
    }

    private void assureOpen(Object module, String packageName) throws Throwable {
        if (!(Boolean) isOpen.invokeWithArguments(module, packageName, DISPATCHER.getModule())) {
            redefineModule
                    .bindTo(INSTRUMENTATION)
                    .invokeWithArguments(
                            module,
                            Collections.emptySet(),
                            Collections.emptyMap(),
                            Collections.singletonMap(
                                    packageName, Collections.singleton(DISPATCHER.getModule())),
                            Collections.emptySet(),
                            Collections.emptyMap());
        }
    }

    private static void assureArguments(
            AccessibleObject target,
            @Nullable Object owner,
            @Nullable Class<?> type,
            Object[] values,
            Class<?>[] types) {
        if (owner != null) {
            if (!type.isAssignableFrom(owner.getClass())) {
                throw new IllegalArgumentException("Cannot access " + target + " on " + owner);
            }
        }
        if (types.length != values.length) {
            throw new IllegalArgumentException(
                    "Incorrect number of arguments for "
                            + target
                            + ": expected "
                            + types.length
                            + " but recevied "
                            + values.length);
        }
        for (int index = 0; index < values.length; index++) {
            if (values[index] == null) {
                if (types[index].isPrimitive()) {
                    throw new IllegalArgumentException(
                            "Cannot assign null to primitive type "
                                    + types[index].getTypeName()
                                    + " for "
                                    + index
                                    + " parameter of "
                                    + target);
                }
            } else {
                Class<?> resolved = WRAPPERS.getOrDefault(types[index], types[index]);
                if (!resolved.isAssignableFrom(values[index].getClass())) {
                    throw new IllegalArgumentException(
                            "Cannot assign value of type "
                                    + values[index].getClass()
                                    + " to "
                                    + resolved
                                    + " for "
                                    + index
                                    + " parameter of "
                                    + target);
                }
            }
        }
    }

    public interface Dispatcher {

        MethodHandles.Lookup getLookup();

        Object getModule();

        void setAccessible(AccessibleObject target, boolean value);
    }
}
