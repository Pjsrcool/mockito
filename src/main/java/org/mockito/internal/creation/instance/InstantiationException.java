/*
 * Copyright (c) 2016 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.creation.instance;

import javax.annotation.Nullable;

import org.mockito.exceptions.base.MockitoException;

@Deprecated
public class InstantiationException extends MockitoException {

    public InstantiationException(@Nullable String message, Throwable cause) {
        super(message, cause);
    }
}
