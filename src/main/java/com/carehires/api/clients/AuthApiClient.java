package com.carehires.api.clients;

import com.carehires.api.endpoints.ApiEndpoints;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

import java.util.HashMap;
import java.util.Map;

public class AuthApiClient extends BaseApiClient {

    public AuthApiClient(APIRequestContext requestContext) {
        super(requestContext);
    }

    public APIResponse login(String username, String password) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", username);
        body.put("password", password);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return post(ApiEndpoints.LOGIN, body, headers);
    }
}
