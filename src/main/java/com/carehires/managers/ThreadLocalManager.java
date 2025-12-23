package com.carehires.managers;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadLocalManager {

    private static final Logger logger = LogManager.getLogger(ThreadLocalManager.class);

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();
    private static final ThreadLocal<APIRequestContext> apiRequestContext = new ThreadLocal<>();

    // Playwright
    public static void setPlaywright(Playwright pw) {
        playwright.set(pw);
        logger.debug("Playwright instance set for thread: {}", Thread.currentThread().getId());
    }

    public static Playwright getPlaywright() {
        return playwright.get();
    }

    public static void removePlaywright() {
        playwright.remove();
        logger.debug("Playwright instance removed for thread: {}", Thread.currentThread().getId());
    }

    // Browser
    public static void setBrowser(Browser br) {
        browser.set(br);
        logger.debug("Browser instance set for thread: {}", Thread.currentThread().getId());
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    public static void removeBrowser() {
        browser.remove();
        logger.debug("Browser instance removed for thread: {}", Thread.currentThread().getId());
    }

    // BrowserContext
    public static void setContext(BrowserContext ctx) {
        context.set(ctx);
        logger.debug("BrowserContext instance set for thread: {}", Thread.currentThread().getId());
    }

    public static BrowserContext getContext() {
        return context.get();
    }

    public static void removeContext() {
        context.remove();
        logger.debug("BrowserContext instance removed for thread: {}",
                Thread.currentThread().getId());
    }

    // Page
    public static void setPage(Page pg) {
        page.set(pg);
        logger.debug("Page instance set for thread: {}", Thread.currentThread().getId());
    }

    public static Page getPage() {
        return page.get();
    }

    public static void removePage() {
        page.remove();
        logger.debug("Page instance removed for thread: {}", Thread.currentThread().getId());
    }

    // APIRequestContext
    public static void setApiRequestContext(APIRequestContext apiContext) {
        apiRequestContext.set(apiContext);
        logger.debug("APIRequestContext instance set for thread: {}",
                Thread.currentThread().getId());
    }

    public static APIRequestContext getApiRequestContext() {
        return apiRequestContext.get();
    }

    public static void removeApiRequestContext() {
        apiRequestContext.remove();
        logger.debug("APIRequestContext instance removed for thread: {}",
                Thread.currentThread().getId());
    }

    // Clean up all ThreadLocal variables
    public static void quitAll() {
        logger.info("Cleaning up all ThreadLocal instances for thread: {}",
                Thread.currentThread().getId());

        try {
            if (getApiRequestContext() != null) {
                getApiRequestContext().dispose();
                removeApiRequestContext();
            }
        } catch (Exception e) {
            logger.warn("Error disposing APIRequestContext", e);
        }

        try {
            if (getPage() != null) {
                getPage().close();
                removePage();
            }
        } catch (Exception e) {
            logger.warn("Error closing Page", e);
        }

        try {
            if (getContext() != null) {
                getContext().close();
                removeContext();
            }
        } catch (Exception e) {
            logger.warn("Error closing BrowserContext", e);
        }

        try {
            if (getBrowser() != null) {
                getBrowser().close();
                removeBrowser();
            }
        } catch (Exception e) {
            logger.warn("Error closing Browser", e);
        }

        try {
            if (getPlaywright() != null) {
                getPlaywright().close();
                removePlaywright();
            }
        } catch (Exception e) {
            logger.warn("Error closing Playwright", e);
        }

        logger.info("ThreadLocal cleanup completed for thread: {}",
                Thread.currentThread().getId());
    }
}
