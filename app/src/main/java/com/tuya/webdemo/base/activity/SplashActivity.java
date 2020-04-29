package com.tuya.webdemo.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.tuya.smart.android.common.utils.L;
import com.tuya.smart.android.common.utils.TuyaUtil;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.utils.DialogUtil;
import com.tuya.webdemo.base.utils.ActivityUtils;
import com.tuya.webdemo.base.utils.LoginHelper;
import com.tuya.webdemo.login.activity.LoginActivity;


/**
 * Created by letian on 16/7/19.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("splash", "tuyaTime: " + TuyaUtil.formatDate(System.currentTimeMillis(), "yyyy-mm-dd hh:mm:ss"));

        gotoLogin();
    }

    public void gotoLogin() {
        if (TuyaHomeSdk.getUserInstance().isLogin()) {//已登录，跳主页
            LoginHelper.afterLogin();
            ActivityUtils.gotoMainActivity(this);
        } else {
            ActivityUtils.gotoActivity(this, LoginActivity.class, ActivityUtils.ANIMATE_FORWARD, true);
        }
    }


    private void showTipDialog() {
        DialogUtil.simpleConfirmDialog(this, "appkey or appsecret is empty. \nPlease check your configuration", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
    }

    public static String getInfo(String infoName, Context context) {
        ApplicationInfo e;
        try {
            e = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            return e.metaData.getString(infoName);
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        return "";
    }

}
