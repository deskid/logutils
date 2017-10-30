package com.github.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.logutils.CheckUtils;
import com.github.logutils.DebugUtils;
import com.github.logutils.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (DebugUtils.isDebug()) {
            Log.d("TAG", "in debug mode");
        }
        CheckUtils.checkAssert(1 + 1 == 2, "wtf");
//        CheckUtils.throwExceptionIfDebug(new IllegalArgumentException("string"));
        LogUtils.d(getLocalClassName());
    }
}
