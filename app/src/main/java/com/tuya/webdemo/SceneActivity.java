package com.tuya.webdemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tuya.smart.android.common.utils.L;
import com.tuya.smart.android.user.api.ILogoutCallback;
import com.tuya.smart.api.MicroContext;
import com.tuya.smart.demo_login.base.utils.LoginHelper;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.bean.scene.SceneBean;
import com.tuya.smart.home.sdk.callback.ITuyaGetHomeListCallback;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.tuya.smart.home.sdk.callback.ITuyaResultCallback;
import com.tuya.smart.scene.business.api.ITuyaSceneBusinessService;
import com.tuya.smart.utils.ToastUtil;

import java.util.List;

public class SceneActivity extends AppCompatActivity implements View.OnClickListener {

    private View mAddScene;
    private View mEditScene;
    private View mSetLocation;
    private ITuyaSceneBusinessService iTuyaSceneBusinessService;
    public static long HOME_ID;
    private static final int ADD_SCENE_REQUEST_CODE = 1001;
    private static final int EDIT_SCENE_REQUEST_CODE = 1002;
    private View mSetMap;
    private View mSaveMapData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.toolbar_main_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_logout) {
                    TuyaHomeSdk.getUserInstance().logout(new ILogoutCallback() {
                        @Override
                        public void onSuccess() {
                            LoginHelper.reLogin(SceneActivity.this, false);
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


        mAddScene = findViewById(R.id.add_scene);
        mSetLocation = findViewById(R.id.set_location);
        mEditScene = findViewById(R.id.edit_scene);
        mSetMap = findViewById(R.id.set_map);
        mSaveMapData = findViewById(R.id.save_map_data);
        mSetLocation.setOnClickListener(this);
        mAddScene.setOnClickListener(this);
        mEditScene.setOnClickListener(this);
        mSetMap.setOnClickListener(this);
        mSaveMapData.setOnClickListener(this);
        //获取场景业务包服务
        iTuyaSceneBusinessService = MicroContext.findServiceByInterface(ITuyaSceneBusinessService.class.getName());
        //获取家庭信息
        getDataFromServer();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_location:
                setLocation();
                break;
            case R.id.add_scene:
                addScene();
                break;
            case R.id.edit_scene:
                editScene();
                break;
            case R.id.set_map:
                setMapClass();
                break;
            case R.id.save_map_data:
                saveMapData();
                break;
            default:break;
        }
    }

    private void editScene() {
        if(HOME_ID == 0) return;
        TuyaHomeSdk.getSceneManagerInstance().getSceneList(HOME_ID, new ITuyaResultCallback<List<SceneBean>>() {
            @Override
            public void onSuccess(List<SceneBean> result) {
                if(!result.isEmpty()){
                    SceneBean sceneBean = result.get(0);
                    if(null != iTuyaSceneBusinessService){
                        iTuyaSceneBusinessService.editScene(SceneActivity.this, HOME_ID, sceneBean, EDIT_SCENE_REQUEST_CODE);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMessage) {

            }
        });

    }

    private void addScene() {
        if(null != iTuyaSceneBusinessService && HOME_ID != 0){
            iTuyaSceneBusinessService.addScene(this, HOME_ID, ADD_SCENE_REQUEST_CODE);
        }
    }




    /**
     * 设置app经纬度,经纬度由业务app接入的地图sdk决定
     */
    private void setLocation() {
        double lng = 120.06420814321443;
        double lat = 30.302782241301667;
        if(null != iTuyaSceneBusinessService){
            iTuyaSceneBusinessService.setAppLocation(lng, lat);
        }
    }


    /**
     * 场景条件中的地理位置，在国外账号需要设置地图，不设置默认走获取国内城市接口
     */
    private void setMapClass() {
        if(null != iTuyaSceneBusinessService){
            //TODO 业务方地图Activity
            iTuyaSceneBusinessService.setMapActivity(null);
        }
    }

    /**
     * 设置地图业务类后，可以用此接口将地图数据传给场景相关业务类
     */
    private void saveMapData() {
        if(null != iTuyaSceneBusinessService){
            //TODO 地图数据保存
            double lng = 120.06420814321443;
            double lat = 30.302782241301667;
            String city = "hangzhou";
            String address = "address";
            iTuyaSceneBusinessService.saveMapData(lng, lat, city, address);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ADD_SCENE_REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK){
                    onAddSuc(data);
                }
                break;
            case EDIT_SCENE_REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK){
                    onEditSuc(data);
                }
                break;
            default:break;
        }
    }

    private void onEditSuc(Intent data) {
        SceneBean sceneBean = (SceneBean) data.getSerializableExtra("sceneBean");
        if(null != sceneBean){
            ToastUtil.shortToast(this, "场景："+sceneBean.getName()+"编辑成功!");
        }
    }

    private void onAddSuc(Intent data) {
        SceneBean sceneBean = (SceneBean) data.getSerializableExtra("sceneBean");
        if(null != sceneBean){
            ToastUtil.shortToast(this, "场景："+sceneBean.getName()+"创建成功!");
        }
    }

    public void getDataFromServer() {
        TuyaHomeSdk.getHomeManagerInstance().queryHomeList(new ITuyaGetHomeListCallback() {
            @Override
            public void onSuccess(List<HomeBean> homeBeans) {
                if (homeBeans.size() == 0) {
                    ToastUtil.shortToast(SceneActivity.this, "请先在SDK Demo中创建家庭");
                    return;
                }
                final long homeId = homeBeans.get(0).getHomeId();
                HOME_ID = homeId;
                TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(new ITuyaHomeResultCallback() {
                    @Override
                    public void onSuccess(HomeBean bean) {

                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {

                    }
                });

            }

            @Override
            public void onError(String errorCode, String error) {
                TuyaHomeSdk.newHomeInstance(HOME_ID).getHomeLocalCache(new ITuyaHomeResultCallback() {
                    @Override
                    public void onSuccess(HomeBean bean) {
                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {

                    }
                });
            }
        });
    }


}
