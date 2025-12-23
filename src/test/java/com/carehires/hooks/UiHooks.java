package com.carehires.hooks;

import com.carehires.config.PlaywrightConfig;
import com.carehires.helpers.TraceHelper;
import com.carehires.managers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UiHooks {

    private static final Logger logger = LogManager.getLogger(UiHooks.class);
    private final PlaywrightConfig config;

    public UiHooks() {
        this.config = PlaywrightConfig.getInstance();
    }

    @Before("@ui")
    public void beforeUiScenario(Scenario scenario) {
        logger.info("Initializing UI test environment for: {}", scenario.getName());
        
        try {
            DriverManager.initializeDriver();
            logger.info("Browser initialized successfully");
            
            if (config.isTracingEnabled()) {
                TraceHelper.startTracing(DriverManager.getContext());
            }
        } catch (Exception e) {
            logger.error("Failed to initialize UI test environment", e);
            throw new RuntimeException("UI initialization failed", e);
        }
    }

    @After("@ui")
    public void afterUiScenario(Scenario scenario) {
        logger.info("Cleaning up UI test environment for: {}", scenario.getName());
        
        try {
            if (config.isTracingEnabled() && !scenario.isFailed()) {
                TraceHelper.stopTracingWithoutSaving(DriverManager.getContext());
            }
            
            DriverManager.quitDriver();
            logger.info("Browser closed successfully");
        } catch (Exception e) {
            logger.warn("Error during UI cleanup", e);
        }
    }
}
