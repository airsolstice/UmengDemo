package com.admin.umengdemo;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2016/10/25 0025.
 */
public class UmengManager {

    private Context mContext;

    public UmengManager(Context mContext) {
        this.mContext = mContext;
    }

    public void onEvent(String key){
        MobclickAgent.onEvent(mContext, key);
    }

    public void onKillProcess(){
        MobclickAgent.onKillProcess(mContext);
    }

    public void initUmeng(){
        MobclickAgent.setDebugMode(true);
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);
        // MobclickAgent.setAutoLocation(true);
        // MobclickAgent.setSessionContinueMillis(1000);
        // MobclickAgent.startWithConfigure(
        // new UMAnalyticsConfig(mContext, "4f83c5d852701564c0000011", "Umeng",
        // EScenarioType.E_UM_NORMAL));
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }

    public void onResume(String TAG) {
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(mContext);
    }

    public void onPause(String TAG) {
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(mContext);
    }

}
