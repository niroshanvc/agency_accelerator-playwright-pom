package com.carehires.config;

import com.carehires.enums.BrowserType;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class PlaywrightConfig {

    private static PlaywrightConfig instance;

    private PlaywrightConfig() {}

    public static PlaywrightConfig getInstance() {
        if (instance == null) {
            synchronized (PlaywrightConfig.class) {
                if (instance == null) {
                    instance = new PlaywrightConfig();
                }
            }
        }
        return instance;
    }

    public BrowserType getBrowserType() {
        String browser = System.getProperty("browser", "chromium").toLowerCase();
        return BrowserType.valueOf(browser.toUpperCase());
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }

    public LaunchOptions getLaunchOptions() {
        LaunchOptions options = new LaunchOptions();
        options.setHeadless(isHeadless());
        options.setTimeout(getTimeout());
        
        if (Boolean.parseBoolean(System.getProperty("slowmo", "false"))) {
            options.setSlowMo(1000);
        }

        String browserArgs = System.getProperty("browser.args", "");
        if (!browserArgs.isEmpty()) {
            options.setArgs(java.util.Arrays.asList(browserArgs.split(",")));
        }

        return options;
    }

    public Browser.NewContextOptions getContextOptions() {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        
        String viewport = System.getProperty("viewport", "1920x1080");
        String[] dimensions = viewport.split("x");
        options.setViewportSize(
            Integer.parseInt(dimensions[0]), 
            Integer.parseInt(dimensions[1])
        );

        options.setBaseURL(EnvironmentConfig.getInstance().getBaseUrl());
        options.setIgnoreHTTPSErrors(true);
        
        // Enable video recording if needed
        if (Boolean.parseBoolean(System.getProperty("record.video", "false"))) {
            options.setRecordVideoDir(java.nio.file.Paths.get("reports/videos"));
        }

        return options;
    }

    public double getTimeout() {
        return Double.parseDouble(System.getProperty("timeout", "30000"));
    }

    public int getMaxRetries() {
        return Integer.parseInt(System.getProperty("max.retries", "2"));
    }

    public boolean isTracingEnabled() {
        return Boolean.parseBoolean(System.getProperty("enable.tracing", "true"));
    }

    public boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(System.getProperty("screenshot.on.failure", "true"));
    }
}
