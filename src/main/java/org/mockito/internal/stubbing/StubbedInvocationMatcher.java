/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.stubbing;

import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubbing;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@SuppressWarnings("unchecked")
public class StubbedInvocationMatcher extends InvocationMatcher implements Serializable, Stubbing {

    private static final long serialVersionUID = 4919105134123672727L;
    private final Queue<Answer> answers = new ConcurrentLinkedQueue<Answer>();

    @Nullable private final Strictness strictness;

    @Nullable private DescribedInvocation usedAt;

    public StubbedInvocationMatcher(
            Answer answer, MatchableInvocation invocation, @Nullable Strictness strictness) {
        super(invocation.getInvocation(), invocation.getMatchers());
        this.strictness = strictness;
        this.answers.add(answer);
    }

    public Object answer(InvocationOnMock invocation) throws Throwable {
        // see ThreadsShareGenerouslyStubbedMockTest
        Answer a;
        synchronized (answers) {
            a = answers.size() == 1 ? answers.peek() : answers.poll();
        }
        return a.answer(invocation);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void markStubUsed(DescribedInvocation usedAt) {
        this.usedAt = usedAt;
    }

    public boolean wasUsed() {
        return usedAt != null;
    }

    @Override
    public String toString() {
        return super.toString() + " stubbed with: " + answers;
    }

    @Override
    @Nullable
    public Strictness getStrictness() {
        return strictness;
    }
}
