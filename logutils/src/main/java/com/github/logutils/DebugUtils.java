package com.github.logutils;

import android.content.Context;

import java.lang.reflect.Field;

public class DebugUtils {
    private static Boolean sDebug;
    private static Context sContexct;

    private DebugUtils() {
    }

    public static void setApplicationContext(Context context) {
        sContexct = context;
    }

    private static Context getApplicationContext() {
        if (sContexct == null) {
            throw new RuntimeException("call DebugUtils setApplicationContext on Application onCreate()");
        }
        return sContexct;
    }

    /**
     * @return boolean
     * BuildConfig.DEBUG will always return false in library projects
     * see https://code.google.com/p/android/issues/detail?id=52962
     */
    static boolean isDebug() {
        if (sDebug == null) {
            try {
                final String packageName = getApplicationContext().getPackageName();
                final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
                final Field DEBUG = buildConfig.getField("DEBUG");
                DEBUG.setAccessible(true);
                sDebug = DEBUG.getBoolean(null);
            } catch (final Throwable t) {
                sDebug = false;
            }
        }
        return sDebug;
    }
}
