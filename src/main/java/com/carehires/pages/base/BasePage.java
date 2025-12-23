package com.carehires.pages.base;

import com.carehires.constants.TimeoutConstants;
import com.carehires.utils.WaitUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BasePage {
    
    protected final Page page;
    protected final Logger logger;

    protected BasePage(Page page) {
        this.page = page;
        this.logger = LogManager.getLogger(this.getClass());
    }

    protected void navigateTo(String url) {
        logger.info("Navigating to URL: {}", url);
        page.navigate(url);
        WaitUtils.waitForPageLoad(page);
    }

    protected void click(String selector) {
        logger.info("Clicking element: {}", selector);
        waitForElementVisible(selector);
        page.locator(selector).click();
    }

    protected void click(Locator locator) {
        logger.info("Clicking locator");
        locator.click();
    }

    protected void fill(String selector, String text) {
        logger.info("Filling text '{}' in element: {}", text, selector);
        waitForElementVisible(selector);
        page.locator(selector).fill(text);
    }

    protected void fill(Locator locator, String text) {
        logger.info("Filling text in locator");
        locator.fill(text);
    }

    protected void type(String selector, String text) {
        logger.info("Typing text '{}' in element: {}", text, selector);
        waitForElementVisible(selector);
        page.locator(selector).type(text);
    }

    protected String getText(String selector) {
        logger.info("Getting text from element: {}", selector);
        waitForElementVisible(selector);
        return page.locator(selector).textContent();
    }

    protected String getText(Locator locator) {
        logger.info("Getting text from locator");
        return locator.textContent();
    }

    protected String getInputValue(String selector) {
        logger.info("Getting input value from element: {}", selector);
        return page.locator(selector).inputValue();
    }

    protected boolean isElementVisible(String selector) {
        try {
            return page.locator(selector).isVisible();
        } catch (Exception e) {
            logger.debug("Element not visible: {}", selector);
            return false;
        }
    }

    protected boolean isElementEnabled(String selector) {
        return page.locator(selector).isEnabled();
    }

    protected boolean isElementChecked(String selector) {
        return page.locator(selector).isChecked();
    }

    protected void check(String selector) {
        logger.info("Checking checkbox/radio: {}", selector);
        if (!isElementChecked(selector)) {
            page.locator(selector).check();
        }
    }

    protected void uncheck(String selector) {
        logger.info("Unchecking checkbox: {}", selector);
        if (isElementChecked(selector)) {
            page.locator(selector).uncheck();
        }
    }

    protected void selectOption(String selector, String value) {
        logger.info("Selecting option '{}' from dropdown: {}", value, selector);
        page.locator(selector).selectOption(value);
    }

    protected void waitForElementVisible(String selector) {
        WaitUtils.waitForElementVisible(page, selector);
    }

    protected void waitForElementHidden(String selector) {
        WaitUtils.waitForElementHidden(page, selector);
    }

    protected void waitForElementAttached(String selector) {
        WaitUtils.waitForElementAttached(page, selector);
    }

    protected void scrollToElement(String selector) {
        logger.info("Scrolling to element: {}", selector);
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    protected void hover(String selector) {
        logger.info("Hovering over element: {}", selector);
        page.locator(selector).hover();
    }

    protected void doubleClick(String selector) {
        logger.info("Double clicking element: {}", selector);
        page.locator(selector).dblclick();
    }

    protected void rightClick(String selector) {
        logger.info("Right clicking element: {}", selector);
        page.locator(selector).click(new Locator.ClickOptions().setButton(
            com.microsoft.playwright.options.MouseButton.RIGHT));
    }

    protected void pressKey(String key) {
        logger.info("Pressing key: {}", key);
        page.keyboard().press(key);
    }

    protected int getElementCount(String selector) {
        return page.locator(selector).count();
    }

    protected String getAttribute(String selector, String attribute) {
        logger.info("Getting attribute '{}' from element: {}", attribute, selector);
        return page.locator(selector).getAttribute(attribute);
    }

    protected void dragAndDrop(String sourceSelector, String targetSelector) {
        logger.info("Dragging from '{}' to '{}'", sourceSelector, targetSelector);
        page.locator(sourceSelector).dragTo(page.locator(targetSelector));
    }

    protected void uploadFile(String selector, String filePath) {
        logger.info("Uploading file '{}' to element: {}", filePath, selector);
        page.locator(selector).setInputFiles(java.nio.file.Paths.get(filePath));
    }

    protected void clearInput(String selector) {
        logger.info("Clearing input: {}", selector);
        page.locator(selector).clear();
    }

    protected String getCurrentUrl() {
        return page.url();
    }

    protected String getPageTitle() {
        return page.title();
    }

    protected void reloadPage() {
        logger.info("Reloading page");
        page.reload();
    }

    protected void goBack() {
        logger.info("Navigating back");
        page.goBack();
    }

    protected void goForward() {
        logger.info("Navigating forward");
        page.goForward();
    }

    // Playwright Assertions
    protected void assertElementVisible(String selector) {
        logger.info("Asserting element is visible: {}", selector);
        assertThat(page.locator(selector)).isVisible();
    }

    protected void assertElementHidden(String selector) {
        logger.info("Asserting element is hidden: {}", selector);
        assertThat(page.locator(selector)).isHidden();
    }

    protected void assertElementEnabled(String selector) {
        logger.info("Asserting element is enabled: {}", selector);
        assertThat(page.locator(selector)).isEnabled();
    }

    protected void assertElementDisabled(String selector) {
        logger.info("Asserting element is disabled: {}", selector);
        assertThat(page.locator(selector)).isDisabled();
    }

    protected void assertTextEquals(String selector, String expectedText) {
        logger.info("Asserting text equals '{}' for element: {}", expectedText, selector);
        assertThat(page.locator(selector)).hasText(expectedText);
    }

    protected void assertTextContains(String selector, String expectedText) {
        logger.info("Asserting text contains '{}' for element: {}", expectedText, selector);
        assertThat(page.locator(selector)).containsText(expectedText);
    }

    protected void assertElementCount(String selector, int expectedCount) {
        logger.info("Asserting element count is {} for selector: {}", expectedCount, selector);
        assertThat(page.locator(selector)).hasCount(expectedCount);
    }
}
