package com.carehires.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlReader {
    
    private static final Logger logger = LogManager.getLogger(YamlReader.class);

    public static Map readYaml(String filePath) {
        logger.info("Reading YAML file: {}", filePath);
        Yaml yaml = new Yaml();
        
        try (InputStream inputStream = YamlReader.class.getClassLoader()
                .getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new RuntimeException("YAML file not found: " + filePath);
            }
            Map data = yaml.load(inputStream);
            logger.info("Successfully loaded YAML file: {}", filePath);
            return data;
        } catch (Exception e) {
            logger.error("Failed to read YAML file: {}", filePath, e);
            throw new RuntimeException("Error reading YAML file: " + filePath, e);
        }
    }

    @SuppressWarnings("unchecked")
    public static  T getTestData(String filePath, String key, Class type) {
        Map data = readYaml(filePath);
        Object value = data.get(key);
        
        if (value == null) {
            throw new RuntimeException("Key '" + key + "' not found in YAML file: " + filePath);
        }
        
        return (T) value;
    }

    @SuppressWarnings("unchecked")
    public static Map getTestDataAsMap(String filePath, String key) {
        Map data = readYaml(filePath);
        Object value = data.get(key);
        
        if (value == null) {
            throw new RuntimeException("Key '" + key + "' not found in YAML file: " + filePath);
        }
        
        if (!(value instanceof Map)) {
            throw new RuntimeException("Value for key '" + key + "' is not a Map");
        }
        
        return (Map) value;
    }
}
