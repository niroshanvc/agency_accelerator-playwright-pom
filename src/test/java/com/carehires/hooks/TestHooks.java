package com.carehires.hooks;

import com.carehires.config.PlaywrightConfig;
import com.carehires.context.TestContext;
import com.carehires.helpers.ScreenshotHelper;
import com.carehires.helpers.TraceHelper;
import com.carehires.managers.DriverManager;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestHooks {
    
    private static final Logger logger = LogManager.getLogger(TestHooks.class);
    private final PlaywrightConfig config;

    public TestHooks() {
        this.config = PlaywrightConfig.getInstance();
    }

    @BeforeAll
    public static void beforeAll() {
        logger.info("=== Test Suite Execution Started ===");
    }

    @Before
    public void before(Scenario scenario) {
        logger.info("=== Starting Scenario: {} ===", scenario.getName());
        logger.info("Tags: {}", scenario.getSourceTagNames());
        
        TestContext.getInstance().setScenario(scenario);
        
        // Log scenario details
        logger.info("Feature: {}", scenario.getUri());
        logger.info("Line: {}", scenario.getLine());
    }

    @After
    public void after(Scenario scenario) {
        logger.info("=== Scenario Status: {} ===", scenario.getStatus());
        
        if (scenario.isFailed() && config.isScreenshotOnFailure()) {
            try {
                if (DriverManager.getPage() != null) {
                    byte[] screenshot = ScreenshotHelper.captureScreenshotAsBytes(
                        DriverManager.getPage());
                    scenario.attach(screenshot, "image/png", 
                        scenario.getName() + "_failure");
                    logger.info("Screenshot attached to scenario");
                }
            } catch (Exception e) {
                logger.warn("Failed to capture screenshot", e);
            }

            try {
                if (config.isTracingEnabled() && DriverManager.getContext() != null) {
                    String tracePath = TraceHelper.stopTracing(
                        DriverManager.getContext(), scenario.getName());
                    logger.info("Trace saved for failed scenario: {}", tracePath);
                }
            } catch (Exception e) {
                logger.warn("Failed to save trace", e);
            }
        }
        
        logger.info("=== Scenario Completed: {} ===", scenario.getName());
        TestContext.reset();
    }

    @AfterAll
    public static void afterAll() {
        logger.info("=== Test Suite Execution Completed ===");
    }
}
