package com.carehires.api.clients;

import com.carehires.api.endpoints.ApiEndpoints;
import com.carehires.api.models.request.CreateUserRequest;
import com.carehires.api.models.response.UserResponse;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;

import java.util.HashMap;
import java.util.Map;

public class UserApiClient extends BaseApiClient {

    public UserApiClient(APIRequestContext requestContext) {
        super(requestContext);
    }

    public APIResponse createUser(CreateUserRequest request) {
        logger.info("Creating user: {}", request.getEmail());
        return post(ApiEndpoints.USERS, request);
    }

    public APIResponse getUser(String userId) {
        logger.info("Getting user: {}", userId);
        return get(ApiEndpoints.USERS + "/" + userId);
    }

    public APIResponse getAllUsers() {
        logger.info("Getting all users");
        return get(ApiEndpoints.USERS);
    }

    public APIResponse getUsersWithPagination(int page, int perPage) {
        logger.info("Getting users with pagination: page={}, perPage={}", page, perPage);
        Map params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", String.valueOf(perPage));
        return get(ApiEndpoints.USERS, params);
    }

    public APIResponse updateUser(String userId, CreateUserRequest request) {
        logger.info("Updating user: {}", userId);
        return put(ApiEndpoints.USERS + "/" + userId, request);
    }

    public APIResponse deleteUser(String userId) {
        logger.info("Deleting user: {}", userId);
        return delete(ApiEndpoints.USERS + "/" + userId);
    }

    public UserResponse getUserAsObject(String userId) {
        APIResponse response = getUser(userId);
        return getResponseBodyAs(response, UserResponse.class);
    }
}
