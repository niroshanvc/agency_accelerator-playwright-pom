package com.carehires.listeners;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestListener implements TestExecutionListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        logger.info("Test started: " + testIdentifier.getDisplayName());
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        logger.info("Test finished: " + testIdentifier.getDisplayName() + " Status: " + testExecutionResult.getStatus());
    }
}
