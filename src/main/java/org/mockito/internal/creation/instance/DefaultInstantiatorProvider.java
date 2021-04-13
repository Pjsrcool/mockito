/*
 * Copyright (c) 2016 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.creation.instance;

import javax.annotation.Nullable;

import org.mockito.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InstantiatorProvider2;

public class DefaultInstantiatorProvider implements InstantiatorProvider2 {

    private static final Instantiator INSTANCE = new ObjenesisInstantiator();

    public Instantiator getInstantiator(@Nullable MockCreationSettings<?> settings) {
        if (settings != null && settings.getConstructorArgs() != null) {
            return new ConstructorInstantiator(
                    settings.getOuterClassInstance() != null, settings.getConstructorArgs());
        } else {
            return INSTANCE;
        }
    }
}
