package com.carehires.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/ui",
    glue = {"com.carehires.steps", "com.carehires.hooks"},
    tags = "@ui",
    plugin = {
        "pretty",
        "html:reports/cucumber-reports/ui-report.html",
        "json:reports/cucumber-reports/ui-report.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true,
    dryRun = false
)
public class UiTestRunner {
}
