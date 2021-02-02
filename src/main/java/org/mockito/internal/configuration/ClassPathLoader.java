/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.configuration;

import javax.annotation.Nullable;

import org.mockito.configuration.IMockitoConfiguration;
import org.mockito.exceptions.misusing.MockitoConfigurationException;
import org.mockito.plugins.MockMaker;

public class ClassPathLoader {

    public static final String MOCKITO_CONFIGURATION_CLASS_NAME =
            "org.mockito.configuration.MockitoConfiguration";

    /**
     * @return configuration loaded from classpath or null
     */
    @SuppressWarnings({"unchecked"})@Nullable
    public IMockitoConfiguration loadConfiguration() {
        // Trying to get config from classpath
        Class<?> configClass;
        try {
            configClass = Class.forName(MOCKITO_CONFIGURATION_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            // that's ok, it means there is no global config, using default one.
            return null;
        }

        try {
            return (IMockitoConfiguration) configClass.getDeclaredConstructor().newInstance();
        } catch (ClassCastException e) {
            throw new MockitoConfigurationException(
                    "MockitoConfiguration class must implement "
                            + IMockitoConfiguration.class.getName()
                            + " interface.",
                    e);
        } catch (Exception e) {
            throw new MockitoConfigurationException(
                    "Unable to instantiate "
                            + MOCKITO_CONFIGURATION_CLASS_NAME
                            + " class. Does it have a safe, no-arg constructor?",
                    e);
        }
    }
}
