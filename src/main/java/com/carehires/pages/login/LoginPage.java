package com.carehires.pages.login;

import com.carehires.pages.base.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    // Locators
    private static final String USERNAME_INPUT = "#username";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "button[type='submit']";
    private static final String ERROR_MESSAGE = ".error-message";
    private static final String REMEMBER_ME_CHECKBOX = "#rememberMe";
    private static final String FORGOT_PASSWORD_LINK = "a[href*='forgot-password']";

    public LoginPage(Page page) {
        super(page);
    }

    public LoginPage navigateToLoginPage(String url) {
        logger.info("Navigating to login page");
        navigateTo(url);
        return this;
    }

    public LoginPage enterUsername(String username) {
        logger.info("Entering username: {}", username);
        fill(USERNAME_INPUT, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        logger.info("Entering password");
        fill(PASSWORD_INPUT, password);
        return this;
    }

    public LoginPage checkRememberMe() {
        logger.info("Checking remember me checkbox");
        check(REMEMBER_ME_CHECKBOX);
        return this;
    }

    public void clickLoginButton() {
        logger.info("Clicking login button");
        click(LOGIN_BUTTON);
    }

    public void login(String username, String password) {
        logger.info("Performing login with username: {}", username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        logger.info("Getting error message");
        return getText(ERROR_MESSAGE);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementVisible(ERROR_MESSAGE);
    }

    public void clickForgotPassword() {
        logger.info("Clicking forgot password link");
        click(FORGOT_PASSWORD_LINK);
    }

    // Assertions
    public void verifyLoginPageLoaded() {
        logger.info("Verifying login page is loaded");
        assertElementVisible(USERNAME_INPUT);
        assertElementVisible(PASSWORD_INPUT);
        assertElementVisible(LOGIN_BUTTON);
    }

    public void verifyErrorMessage(String expectedMessage) {
        logger.info("Verifying error message: {}", expectedMessage);
        assertTextEquals(ERROR_MESSAGE, expectedMessage);
    }
}
