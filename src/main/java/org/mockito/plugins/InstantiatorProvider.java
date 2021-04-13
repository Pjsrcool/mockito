/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.plugins;

import javax.annotation.Nullable;

import org.mockito.internal.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;

@Deprecated
public interface InstantiatorProvider {

    /**
     * @deprecated, see {@link InstantiatorProvider}.
     *
     * Returns an instantiator, used to create new class instances.
     */
    @Deprecated
    Instantiator getInstantiator(@Nullable MockCreationSettings<?> settings);
}
