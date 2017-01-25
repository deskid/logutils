package com.github.demo;

import android.app.Application;

import com.github.logutils.DebugUtils;

/**
 * Created by zhou on 1/25/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DebugUtils.setApplicationContext(this);
    }
}
