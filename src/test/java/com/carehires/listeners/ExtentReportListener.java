package com.carehires.listeners;

import com.aventstack.extentreports.service.ExtentService;
import org.junit.platform.launcher.TestExecutionListener;

public class ExtentReportListener implements TestExecutionListener {
    // Extent Reports for Cucumber uses tech.grasshopper adapter which auto-hooks.
    // Additional custom listener logic can be added here if not covered by the adapter.
    
    public void flushReports() {
        ExtentService.getInstance().flush();
    }
}
