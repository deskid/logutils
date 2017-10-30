package com.github.logutils;

import android.content.Context;
import android.util.Log;

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
    public static boolean isDebug() {
        if (sDebug == null) {
            final String packageName = getApplicationContext().getPackageName();
            try {
                final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
                final Field DEBUG = buildConfig.getField("DEBUG");
                DEBUG.setAccessible(true);
                sDebug = DEBUG.getBoolean(null);
                Log.d("DebugUtils", "packageName : " + packageName + "; BuildConfig.DEBUG : " + sDebug);
            } catch (final Throwable t) {
                Log.d("DebugUtils", "packageName invalid : " + packageName + "; BuildConfig.DEBUG : " + sDebug);
                sDebug = false;
            }
        }
        return sDebug;
    }
}
