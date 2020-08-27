/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.stubbing.defaultanswers;

import org.mockito.Mockito;
import org.mockito.internal.creation.MockSettingsImpl;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.annotation.Nullable;
import java.io.Serializable;

public class ReturnsMocks implements Answer<Object>, Serializable {

    private static final long serialVersionUID = -6755257986994634579L;
    private final Answer<Object> delegate = new ReturnsMoreEmptyValues();

    @Override
    @Nullable
    @SuppressWarnings("NullAway") // todo: NullAway to avoid superclass errors
    public Object answer(final InvocationOnMock invocation) throws Throwable {
        Object defaultReturnValue = delegate.answer(invocation);

        if (defaultReturnValue != null) {
            return defaultReturnValue;
        }

        return RetrieveGenericsForDefaultAnswers.returnTypeForMockWithCorrectGenerics(
                invocation,
                new RetrieveGenericsForDefaultAnswers.AnswerCallback() {
                    @Nullable
                    @Override
                    @SuppressWarnings(
                            "NullAway") // todo: NullAway - added to avoid superclass errors
                    public Object apply(Class<?> type) {
                        if (type == null) {
                            return null;
                        }

                        return Mockito.mock(
                                type,
                                new MockSettingsImpl<Object>().defaultAnswer(ReturnsMocks.this));
                    }
                });
    }
}
