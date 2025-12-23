package com.carehires.api.endpoints;

public class ApiEndpoints {
    private ApiEndpoints() {
        throw new UnsupportedOperationException("Cannot instantiate endpoints class");
    }

    // Base paths
    public static final String API_V1 = "/api/v1";
    public static final String API_V2 = "/api/v2";

    // Auth endpoints
    public static final String LOGIN = API_V1 + "/auth/login";
    public static final String LOGOUT = API_V1 + "/auth/logout";
    public static final String REGISTER = API_V1 + "/auth/register";
    public static final String REFRESH_TOKEN = API_V1 + "/auth/refresh";

    // User endpoints
    public static final String USERS = API_V1 + "/users";
    public static final String USER_PROFILE = API_V1 + "/users/profile";

    // Other endpoints
    public static final String PRODUCTS = API_V1 + "/products";
    public static final String ORDERS = API_V1 + "/orders";
}
