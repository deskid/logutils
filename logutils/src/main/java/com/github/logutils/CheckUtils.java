package com.github.logutils;

/**
 * Created by zhou on 2/20/17.
 */
public class CheckUtils {

    private CheckUtils() {
    }

    public static void checkAssert(boolean expression, String failedMessage) {
        if (!expression) {
            throwExceptionIfDebug(new AssertionError(failedMessage));
        }
    }

    public static void throwExceptionIfDebug(RuntimeException ex) {
        if (DebugUtils.isDebug() && ex != null) {
            throw ex;
        }
    }

    public static void throwExceptionIfDebug(Error error) {
        if (DebugUtils.isDebug()) {
            throw error;
        }
    }

}
