package com.carehires.constants;

public class FrameworkConstants {
    private FrameworkConstants() {
        throw new UnsupportedOperationException("Cannot instantiate constants class");
    }

    // Project paths
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String RESOURCES_PATH = PROJECT_PATH + "/src/main/resources/";
    public static final String TEST_RESOURCES_PATH = PROJECT_PATH + "/src/test/resources/";
    
    // Config paths
    public static final String CONFIG_PATH = RESOURCES_PATH + "config/";
    public static final String TEST_DATA_PATH = RESOURCES_PATH + "testdata/";
    
    // Report paths
    public static final String REPORT_PATH = PROJECT_PATH + "/reports/";
    public static final String EXTENT_REPORT_PATH = REPORT_PATH + "extent-reports/";
    public static final String ALLURE_RESULTS_PATH = REPORT_PATH + "allure-results/";
    public static final String SCREENSHOT_PATH = REPORT_PATH + "screenshots/";
    public static final String VIDEO_PATH = REPORT_PATH + "videos/";
    public static final String TRACE_PATH = REPORT_PATH + "traces/";
    
    // Log paths
    public static final String LOG_PATH = PROJECT_PATH + "/logs/";
    
    // Feature file path
    public static final String FEATURE_PATH = TEST_RESOURCES_PATH + "features/";
    
    // Report file names
    public static final String EXTENT_REPORT_NAME = "TestReport.html";
    public static final String ALLURE_REPORT_NAME = "allure-report";
    
    // Date formats
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    
    // Screenshot name format
    public static final String SCREENSHOT_NAME_FORMAT = "Screenshot_%s.png";
    public static final String VIDEO_NAME_FORMAT = "Video_%s.webm";
    public static final String TRACE_NAME_FORMAT = "Trace_%s.zip";
}
