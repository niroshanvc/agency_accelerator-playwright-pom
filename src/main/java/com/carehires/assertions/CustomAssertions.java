package com.carehires.assertions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.*;

public class CustomAssertions {

    private static final Logger logger = LogManager.getLogger(CustomAssertions.class);
    private static final ThreadLocal<SoftAssertions> softAssertions = new ThreadLocal<>();

    public static void initSoftAssertions() {
        softAssertions.set(new SoftAssertions());
        logger.debug("Soft assertions initialized for thread: {}", Thread.currentThread().getId());
    }

    public static SoftAssertions getSoftAssertions() {
        if (softAssertions.get() == null) {
            initSoftAssertions();
        }
        return softAssertions.get();
    }

    public static void assertAllSoft() {
        if (softAssertions.get() != null) {
            logger.info("Asserting all soft assertions");
            softAssertions.get().assertAll();
            softAssertions.remove();
        }
    }

    // String Assertions
    public static void assertEquals(String actual, String expected, String message) {
        logger.info("Asserting equals: expected='{}', actual='{}'", expected, actual);
        assertThat(actual).as(message).isEqualTo(expected);
    }

    public static void assertContains(String actual, String expected, String message) {
        logger.info("Asserting contains: '{}' contains '{}'", actual, expected);
        assertThat(actual).as(message).contains(expected);
    }

    public static void assertStartsWith(String actual, String prefix, String message) {
        logger.info("Asserting starts with: '{}' starts with '{}'", actual, prefix);
        assertThat(actual).as(message).startsWith(prefix);
    }

    public static void assertEndsWith(String actual, String suffix, String message) {
        logger.info("Asserting ends with: '{}' ends with '{}'", actual, suffix);
        assertThat(actual).as(message).endsWith(suffix);
    }

    public static void assertNotEmpty(String actual, String message) {
        logger.info("Asserting not empty");
        assertThat(actual).as(message).isNotEmpty();
    }

    // Boolean Assertions
    public static void assertTrue(boolean condition, String message) {
        logger.info("Asserting true");
        assertThat(condition).as(message).isTrue();
    }

    public static void assertFalse(boolean condition, String message) {
        logger.info("Asserting false");
        assertThat(condition).as(message).isFalse();
    }

    // Number Assertions
    public static void assertGreaterThan(int actual, int expected, String message) {
        logger.info("Asserting {} > {}", actual, expected);
        assertThat(actual).as(message).isGreaterThan(expected);
    }

    public static void assertLessThan(int actual, int expected, String message) {
        logger.info("Asserting {} < {}", actual, expected);
        assertThat(actual).as(message).isLessThan(expected);
    }

    // Object Assertions
    public static void assertNotNull(Object actual, String message) {
        logger.info("Asserting not null");
        assertThat(actual).as(message).isNotNull();
    }

    public static void assertNull(Object actual, String message) {
        logger.info("Asserting null");
        assertThat(actual).as(message).isNull();
    }

    // Collection Assertions
    public static void assertCollectionSize(java.util.Collection collection, int expectedSize, String message) {
        logger.info("Asserting collection size: expected={}, actual={}", expectedSize, collection.size());
        assertThat(collection).as(message).hasSize(expectedSize);
    }

    public static void assertCollectionNotEmpty(java.util.Collection collection, String message) {
        logger.info("Asserting collection not empty");
        assertThat(collection).as(message).isNotEmpty();
    }
}
