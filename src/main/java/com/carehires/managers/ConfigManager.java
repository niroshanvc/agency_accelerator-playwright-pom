package com.carehires.managers;

import com.carehires.config.EnvironmentConfig;
import com.carehires.config.PlaywrightConfig;
import com.carehires.config.TestConfig;

public class ConfigManager {
    
    private static ConfigManager instance;
    private final EnvironmentConfig environmentConfig;
    private final PlaywrightConfig playwrightConfig;
    private final TestConfig testConfig;

    private ConfigManager() {
        this.environmentConfig = EnvironmentConfig.getInstance();
        this.playwrightConfig = PlaywrightConfig.getInstance();
        this.testConfig = TestConfig.getInstance();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    public EnvironmentConfig getEnvironmentConfig() {
        return environmentConfig;
    }

    public PlaywrightConfig getPlaywrightConfig() {
        return playwrightConfig;
    }

    public TestConfig getTestConfig() {
        return testConfig;
    }
}
