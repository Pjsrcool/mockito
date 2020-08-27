/*
 * Copyright (c) 2018 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.junit;

import org.mockito.NullAwayUtil;

import javax.annotation.Nullable;

public class DefaultTestFinishedEvent implements TestFinishedEvent {
    @Nullable private final Object testClassInstance;
    private final String testMethodName;
    @Nullable private final Throwable testFailure;

    public DefaultTestFinishedEvent(
            @Nullable Object testClassInstance, String testMethodName, @Nullable Throwable testFailure) {
        this.testClassInstance = testClassInstance;
        this.testMethodName = testMethodName;
        this.testFailure = testFailure;
    }

    @Override
    @Nullable
    public Throwable getFailure() {
        return testFailure;
    }

    @Override
    public String getTestName() {
        //todo: NullAway: real bug
        Object nonnullTestClassInstance = NullAwayUtil.castToNonNull(testClassInstance);
        return nonnullTestClassInstance.getClass().getSimpleName() + "." + testMethodName;
    }
}
