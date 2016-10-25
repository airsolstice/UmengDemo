package com.admin.umengdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/10/25 0025.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = getTag();
    protected abstract String getTag();
    protected Context mContext ;
    protected int layoutId = getLayoutId();
    protected abstract int getLayoutId();
    public abstract void click(View v);
    protected UmengManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        mContext = this;
        mManager = new UmengManager(mContext);
        mManager.initUmeng();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            hook();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    // /对于好多应用，会在程序中杀死 进程，这样会导致我们统计不到此时Activity结束的信息，
    // /对于这种情况需要调用 'MobclickAgent.onKillProcess( Context )'
    // /方法，保存一些页面调用的数据。正常的应用是不需要调用此方法的。
    private void hook() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("退出应用", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mManager.onKillProcess();
                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
            }
        });
        builder.setNeutralButton("后退一下", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });
        builder.setNegativeButton("点错了", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        builder.show();
    }


    public void onResume() {
        super.onResume();
        mManager.onResume(TAG);
    }

    public void onPause() {
        super.onPause();
        mManager.onPause(TAG);
    }


}
