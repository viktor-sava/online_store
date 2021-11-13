package com.example.online_store;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    private final Properties properties;

    private static ApplicationProperties instance;

    private static final String PATH = "/application.properties";

    private ApplicationProperties() {
        this.properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(PATH));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot init application properties", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static synchronized ApplicationProperties getInstance() {
        if (instance == null) {
            instance = new ApplicationProperties();
        }
        return instance;
    }
}
