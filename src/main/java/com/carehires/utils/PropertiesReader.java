package com.carehires.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    
    private static final Logger logger = LogManager.getLogger(PropertiesReader.class);

    public static Properties loadProperties(String filePath) {
        logger.info("Loading properties file: {}", filePath);
        Properties properties = new Properties();
        
        try (InputStream input = PropertiesReader.class.getClassLoader()
                .getResourceAsStream(filePath)) {
            if (input == null) {
                throw new RuntimeException("Properties file not found: " + filePath);
            }
            properties.load(input);
            logger.info("Successfully loaded properties file: {}", filePath);
            return properties;
        } catch (IOException e) {
            logger.error("Failed to load properties file: {}", filePath, e);
            throw new RuntimeException("Error loading properties file: " + filePath, e);
        }
    }

    public static String getProperty(String filePath, String key) {
        Properties properties = loadProperties(filePath);
        String value = properties.getProperty(key);
        
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in: " + filePath);
        }
        
        return value;
    }

    public static String getProperty(String filePath, String key, String defaultValue) {
        Properties properties = loadProperties(filePath);
        return properties.getProperty(key, defaultValue);
    }
}
