package com.carehires.api.clients;

import com.carehires.utils.JsonUtils;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseApiClient {

    protected final APIRequestContext requestContext;
    protected final Logger logger;
    protected Map<String, String> headers;

    protected BaseApiClient(APIRequestContext requestContext) {
        this.requestContext = requestContext;
        this.logger = LogManager.getLogger(this.getClass());
        this.headers = new HashMap<>();
        setDefaultHeaders();
    }

    private void setDefaultHeaders() {
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
    }

    protected void addHeader(String key, String value) {
        headers.put(key, value);
    }

    protected void setAuthToken(String token) {
        headers.put("Authorization", "Bearer " + token);
        logger.info("Auth token set");
    }

    protected APIResponse get(String endpoint) {
        logger.info("GET request to: {}", endpoint);
        try {
            // FIX: Use setHeader() for each header instead of setHeaders()
            RequestOptions options = RequestOptions.create();
            headers.forEach((key, value) -> options.setHeader(key, value));

            APIResponse response = requestContext.get(endpoint, options);
            logResponse(response);
            return response;
        } catch (Exception e) {
            logger.error("GET request failed: {}", endpoint, e);
            throw new RuntimeException("GET request failed: " + endpoint, e);
        }
    }

    protected APIResponse get(String endpoint, Map<String, String> queryParams) {
        logger.info("GET request to: {} with params: {}", endpoint, queryParams);
        try {
            String url = buildUrlWithParams(endpoint, queryParams);

            RequestOptions options = RequestOptions.create();
            headers.forEach((key, value) -> options.setHeader(key, value));

            APIResponse response = requestContext.get(url, options);
            logResponse(response);
            return response;
        } catch (Exception e) {
            logger.error("GET request failed: {}", endpoint, e);
            throw new RuntimeException("GET request failed: " + endpoint, e);
        }
    }

    protected APIResponse post(String endpoint, Object body) {
        return post(endpoint, body, null);
    }

    protected APIResponse post(String endpoint, Object body, Map<String, String> customHeaders) {
        logger.info("POST request to: {}", endpoint);
        try {
            String jsonBody = body instanceof String ? (String) body : JsonUtils.toJson(body);
            logger.debug("Request body: {}", jsonBody);

            RequestOptions options = RequestOptions.create();
            // Add default/instance headers
            headers.forEach((key, value) -> options.setHeader(key, value));

            // Add/Override with custom headers
            if (customHeaders != null) {
                customHeaders.forEach((key, value) -> options.setHeader(key, value));
            }

            options.setData(jsonBody);

            APIResponse response = requestContext.post(endpoint, options);
            logResponse(response);
            return response;
        } catch (Exception e) {
            logger.error("POST request failed: {}", endpoint, e);
            throw new RuntimeException("POST request failed: " + endpoint, e);
        }
    }

    protected APIResponse put(String endpoint, Object body) {
        logger.info("PUT request to: {}", endpoint);
        try {
            String jsonBody = body instanceof String ? (String) body : JsonUtils.toJson(body);
            logger.debug("Request body: {}", jsonBody);

            RequestOptions options = RequestOptions.create();
            headers.forEach((key, value) -> options.setHeader(key, value));
            options.setData(jsonBody);

            APIResponse response = requestContext.put(endpoint, options);
            logResponse(response);
            return response;
        } catch (Exception e) {
            logger.error("PUT request failed: {}", endpoint, e);
            throw new RuntimeException("PUT request failed: " + endpoint, e);
        }
    }

    protected APIResponse patch(String endpoint, Object body) {
        logger.info("PATCH request to: {}", endpoint);
        try {
            String jsonBody = body instanceof String ? (String) body : JsonUtils.toJson(body);
            logger.debug("Request body: {}", jsonBody);

            RequestOptions options = RequestOptions.create();
            headers.forEach((key, value) -> options.setHeader(key, value));
            options.setData(jsonBody);

            APIResponse response = requestContext.patch(endpoint, options);
            logResponse(response);
            return response;
        } catch (Exception e) {
            logger.error("PATCH request failed: {}", endpoint, e);
            throw new RuntimeException("PATCH request failed: " + endpoint, e);
        }
    }

    protected APIResponse delete(String endpoint) {
        logger.info("DELETE request to: {}", endpoint);
        try {
            RequestOptions options = RequestOptions.create();
            headers.forEach((key, value) -> options.setHeader(key, value));

            APIResponse response = requestContext.delete(endpoint, options);
            logResponse(response);
            return response;
        } catch (Exception e) {
            logger.error("DELETE request failed: {}", endpoint, e);
            throw new RuntimeException("DELETE request failed: " + endpoint, e);
        }
    }

    protected String getResponseBody(APIResponse response) {
        try {
            return new String(response.body());
        } catch (Exception e) {
            logger.error("Failed to get response body", e);
            return "";
        }
    }

    protected <T> T getResponseBodyAs(APIResponse response, Class<T> clazz) {
        String body = getResponseBody(response);
        return JsonUtils.fromJson(body, clazz);
    }

    protected int getStatusCode(APIResponse response) {
        return response.status();
    }

    protected Map<String, String> getResponseHeaders(APIResponse response) {
        return response.headers();
    }

    protected boolean isSuccessResponse(APIResponse response) {
        int status = response.status();
        return status >= 200 && status < 300;
    }

    private void logResponse(APIResponse response) {
        logger.info("Response status: {}", response.status());
        logger.debug("Response body: {}", getResponseBody(response));
    }

    private String buildUrlWithParams(String endpoint, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return endpoint;
        }

        StringBuilder url = new StringBuilder(endpoint);
        url.append("?");
        params.forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
        return url.substring(0, url.length() - 1);
    }
}