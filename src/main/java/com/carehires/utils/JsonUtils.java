package com.carehires.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonUtils {
    
    private static final Logger logger = LogManager.getLogger(JsonUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .enable(SerializationFeature.INDENT_OUTPUT);

    public static String toJson(Object object) {
        try {
            String json = objectMapper.writeValueAsString(object);
            logger.debug("Converted object to JSON: {}", json);
            return json;
        } catch (IOException e) {
            logger.error("Failed to convert object to JSON", e);
            throw new RuntimeException("Error converting to JSON", e);
        }
    }

    // FIX: Add <T> before return type and Class<T>
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            T object = objectMapper.readValue(json, clazz);
            logger.debug("Converted JSON to object: {}", clazz.getSimpleName());
            return object;
        } catch (IOException e) {
            logger.error("Failed to convert JSON to object: {}", clazz.getSimpleName(), e);
            throw new RuntimeException("Error converting from JSON", e);
        }
    }

    // FIX: Add <T> before return type and TypeReference<T>
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            T object = objectMapper.readValue(json, typeReference);
            logger.debug("Converted JSON to object with TypeReference");
            return object;
        } catch (IOException e) {
            logger.error("Failed to convert JSON to object with TypeReference", e);
            throw new RuntimeException("Error converting from JSON", e);
        }
    }

    // FIX: Add <T> before return type and Class<T>
    public static <T> T fromJsonFile(String filePath, Class<T> clazz) {
        try {
            T object = objectMapper.readValue(new File(filePath), clazz);
            logger.info("Loaded object from JSON file: {}", filePath);
            return object;
        } catch (IOException e) {
            logger.error("Failed to load JSON file: {}", filePath, e);
            throw new RuntimeException("Error reading JSON file: " + filePath, e);
        }
    }

    public static void toJsonFile(Object object, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), object);
            logger.info("Saved object to JSON file: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to save JSON file: {}", filePath, e);
            throw new RuntimeException("Error writing JSON file: " + filePath, e);
        }
    }

    public static boolean isValidJson(String json) {
        try {
            objectMapper.readTree(json);
            return true;
        } catch (IOException e) {
            logger.debug("Invalid JSON string", e);
            return false;
        }
    }
}
