package com.carehires.helpers;

import com.carehires.constants.FrameworkConstants;
import com.carehires.utils.DateUtils;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TraceHelper {
    
    private static final Logger logger = LogManager.getLogger(TraceHelper.class);

    public static void startTracing(BrowserContext context) {
        try {
            context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
            logger.info("Tracing started");
        } catch (Exception e) {
            logger.error("Failed to start tracing", e);
        }
    }

    public static String stopTracing(BrowserContext context, String traceName) {
        try {
            Path traceDir = Paths.get(FrameworkConstants.TRACE_PATH);
            if (!Files.exists(traceDir)) {
                Files.createDirectories(traceDir);
            }

            String timestamp = DateUtils.getTimestamp();
            String fileName = String.format("%s_%s.zip", traceName, timestamp);
            Path tracePath = traceDir.resolve(fileName);

            context.tracing().stop(new Tracing.StopOptions()
                .setPath(tracePath));

            logger.info("Trace saved: {}", tracePath);
            return tracePath.toString();
        } catch (Exception e) {
            logger.error("Failed to stop tracing", e);
            return null;
        }
    }

    public static void stopTracingWithoutSaving(BrowserContext context) {
        try {
            context.tracing().stop();
            logger.info("Tracing stopped without saving");
        } catch (Exception e) {
            logger.error("Failed to stop tracing", e);
        }
    }
}
