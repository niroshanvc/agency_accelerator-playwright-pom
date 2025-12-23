package com.carehires.hooks;

import com.carehires.context.TestContext;
import com.carehires.managers.ApiContextManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiHooks {

    private static final Logger logger = LogManager.getLogger(ApiHooks.class);

    @Before("@api")
    public void beforeApiScenario(Scenario scenario) {
        logger.info("Initializing API test environment for: {}", scenario.getName());
        
        try {
            ApiContextManager.initializeApiContext();
            logger.info("API context initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize API test environment", e);
            throw new RuntimeException("API initialization failed", e);
        }
    }

    @After("@api")
    public void afterApiScenario(Scenario scenario) {
        logger.info("Cleaning up API test environment for: {}", scenario.getName());
        
        try {
            // Log API response if available in context
            if (TestContext.getInstance().containsKey(TestContext.Keys.API_RESPONSE)) {
                logger.debug("API Response available in context");
            }
            
            ApiContextManager.disposeApiContext();
            logger.info("API context disposed successfully");
        } catch (Exception e) {
            logger.warn("Error during API cleanup", e);
        }
    }
}
