package com.carehires.factories;

import com.carehires.config.PlaywrightConfig;
import com.carehires.enums.BrowserType;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrowserFactory {
    
    private static final Logger logger = LogManager.getLogger(BrowserFactory.class);
    private final PlaywrightConfig config;

    public BrowserFactory() {
        this.config = PlaywrightConfig.getInstance();
    }

    public Browser createBrowser(Playwright playwright) {
        BrowserType browserType = config.getBrowserType();
        logger.info("Launching {} browser in {} mode", 
            browserType, config.isHeadless() ? "headless" : "headed");

        Browser browser = switch (browserType) {
            case CHROMIUM -> playwright.chromium().launch(config.getLaunchOptions());
            case FIREFOX -> playwright.firefox().launch(config.getLaunchOptions());
            case WEBKIT -> playwright.webkit().launch(config.getLaunchOptions());
            case CHROME -> {
                var options = config.getLaunchOptions();
                options.setChannel("chrome");
                yield playwright.chromium().launch(options);
            }
            case EDGE -> {
                var options = config.getLaunchOptions();
                options.setChannel("msedge");
                yield playwright.chromium().launch(options);
            }
        };

        logger.info("Browser launched successfully: {}", browserType);
        return browser;
    }

    public BrowserContext createContext(Browser browser) {
        logger.info("Creating new browser context");
        BrowserContext context = browser.newContext(config.getContextOptions());
        
        if (config.isTracingEnabled()) {
            context.tracing().start(new com.microsoft.playwright.Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
            logger.info("Tracing enabled for browser context");
        }
        
        return context;
    }

    public Page createPage(BrowserContext context) {
        logger.info("Creating new page");
        Page page = context.newPage();
        page.setDefaultTimeout(config.getTimeout());
        return page;
    }
}
