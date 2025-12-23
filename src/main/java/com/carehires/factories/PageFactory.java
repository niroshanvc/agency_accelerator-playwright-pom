package com.carehires.factories;

import com.carehires.managers.DriverManager;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Constructor;

public class PageFactory {

    private static final Logger logger = LogManager.getLogger(PageFactory.class);

    public static <T> T createInstance(Class<T> pageClass) {
        try {
            Page page = DriverManager.getPage();
            Constructor<T> constructor = pageClass.getDeclaredConstructor(Page.class);
            T instance = constructor.newInstance(page);
            logger.info("Created page object instance: {}", pageClass.getSimpleName());
            return instance;
        } catch (Exception e) {
            logger.error("Failed to create page object instance: {}", pageClass.getSimpleName(), e);
            throw new RuntimeException("Failed to create page object: " + pageClass.getName(), e);
        }
    }

    // FIX: Add generic type declaration <T>
    public static <T> T createInstance(Class<T> pageClass, Page page) {
        try {
            Constructor<T> constructor = pageClass.getDeclaredConstructor(Page.class);
            T instance = constructor.newInstance(page);
            logger.info("Created page object instance with custom page: {}", pageClass.getSimpleName());
            return instance;
        } catch (Exception e) {
            logger.error("Failed to create page object instance: {}", pageClass.getSimpleName(), e);
            throw new RuntimeException("Failed to create page object: " + pageClass.getName(), e);
        }
    }
}
