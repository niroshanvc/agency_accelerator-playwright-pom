package com.carehires.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaitUtils {

    private static final Logger logger = LogManager.getLogger(WaitUtils.class);
    private static final int DEFAULT_TIMEOUT = 30000;

    public static void waitForElementVisible(Page page, String selector) {
        waitForElementVisible(page, selector, DEFAULT_TIMEOUT);
    }

    public static void waitForElementVisible(Page page, String selector, int timeout) {
        try {
            logger.debug("Waiting for element to be visible: {}", selector);
            page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(timeout));
            logger.debug("Element is visible: {}", selector);
        } catch (Exception e) {
            logger.error("Element not visible within timeout: {}", selector, e);
            throw new RuntimeException("Element not visible: " + selector, e);
        }
    }

    public static void waitForElementHidden(Page page, String selector) {
        waitForElementHidden(page, selector, DEFAULT_TIMEOUT);
    }

    public static void waitForElementHidden(Page page, String selector, int timeout) {
        try {
            logger.debug("Waiting for element to be hidden: {}", selector);
            page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.HIDDEN)
                .setTimeout(timeout));
            logger.debug("Element is hidden: {}", selector);
        } catch (Exception e) {
            logger.error("Element not hidden within timeout: {}", selector, e);
            throw new RuntimeException("Element not hidden: " + selector, e);
        }
    }

    public static void waitForElementAttached(Page page, String selector) {
        waitForElementAttached(page, selector, DEFAULT_TIMEOUT);
    }

    public static void waitForElementAttached(Page page, String selector, int timeout) {
        try {
            logger.debug("Waiting for element to be attached: {}", selector);
            page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.ATTACHED)
                .setTimeout(timeout));
            logger.debug("Element is attached: {}", selector);
        } catch (Exception e) {
            logger.error("Element not attached within timeout: {}", selector, e);
            throw new RuntimeException("Element not attached: " + selector, e);
        }
    }

    public static void waitForPageLoad(Page page) {
        waitForPageLoad(page, DEFAULT_TIMEOUT);
    }

    public static void waitForPageLoad(Page page, int timeout) {
        try {
            logger.debug("Waiting for page to load");
            page.waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE,
                new Page.WaitForLoadStateOptions().setTimeout(timeout));
            logger.debug("Page loaded successfully");
        } catch (Exception e) {
            logger.error("Page did not load within timeout", e);
            throw new RuntimeException("Page load timeout", e);
        }
    }

    public static void waitForUrl(Page page, String url) {
        waitForUrl(page, url, DEFAULT_TIMEOUT);
    }

    public static void waitForUrl(Page page, String url, int timeout) {
        try {
            logger.debug("Waiting for URL: {}", url);
            page.waitForURL(url, new Page.WaitForURLOptions().setTimeout(timeout));
            logger.debug("URL matched: {}", url);
        } catch (Exception e) {
            logger.error("URL did not match within timeout: {}", url, e);
            throw new RuntimeException("URL wait timeout: " + url, e);
        }
    }

    public static void staticWait(int milliseconds) {
        try {
            logger.debug("Static wait for {} ms", milliseconds);
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            logger.error("Static wait interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
