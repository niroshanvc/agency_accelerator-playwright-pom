package com.carehires.helpers;

import com.carehires.constants.FrameworkConstants;
import com.carehires.utils.DateUtils;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotHelper {
    
    private static final Logger logger = LogManager.getLogger(ScreenshotHelper.class);

    public static String captureScreenshot(Page page, String screenshotName) {
        try {
            Path screenshotDir = Paths.get(FrameworkConstants.SCREENSHOT_PATH);
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            String timestamp = DateUtils.getTimestamp();
            String fileName = String.format("%s_%s.png", screenshotName, timestamp);
            Path screenshotPath = screenshotDir.resolve(fileName);

            page.screenshot(new Page.ScreenshotOptions()
                .setPath(screenshotPath)
                .setFullPage(false));

            logger.info("Screenshot captured: {}", screenshotPath);
            return screenshotPath.toString();
        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
            return null;
        }
    }

    public static String captureFullPageScreenshot(Page page, String screenshotName) {
        try {
            Path screenshotDir = Paths.get(FrameworkConstants.SCREENSHOT_PATH);
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            String timestamp = DateUtils.getTimestamp();
            String fileName = String.format("%s_%s.png", screenshotName, timestamp);
            Path screenshotPath = screenshotDir.resolve(fileName);

            page.screenshot(new Page.ScreenshotOptions()
                .setPath(screenshotPath)
                .setFullPage(true));

            logger.info("Full page screenshot captured: {}", screenshotPath);
            return screenshotPath.toString();
        } catch (Exception e) {
            logger.error("Failed to capture full page screenshot", e);
            return null;
        }
    }

    public static byte[] captureScreenshotAsBytes(Page page) {
        try {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(false));
            logger.info("Screenshot captured as bytes");
            return screenshot;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot as bytes", e);
            return new byte[0];
        }
    }

    public static String captureElementScreenshot(Page page, String selector, String screenshotName) {
        try {
            Path screenshotDir = Paths.get(FrameworkConstants.SCREENSHOT_PATH);
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }

            String timestamp = DateUtils.getTimestamp();
            String fileName = String.format("%s_%s.png", screenshotName, timestamp);
            Path screenshotPath = screenshotDir.resolve(fileName);

            page.locator(selector).screenshot(new com.microsoft.playwright.Locator.ScreenshotOptions()
                .setPath(screenshotPath));

            logger.info("Element screenshot captured: {}", screenshotPath);
            return screenshotPath.toString();
        } catch (Exception e) {
            logger.error("Failed to capture element screenshot for: {}", selector, e);
            return null;
        }
    }
}
