package com.admin.umengdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected String getTag() {
        return "MainActivity";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void click(View v) {
        String tag = "";

        switch (v.getId()){
            case R.id.btn1:
                Log.e(TAG,"click one");
                tag = "click";
                break;
            case R.id.btn2:
                Log.e(TAG,"click two");
                tag = "click1";
                break;

        }

        mManager.onEvent(tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
