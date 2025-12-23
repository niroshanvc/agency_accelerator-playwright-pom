package com.carehires.config;

import com.carehires.enums.Environment;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentConfig {
    
    private static EnvironmentConfig instance;
    private final Properties properties;
    private final Environment environment;

    private EnvironmentConfig() {
        String env = System.getProperty("env", "dev").toLowerCase();
        this.environment = Environment.valueOf(env.toUpperCase());
        this.properties = loadProperties();
    }

    public static EnvironmentConfig getInstance() {
        if (instance == null) {
            synchronized (EnvironmentConfig.class) {
                if (instance == null) {
                    instance = new EnvironmentConfig();
                }
            }
        }
        return instance;
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        String fileName = String.format("config/%s.properties", environment.name().toLowerCase());
        
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + fileName);
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load environment properties: " + fileName, e);
        }
        return props;
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in " + environment + " environment");
        }
        return value;
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getApiBaseUrl() {
        return getProperty("api.base.url");
    }

    public Environment getEnvironment() {
        return environment;
    }

    public int getTimeout() {
        return Integer.parseInt(getProperty("timeout", "30000"));
    }

    public String getDbUrl() {
        return getProperty("db.url");
    }

    public String getDbUsername() {
        return getProperty("db.username");
    }

    public String getDbPassword() {
        return getProperty("db.password");
    }
}
