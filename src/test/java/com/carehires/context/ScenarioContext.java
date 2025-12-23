package com.carehires.context;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.Map;


/**
 * Alternative context implementation for scenarios
 * Can be used alongside TestContext or as a replacement
 */
public class ScenarioContext {
    
    private static final Logger logger = LogManager.getLogger(ScenarioContext.class);
    private static final ThreadLocal<ScenarioContext> instance = new ThreadLocal<>();
    
    private final Map scenarioData;
    private long scenarioStartTime;
    private long scenarioEndTime;

    private ScenarioContext() {
        this.scenarioData = new HashMap<>();
    }

    public static ScenarioContext getInstance() {
        if (instance.get() == null) {
            instance.set(new ScenarioContext());
        }
        return instance.get();
    }

    public void set(String key, Object value) {
        logger.debug("Setting scenario data: {}={}", key, value);
        scenarioData.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public  T get(String key) {
        return (T) scenarioData.get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(scenarioData.get(key));
    }

    public boolean contains(String key) {
        return scenarioData.containsKey(key);
    }

    public void remove(String key) {
        scenarioData.remove(key);
    }

    public void clear() {
        logger.info("Clearing scenario context");
        scenarioData.clear();
    }

    public void startTimer() {
        scenarioStartTime = System.currentTimeMillis();
        logger.debug("Scenario timer started");
    }

    public void stopTimer() {
        scenarioEndTime = System.currentTimeMillis();
        logger.debug("Scenario timer stopped");
    }

    public long getExecutionTime() {
        return scenarioEndTime - scenarioStartTime;
    }

    public static void reset() {
        if (instance.get() != null) {
            instance.get().clear();
            instance.remove();
        }
        logger.debug("ScenarioContext reset for thread: {}", Thread.currentThread().getId());
    }
}
