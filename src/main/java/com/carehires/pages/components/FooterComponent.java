package com.carehires.pages.components;

import com.carehires.pages.base.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class FooterComponent extends BasePage {

    private final Locator copyrightText;

    public FooterComponent(Page page) {
        super(page);
        this.copyrightText = page.locator("footer .copyright");
    }

    public String getCopyrightText() {
        return copyrightText.textContent();
    }
}
