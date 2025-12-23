package com.carehires.managers;

import com.carehires.config.EnvironmentConfig;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ApiContextManager {
    
    private static final Logger logger = LogManager.getLogger(ApiContextManager.class);

    public static void initializeApiContext() {
        initializeApiContext(null);
    }

    public static void initializeApiContext(Map headers) {
        logger.info("Initializing API context for thread: {}", Thread.currentThread().getId());

        Playwright playwright = ThreadLocalManager.getPlaywright();
        if (playwright == null) {
            playwright = Playwright.create();
            ThreadLocalManager.setPlaywright(playwright);
        }

        APIRequest.NewContextOptions options = new APIRequest.NewContextOptions()
            .setBaseURL(EnvironmentConfig.getInstance().getApiBaseUrl())
            .setIgnoreHTTPSErrors(true);

        if (headers != null && !headers.isEmpty()) {
            options.setExtraHTTPHeaders(headers);
        }

        APIRequestContext requestContext = playwright.request().newContext(options);
        ThreadLocalManager.setApiRequestContext(requestContext);

        logger.info("API context initialized successfully");
    }

    public static void initializeApiContextWithAuth(String token) {
        Map headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        headers.put("Content-Type", "application/json");
        initializeApiContext(headers);
    }

    public static APIRequestContext getRequestContext() {
        APIRequestContext context = ThreadLocalManager.getApiRequestContext();
        if (context == null) {
            throw new IllegalStateException(
                "APIRequestContext is not initialized. Call initializeApiContext() first.");
        }
        return context;
    }

    public static void disposeApiContext() {
        logger.info("Disposing API context for thread: {}", Thread.currentThread().getId());
        APIRequestContext context = ThreadLocalManager.getApiRequestContext();
        if (context != null) {
            context.dispose();
            ThreadLocalManager.removeApiRequestContext();
        }
    }
}
