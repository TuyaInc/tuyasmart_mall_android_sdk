package com.tuya.webdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


/**
 * create by nielev on 2020/6/30
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.toolbar_main_menu);
        findViewById(R.id.mall_demo).setOnClickListener(this);
        findViewById(R.id.scene_demo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mall_demo:
                Intent intent = new Intent(this, MallActivity.class);
                startActivity(intent);
                break;
            case R.id.scene_demo:
                intent = new Intent(this, SceneActivity.class);
                startActivity(intent);
                break;
        }
    }
}
