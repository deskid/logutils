package com.github.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.logutils.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.d(getLocalClassName());
    }
}
