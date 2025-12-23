package com.carehires.pages;

import com.carehires.pages.base.BasePage;
import com.carehires.pages.components.FooterComponent;
import com.carehires.pages.components.HeaderComponent;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class DashboardPage extends BasePage {

    private final HeaderComponent header;
    private final FooterComponent footer;
    private final Locator welcomeMessage;

    public DashboardPage(Page page) {
        super(page);
        this.header = new HeaderComponent(page);
        this.footer = new FooterComponent(page);
        this.welcomeMessage = page.locator("h1.welcome-message");
    }

    public HeaderComponent getHeader() {
        return header;
    }

    public FooterComponent getFooter() {
        return footer;
    }

    public String getWelcomeMessage() {
        return welcomeMessage.textContent();
    }

    public boolean isLoaded() {
        return welcomeMessage.isVisible();
    }
}
