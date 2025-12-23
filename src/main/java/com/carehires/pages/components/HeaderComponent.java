package com.carehires.pages.components;

import com.carehires.pages.base.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class HeaderComponent extends BasePage {

    private final Locator userProfileIcon;
    private final Locator logoutButton;

    public HeaderComponent(Page page) {
        super(page);
        this.userProfileIcon = page.locator("#user-profile");
        this.logoutButton = page.locator("button:has-text('Logout')");
    }

    public boolean isUserProfileDisplayed() {
        return userProfileIcon.isVisible();
    }

    public void clickLogout() {
        click(logoutButton);
    }
}
