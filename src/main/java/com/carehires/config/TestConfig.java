package com.carehires.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static TestConfig instance;
    private final Properties properties;

    private TestConfig() {
        this.properties = loadProperties();
    }

    public static TestConfig getInstance() {
        if (instance == null) {
            synchronized (TestConfig.class) {
                if (instance == null) {
                    instance = new TestConfig();
                }
            }
        }
        return instance;
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("config/framework.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find framework.properties");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load framework properties", e);
        }
        return props;
    }

    public boolean isParallelExecution() {
        return Boolean.parseBoolean(getProperty("parallel.execution", "true"));
    }

    public int getThreadCount() {
        return Integer.parseInt(getProperty("thread.count", "4"));
    }

    public String getReportPath() {
        return getProperty("report.path", "reports/");
    }

    public String getScreenshotPath() {
        return getProperty("screenshot.path", "reports/screenshots/");
    }

    public String getExtentReportPath() {
        return getReportPath() + "extent-reports/";
    }

    public String getAllureResultsPath() {
        return getReportPath() + "allure-results/";
    }

    public boolean isExtentReportEnabled() {
        return Boolean.parseBoolean(getProperty("extent.report.enabled", "true"));
    }

    public boolean isAllureReportEnabled() {
        return Boolean.parseBoolean(getProperty("allure.report.enabled", "true"));
    }

    private String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    private String getProperty(String key) {
        return properties.getProperty(key);
    }
}
