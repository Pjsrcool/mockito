/*
 * Copyright (c) 2016 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.hamcrest;

import org.hamcrest.Matcher;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;

import javax.annotation.Nullable;

import static org.mockito.internal.hamcrest.MatcherGenericTypeExtractor.genericTypeOfMatcher;
import static org.mockito.internal.progress.ThreadSafeMockingProgress.mockingProgress;
import static org.mockito.internal.util.Primitives.defaultValue;

public class MockitoHamcrest {

    /**
     * Allows matching arguments with hamcrest matchers.
     * <p/>
     * See examples in javadoc for {@link MockitoHamcrest} class
     *
     * @param matcher decides whether argument matches
     * @return <code>null</code> or default value for primitive (0, false, etc.)
     * @since 2.1.0
     */
    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T argThat(Matcher<T> matcher) {
        reportMatcher(matcher);
        return (T) defaultValue(genericTypeOfMatcher(matcher.getClass()));
    }

    /**
     * Enables integrating hamcrest matchers that match primitive <code>char</code> arguments.
     * Note that {@link #argThat} will not work with primitive <code>char</code> matchers due to <code>NullPointerException</code> auto-unboxing caveat.
     * <p/>
     * See examples in javadoc for {@link MockitoHamcrest} class
     *
     * @param matcher decides whether argument matches
     * @return <code>0</code>.
     */
    public static char charThat(Matcher<Character> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    /**
     * Enables integrating hamcrest matchers that match primitive <code>boolean</code> arguments.
     * Note that {@link #argThat} will not work with primitive <code>boolean</code> matchers due to <code>NullPointerException</code> auto-unboxing caveat.
     * <p/>
     * See examples in javadoc for {@link MockitoHamcrest} class
     *
     * @param matcher decides whether argument matches
     * @return <code>false</code>.
     */
    public static boolean booleanThat(Matcher<Boolean> matcher) {
        reportMatcher(matcher);
        return false;
    }

    /**
     * Enables integrating hamcrest matchers that match primitive <code>byte</code> arguments.
     * Note that {@link #argThat} will not work with primitive <code>byte</code> matchers due to <code>NullPointerException</code> auto-unboxing caveat.
     * <p/>
     * * See examples in javadoc for {@link MockitoHamcrest} class
     *
     * @param matcher decides whether argument matches
     * @return <code>0</code>.
     */
    public static byte byteThat(Matcher<Byte> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    /**
     * Enables integrating hamcrest matchers that match primitive <code>short</code> arguments.
     * Note that {@link #argThat} will not work with primitive <code>short</code> matchers due to <code>NullPointerException</code> auto-unboxing caveat.
     * <p/>
     * * See examples in javadoc for {@link MockitoHamcrest} class
     *
     * @param matcher decides whether argument matches
     * @return <code>0</code>.
     */
    public static short shortThat(Matcher<Short> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    /**
     * Enables integrating hamcrest matchers that match primitive <code>int</code> arguments.
     * Note that {@link #argThat} will not work with primitive <code>int</code> matchers due to <code>NullPointerException</code> auto-unboxing caveat.
     * <p/>
     * * See examples in javadoc for {@link MockitoHamcrest} class
     *
     * @param matcher decides whether argument matches
     * @return <code>0</code>.
     */
    public static int intThat(Matcher<Integer> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    /**
     * Enables integrating hamcrest matchers that match primitive <code>long</code> arguments.
     * Note that {@link #argThat} will not work with primitive <code>long</code> matchers due to <code>NullPointerException</code> auto-unboxing caveat.
     * <p/>
     * * See examples in javadoc for {@link MockitoHamcrest} class
     *
     * @param matcher decides whether argument matches
     * @return <code>0</code>.
     */
    public static long longThat(Matcher<Long> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    /**
     * Enables integrating hamcrest matchers that match primitive <code>float</code> arguments.
     * Note that {@link #argThat} will not work with primitive <code>float</code> matchers due to <code>NullPointerException</code> auto-unboxing caveat.
     * <p/>
     * * See examples in javadoc for {@link MockitoHamcrest} class
     *
     * @param matcher decides whether argument matches
     * @return <code>0</code>.
     */
    public static float floatThat(Matcher<Float> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    /**
     * Enables integrating hamcrest matchers that match primitive <code>double</code> arguments.
     * Note that {@link #argThat} will not work with primitive <code>double</code> matchers due to <code>NullPointerException</code> auto-unboxing caveat.
     * <p/>
     * * See examples in javadoc for {@link MockitoHamcrest} class
     *
     * @param matcher decides whether argument matches
     * @return <code>0</code>.
     */
    public static double doubleThat(Matcher<Double> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    private static <T> void reportMatcher(Matcher<T> matcher) {
        mockingProgress()
                .getArgumentMatcherStorage()
                .reportMatcher(new HamcrestArgumentMatcher<T>(matcher));
    }
}
