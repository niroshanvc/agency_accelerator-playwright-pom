package com.carehires.factories;

import com.carehires.api.clients.BaseApiClient;
import com.carehires.managers.ApiContextManager;
import com.microsoft.playwright.APIRequestContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Constructor;

public class ApiClientFactory {
    
    private static final Logger logger = LogManager.getLogger(ApiClientFactory.class);

    public static <T extends BaseApiClient> T createInstance(Class<T> clientClass) {
        try {
            APIRequestContext requestContext = ApiContextManager.getRequestContext();
            Constructor<T> constructor = clientClass.getDeclaredConstructor(APIRequestContext.class);
            T instance = constructor.newInstance(requestContext);
            logger.info("Created API client instance: {}", clientClass.getSimpleName());
            return instance;
        } catch (Exception e) {
            logger.error("Failed to create API client instance: {}", clientClass.getSimpleName(), e);
            throw new RuntimeException("Failed to create API client: " + clientClass.getName(), e);
        }
    }

    public static <T extends BaseApiClient> T createInstance(Class<T> clientClass, 
                                                             APIRequestContext requestContext) {
        try {
            Constructor<T> constructor = clientClass.getDeclaredConstructor(APIRequestContext.class);
            T instance = constructor.newInstance(requestContext);
            logger.info("Created API client instance with custom context: {}", 
                clientClass.getSimpleName());
            return instance;
        } catch (Exception e) {
            logger.error("Failed to create API client instance: {}", clientClass.getSimpleName(), e);
            throw new RuntimeException("Failed to create API client: " + clientClass.getName(), e);
        }
    }
}
