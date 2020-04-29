package com.tuya.webdemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.optimus.sdk.TuyaOptimusSdk;
import com.tuya.smart.wrapper.api.TuyaWrapper;

public class TuyaSmartApp extends Application {

    private static TuyaSmartApp mInstance;

    public static TuyaSmartApp getAppContext() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init() {
        Fresco.initialize(this);

        TuyaWrapper.init(this);
        TuyaHomeSdk.init(this);
        TuyaOptimusSdk.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
