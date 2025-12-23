package com.carehires.listeners;

import io.qameta.allure.Allure;
import org.junit.platform.launcher.TestExecutionListener;

public class AllureListener implements TestExecutionListener {
    // Allure integration is primarily handled via cucumber-jvm plugin.
    // This listener can be used for JUnit platform specific events if needed.
    
    public void log(String message) {
        Allure.step(message);
    }
}
