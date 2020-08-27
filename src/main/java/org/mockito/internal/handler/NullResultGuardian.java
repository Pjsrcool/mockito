/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.handler;

import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationContainer;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;

import javax.annotation.Nullable;

import static org.mockito.internal.util.Primitives.defaultValue;

class NullResultGuardian<T> implements MockHandler<T> {

    private final MockHandler<T> delegate;

    public NullResultGuardian(MockHandler<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    @Nullable
    public Object handle(Invocation invocation) throws Throwable {
        Object result = delegate.handle(invocation);
        Class<?> returnType = invocation.getMethod().getReturnType();
        if (result == null && returnType.isPrimitive()) {
            // primitive values cannot be null
            return defaultValue(returnType);
        }
        return result;
    }

    @Override
    public MockCreationSettings<T> getMockSettings() {
        return delegate.getMockSettings();
    }

    @Override
    public InvocationContainer getInvocationContainer() {
        return delegate.getInvocationContainer();
    }
}
