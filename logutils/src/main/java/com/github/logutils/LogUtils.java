package com.github.logutils;

import android.util.Log;

public class LogUtils {
    private static final String TAG = "Log";

    private LogUtils() {
    }

    private static String logWithMethodAndLineNumber(String log, StackTraceElement element) {
        String className = element.getFileName();
        String methodName = element.getMethodName();
        int lineNumber = element.getLineNumber();
        String fileName = element.getFileName();

        StringBuilder stringBuilder = new StringBuilder(log);
        stringBuilder.append(" at ")
                .append(className)
                .append(".")
                .append(methodName)
                .append(" (")
                .append(fileName)
                .append(":")
                .append(lineNumber)
                .append(")  ");

        return stringBuilder.toString();
    }

    private static StackTraceElement getTargetStackTraceElement() {
        StackTraceElement targetStackTrace = null;
        boolean shouldTrace = false;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            boolean isLogMethod = stackTraceElement.getClassName().equals(LogUtils.class.getName());
            if (shouldTrace && !isLogMethod) {
                targetStackTrace = stackTraceElement;
                break;
            }
            shouldTrace = isLogMethod;
        }
        return targetStackTrace;
    }

    public static void d(String tag, String msg) {
        if (DebugUtils.isDebug()) {
            Log.d(tag, logWithMethodAndLineNumber(msg, getTargetStackTraceElement()));
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, logWithMethodAndLineNumber(msg, getTargetStackTraceElement()));
    }

    public static void e(String tag, String msg, Throwable tr) {
        Log.e(tag, logWithMethodAndLineNumber(msg, getTargetStackTraceElement()), tr);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String msg, Throwable tr) {
        e(TAG, msg, tr);
    }

    public static void logStackTrace(Throwable throwable) {
        if (DebugUtils.isDebug() && throwable != null) {
            throwable.printStackTrace();
        }
    }

}
