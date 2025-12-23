package com.carehires.helpers;

import com.carehires.constants.FrameworkConstants;
import com.microsoft.playwright.BrowserContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VideoHelper {
 
    private static final Logger logger = LogManager.getLogger(VideoHelper.class);

    public static void saveVideo(BrowserContext context, String videoName) {
        try {
            Path videoDir = Paths.get(FrameworkConstants.VIDEO_PATH);
            if (!Files.exists(videoDir)) {
                Files.createDirectories(videoDir);
            }

            Path videoPath = context.pages().get(0).video().path();
            Path destinationPath = videoDir.resolve(videoName + ".webm");
            
            // Close page to finalize video
            context.pages().get(0).close();
            
            // Move video to reports folder
            Files.move(videoPath, destinationPath);
            
            logger.info("Video saved: {}", destinationPath);
        } catch (Exception e) {
            logger.error("Failed to save video", e);
        }
    }

    public static Path getVideoPath(BrowserContext context) {
        try {
            if (context.pages().isEmpty()) {
                logger.warn("No pages available in context");
                return null;
            }
            return context.pages().get(0).video().path();
        } catch (Exception e) {
            logger.error("Failed to get video path", e);
            return null;
        }
    }
}
