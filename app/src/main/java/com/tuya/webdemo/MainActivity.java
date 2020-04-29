package com.tuya.webdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.tuya.smart.android.common.utils.L;
import com.tuya.smart.android.network.Business;
import com.tuya.smart.android.network.http.BusinessResponse;
import com.tuya.smart.android.user.api.ILogoutCallback;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.jsbridge.ITuyaMallSdk;
import com.tuya.smart.jsbridge.base.webview.WebViewActivity;
import com.tuya.smart.optimus.sdk.TuyaOptimusSdk;
import com.tuya.smart.tuyamall.sdk.IQueryMallPageUrlCallback;
import com.tuya.webdemo.base.utils.LoginHelper;

public class MainActivity extends AppCompatActivity {

    private static boolean isActivity;
    private EditText mUrlEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle("Mall-Test");
        toolbar.inflateMenu(R.menu.toolbar_main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_logout) {
                    TuyaHomeSdk.getUserInstance().logout(new ILogoutCallback() {
                        @Override
                        public void onSuccess() {
                            LoginHelper.reLogin(MainActivity.this, false);
                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
                            L.e("MainActivity", "logout: " + errorMsg);
                        }
                    });

                }
                return true;
            }
        });
        final SwitchCompat switchCompat = findViewById(R.id.container_switch);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isActivity = isChecked;
                switchCompat.setText(isActivity ? "open by WebViewActivity" : "open by WebViewFragment");
            }
        });
        mUrlEdit = findViewById(R.id.input_web);
        mUrlEdit.setSelection(mUrlEdit.getText().length());
        findViewById(R.id.openWeb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mUrlEdit.getText().toString();
                if (!TextUtils.isEmpty(url)) {
                    openWeb(MainActivity.this, url);
                }
            }
        });
        ITuyaMallSdk iTuyaMallSdk = TuyaOptimusSdk.getManager(ITuyaMallSdk.class);

        getMallOrder(iTuyaMallSdk);

        getHomePageUrl(iTuyaMallSdk);

        getMallEnable(iTuyaMallSdk);
    }

    /**
     * check mall service is enable
     *
     * @param iTuyaMallSdk
     */
    private void getMallEnable(ITuyaMallSdk iTuyaMallSdk) {
        iTuyaMallSdk.requestSupportMall(new Business.ResultListener<Boolean>() {
            @Override
            public void onFailure(BusinessResponse businessResponse, Boolean aBoolean, String s) {
                L.e("requestSupportMall", s);
            }

            @Override
            public void onSuccess(BusinessResponse businessResponse, Boolean aBoolean, String s) {
                L.d("requestSupportMall", String.valueOf(aBoolean));
            }
        });
    }

    /**
     * get mall home url
     *
     * @param iTuyaMallSdk
     */
    private void getHomePageUrl(ITuyaMallSdk iTuyaMallSdk) {
        iTuyaMallSdk.requestHomePageUrl(new IQueryMallPageUrlCallback() {
            @Override
            public void onSuccess(String domain) {
                mUrlEdit.setText(domain);
                L.d("requestHomePageUrl", domain);
            }

            @Override
            public void onError(String code, String error) {
                L.e("requestHomePageUrl", error);
            }
        });
    }

    /**
     * get mall order url
     *
     * @param iTuyaMallSdk
     */
    private void getMallOrder(ITuyaMallSdk iTuyaMallSdk) {
        iTuyaMallSdk.requestUserCenterPageUrl(new IQueryMallPageUrlCallback() {
            @Override
            public void onSuccess(String domain) {
                L.d("requestUserCenterPageUrl", domain);
            }

            @Override
            public void onError(String code, String error) {
                L.e("requestUserCenterPageUrl", error);
            }
        });
    }

    private void openWeb(Context context, String url) {
        Intent intent = new Intent(context, isActivity ? WebViewActivity.class : WebActivity.class);
        intent.putExtra("Uri", url);
        context.startActivity(intent);
    }
}
