package com.carehires.assertions;

import com.microsoft.playwright.APIResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.assertj.core.api.Assertions.*;


public class ApiAssertions {

    private static final Logger logger = LogManager.getLogger(ApiAssertions.class);

    public static void assertStatusCode(APIResponse response, int expectedStatusCode) {
        logger.info("Asserting status code: expected={}, actual={}", 
            expectedStatusCode, response.status());
        assertThat(response.status())
            .as("Status code mismatch")
            .isEqualTo(expectedStatusCode);
    }

    public static void assertStatusCodeOk(APIResponse response) {
        assertStatusCode(response, 200);
    }

    public static void assertStatusCodeCreated(APIResponse response) {
        assertStatusCode(response, 201);
    }

    public static void assertStatusCodeNoContent(APIResponse response) {
        assertStatusCode(response, 204);
    }

    public static void assertStatusCodeBadRequest(APIResponse response) {
        assertStatusCode(response, 400);
    }

    public static void assertStatusCodeUnauthorized(APIResponse response) {
        assertStatusCode(response, 401);
    }

    public static void assertStatusCodeForbidden(APIResponse response) {
        assertStatusCode(response, 403);
    }

    public static void assertStatusCodeNotFound(APIResponse response) {
        assertStatusCode(response, 404);
    }

    public static void assertStatusCodeInternalServerError(APIResponse response) {
        assertStatusCode(response, 500);
    }

    public static void assertSuccessResponse(APIResponse response) {
        logger.info("Asserting success response: status={}", response.status());
        assertThat(response.status())
            .as("Expected success status code (2xx)")
            .isBetween(200, 299);
    }

    public static void assertResponseBodyContains(APIResponse response, String expectedText) {
        String body = new String(response.body());
        logger.info("Asserting response body contains: '{}'", expectedText);
        assertThat(body)
            .as("Response body should contain: " + expectedText)
            .contains(expectedText);
    }

    public static void assertResponseBodyNotEmpty(APIResponse response) {
        String body = new String(response.body());
        logger.info("Asserting response body is not empty");
        assertThat(body)
            .as("Response body should not be empty")
            .isNotEmpty();
    }

    public static void assertResponseHeaderExists(APIResponse response, String headerName) {
        logger.info("Asserting header exists: {}", headerName);
        assertThat(response.headers())
            .as("Header should exist: " + headerName)
            .containsKey(headerName);
    }

    public static void assertResponseHeaderValue(APIResponse response, String headerName, String expectedValue) {
        logger.info("Asserting header value: {}={}", headerName, expectedValue);
        assertThat(response.headers().get(headerName))
            .as("Header value mismatch for: " + headerName)
            .isEqualTo(expectedValue);
    }

    public static void assertContentType(APIResponse response, String expectedContentType) {
        String contentType = response.headers().get("content-type");
        logger.info("Asserting content type: expected='{}', actual='{}'", 
            expectedContentType, contentType);
        assertThat(contentType)
            .as("Content-Type mismatch")
            .contains(expectedContentType);
    }

    public static void assertContentTypeJson(APIResponse response) {
        assertContentType(response, "application/json");
    }

    public static void assertResponseTimeWithin(long actualTime, long maxTime) {
        logger.info("Asserting response time: actual={}ms, max={}ms", actualTime, maxTime);
        assertThat(actualTime)
            .as("Response time exceeded maximum")
            .isLessThanOrEqualTo(maxTime);
    }
}
