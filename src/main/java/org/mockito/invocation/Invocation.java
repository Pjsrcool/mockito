/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.invocation;

import javax.annotation.Nullable;

import java.util.List;

import org.mockito.ArgumentMatcher;
import org.mockito.NotExtensible;

@NotExtensible
public interface Invocation extends InvocationOnMock, DescribedInvocation {

    /**
     * @return whether the invocation has been already verified.
     * Needed for {@link org.mockito.Mockito#verifyNoMoreInteractions(Object...)}
     */
    boolean isVerified();

    /**
     * @return the sequence number of the Invocation. Useful to determine the order of invocations.
     * Used by verification in order.
     */
    int getSequenceNumber();

    /**
     * @return the location in code of this invocation.
     */
    Location getLocation();

    /**
     * Returns unprocessed arguments whereas {@link #getArguments()} returns
     * arguments already processed (e.g. varargs expended, etc.).
     *
     * @return unprocessed arguments, exactly as provided to this invocation.
     */
    Object[] getRawArguments();

    /**
     * Wraps each argument using {@link org.mockito.ArgumentMatchers#eq(Object)} or
     * {@link org.mockito.AdditionalMatchers#aryEq(Object[])}
     * Used internally for the purposes of human-readable invocation printing.
     *
     * @return a list of {@link ArgumentMatcher} wrapping each of this invocation arguments
     * @since 2.25.6
     */
    List<ArgumentMatcher> getArgumentsAsMatchers();

    /**
     * Returns unprocessed arguments whereas {@link #getArguments()} returns
     * arguments already processed (e.g. varargs expended, etc.).
     *
     * @return unprocessed arguments, exactly as provided to this invocation.
     */
    Class<?> getRawReturnType();

    /**
     * Marks this invocation as verified so that it will not cause verification error at
     * {@link org.mockito.Mockito#verifyNoMoreInteractions(Object...)}
     */
    void markVerified();

    /**
     * @return the stubbing information for this invocation. May return null - this means
     * the invocation was not stubbed.
     */
    @Nullable
    StubInfo stubInfo();

    /**
     * Marks this invocation as stubbed.
     *
     * @param stubInfo the information about stubbing.
     */
    void markStubbed(StubInfo stubInfo);

    /**
     * Informs if the invocation participates in verify-no-more-invocations or verification in order.
     *
     * @return whether this invocation should be ignored for the purposes of
     * verify-no-more-invocations or verification in order.
     */
    boolean isIgnoredForVerification();

    /**
     * Configures this invocation to be ignored for verify-no-more-invocations or verification in order.
     * See also {@link #isIgnoredForVerification()}
     */
    void ignoreForVerification();
}
