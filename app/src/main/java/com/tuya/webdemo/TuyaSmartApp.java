package com.tuya.webdemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.soloader.SoLoader;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.litho.mist.api.MistCore;
import com.tuya.smart.litho.mist.config.MistConfig;
import com.tuya.smart.optimus.sdk.TuyaOptimusSdk;
import com.tuya.smart.sdk.TuyaSdk;
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
        initMistLitho();
    }

    private void initMistLitho() {
        SoLoader.init(this, false);
        MistConfig config = new MistConfig();
        config.create();
        MistCore.getInstance().init(config, this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
