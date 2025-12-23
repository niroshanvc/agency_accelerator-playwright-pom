package com.carehires.managers;

import com.carehires.factories.BrowserFactory;
import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final BrowserFactory browserFactory = new BrowserFactory();

    public static void initializeDriver() {
        logger.info("Initializing Playwright driver for thread: {}", 
            Thread.currentThread().getId());

        Playwright playwright = Playwright.create();
        ThreadLocalManager.setPlaywright(playwright);

        Browser browser = browserFactory.createBrowser(playwright);
        ThreadLocalManager.setBrowser(browser);

        BrowserContext context = browserFactory.createContext(browser);
        ThreadLocalManager.setContext(context);

        Page page = browserFactory.createPage(context);
        ThreadLocalManager.setPage(page);

        logger.info("Playwright driver initialized successfully");
    }

    public static Page getPage() {
        Page page = ThreadLocalManager.getPage();
        if (page == null) {
            throw new IllegalStateException("Page is not initialized. Call initializeDriver() first.");
        }
        return page;
    }

    public static BrowserContext getContext() {
        BrowserContext context = ThreadLocalManager.getContext();
        if (context == null) {
            throw new IllegalStateException("BrowserContext is not initialized.");
        }
        return context;
    }

    public static Browser getBrowser() {
        Browser browser = ThreadLocalManager.getBrowser();
        if (browser == null) {
            throw new IllegalStateException("Browser is not initialized.");
        }
        return browser;
    }

    public static Playwright getPlaywright() {
        Playwright playwright = ThreadLocalManager.getPlaywright();
        if (playwright == null) {
            throw new IllegalStateException("Playwright is not initialized.");
        }
        return playwright;
    }

    public static void quitDriver() {
        logger.info("Quitting Playwright driver for thread: {}", Thread.currentThread().getId());
        ThreadLocalManager.quitAll();
    }
}
