/*
 * Copyright (c) 2018 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.plugins;

import javax.annotation.Nullable;

import org.mockito.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;

public interface InstantiatorProvider2 {

    /**
     * Returns an instantiator, used to create new class instances.
     *
     * @since 2.15.4
     */
    Instantiator getInstantiator(@Nullable MockCreationSettings<?> settings);
}
