package com.carehires.constants;

public class TimeoutConstants {
    private TimeoutConstants() {
        throw new UnsupportedOperationException("Cannot instantiate constants class");
    }

    public static final int EXPLICIT_WAIT = 30;
    public static final int PAGE_LOAD_TIMEOUT = 60;
    public static final int IMPLICIT_WAIT = 10;
    public static final int SHORT_WAIT = 5;
    public static final int MEDIUM_WAIT = 15;
    public static final int LONG_WAIT = 30;
    public static final int API_TIMEOUT = 30;
    
    // In milliseconds
    public static final int EXPLICIT_WAIT_MS = EXPLICIT_WAIT * 1000;
    public static final int PAGE_LOAD_TIMEOUT_MS = PAGE_LOAD_TIMEOUT * 1000;
    public static final int IMPLICIT_WAIT_MS = IMPLICIT_WAIT * 1000;
    public static final int SHORT_WAIT_MS = SHORT_WAIT * 1000;
    public static final int MEDIUM_WAIT_MS = MEDIUM_WAIT * 1000;
    public static final int LONG_WAIT_MS = LONG_WAIT * 1000;
    public static final int API_TIMEOUT_MS = API_TIMEOUT * 1000;
}
