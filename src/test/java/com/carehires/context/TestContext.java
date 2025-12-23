package com.carehires.context;

import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.carehires.managers.ThreadLocalManager;
import com.microsoft.playwright.Page;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private static final Logger logger = LogManager.getLogger(TestContext.class);
    private static final ThreadLocal<TestContext> instance = new ThreadLocal<>();
    
    private Scenario scenario;
    private final Map<String, Object> contextData;

    private TestContext() {
        this.contextData = new HashMap<>();
    }

    public static TestContext getInstance() {
        if (instance.get() == null) {
            instance.set(new TestContext());
        }
        return instance.get();
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
        logger.info("Scenario set: {}", scenario.getName());
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setContext(String key, Object value) {
        logger.debug("Setting context: {}={}", key, value);
        contextData.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getContext(String key) {
        Object value = contextData.get(key);
        logger.debug("Getting context: {}={}", key, value);
        return (T) value;
    }

    public <T> T getContext(String key, Class<T> clazz) {
        Object value = contextData.get(key);
        logger.debug("Getting context with type: {}={}", key, value);
        return clazz.cast(value);
    }

    public boolean containsKey(String key) {
        return contextData.containsKey(key);
    }

    public void removeContext(String key) {
        logger.debug("Removing context: {}", key);
        contextData.remove(key);
    }

    public void clearContext() {
        logger.info("Clearing all context data");
        contextData.clear();
    }

    public static void reset() {
        logger.info("Resetting TestContext for thread: {}", Thread.currentThread().getId());
        if (instance.get() != null) {
            instance.get().clearContext();
            instance.remove();
        }
    }

    public Page getPage() {
        return ThreadLocalManager.getPage();
    }

    // Common context keys as constants
    public static class Keys {
        public static final String API_RESPONSE = "api.response";
        public static final String API_REQUEST = "api.request";
        public static final String USER_DATA = "user.data";
        public static final String AUTH_TOKEN = "auth.token";
        public static final String TEST_DATA = "test.data";
        public static final String PAGE_OBJECT = "page.object";
        public static final String ERROR_MESSAGE = "error.message";
    }
}
