/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.verification.api;

import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

import javax.annotation.Nullable;
import java.util.List;

public class VerificationDataInOrderImpl implements VerificationDataInOrder {

    private final InOrderContext inOrder;
    private final List<Invocation> allInvocations;

    @Nullable private final MatchableInvocation wanted;

    public VerificationDataInOrderImpl(
            InOrderContext inOrder,
            List<Invocation> allInvocations,
            @Nullable MatchableInvocation wanted) {
        this.inOrder = inOrder;
        this.allInvocations = allInvocations;
        this.wanted = wanted;
    }

    public List<Invocation> getAllInvocations() {
        return allInvocations;
    }

    public InOrderContext getOrderingContext() {
        return inOrder;
    }

    @Nullable
    public MatchableInvocation getWanted() {
        return wanted;
    }
}
