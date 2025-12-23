package com.carehires.constants;

public class MessageConstants {
    private MessageConstants() {
        throw new UnsupportedOperationException("Cannot instantiate constants class");
    }

    // Success messages
    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String LOGOUT_SUCCESS = "Logout successful";
    public static final String REGISTRATION_SUCCESS = "Registration successful";
    
    // Error messages
    public static final String LOGIN_FAILED = "Login failed";
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String ELEMENT_NOT_FOUND = "Element not found: %s";
    public static final String PAGE_LOAD_FAILED = "Page failed to load";
    public static final String API_REQUEST_FAILED = "API request failed";
    
    // Validation messages
    public static final String FIELD_REQUIRED = "%s field is required";
    public static final String INVALID_EMAIL = "Invalid email format";
    public static final String PASSWORD_MISMATCH = "Passwords do not match";
    
    // Info messages
    public static final String TEST_STARTED = "Test started: %s";
    public static final String TEST_PASSED = "Test passed: %s";
    public static final String TEST_FAILED = "Test failed: %s";
    public static final String TEST_SKIPPED = "Test skipped: %s";
}
