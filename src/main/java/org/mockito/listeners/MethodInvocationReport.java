/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.listeners;

import javax.annotation.Nullable;

import org.mockito.invocation.DescribedInvocation;

public interface MethodInvocationReport {
    /**
     * @return Information on the method call, never {@code null}
     */
    DescribedInvocation getInvocation();

    /**
     * @return The resulting value of the method invocation, may be <code>null</code>
     */
    Object getReturnedValue();

    /**
     * @return The throwable raised by the method invocation, maybe <code>null</code>
     */
    Throwable getThrowable();

    /**
     * @return <code>true</code> if an exception was raised, <code>false</code> otherwise
     */
    boolean threwException();

    /**
     * @return Location of the stub invocation
     */
    @Nullable
    String getLocationOfStubbing();
}
