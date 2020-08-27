/*
 * Copyright (c) 2020 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito;

import javax.annotation.Nullable;

public class NullAwayUtil {
    public static <T> T castToNonNull(@Nullable T x) {
        if (x == null) {
            throw new RuntimeException("Did not expect null here");
        } else return x;
    }
}
