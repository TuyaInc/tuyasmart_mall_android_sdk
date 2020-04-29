# Integrated Mall Biz Bundle
1. Create Project

    Build your project in the Android Studio and integrate Tuyasmart HomeSDK

2. Configure the root build.gradle

  ``` groovy
  buildscript {
      repositories {
          maven { url 'https://maven-other.tuya.com/repository/maven-public/' }
      }
      dependencies {
          classpath 'com.tuya.android.module:tymodule-config:0.4.0-SNAPSHOT'
      }
  }

  allprojects {
      repositories {
          maven { url 'https://maven-other.tuya.com/repository/maven-public/' }
      }
  }
  ```

3. Configure the module build.gradle

  ``` groovy
	dependencies {
    //require start
    implementation 'com.tuya.smart:tuyasmart:3.15.0-beta3'
    implementation 'com.tuya.smart:tuyasmart-webcontainer:3.17.6r141-open'
    implementation 'com.tuya.smart:tuyasmart-tuyamall-sdk:1.0.2'
    implementation 'com.tuya.smart:optimus:1.0.0'
    annotationProcessor 'com.tuya.smart:optimus-compiler:1.0.0'
    implementation 'com.tuya.smart:tuyasmart-xplatformmanager:1.0.0'
    implementation 'com.tuya.smart:tuyasmart-appshell:3.10.0'
    implementation "com.tuya.smart:tuyasmart-base:3.17.0r139-rc.3"
    implementation 'com.tuya.smart:tuyasmart-stencilwrapper:3.17.0.1r139'
    implementation "com.tuya.smart:tuyasmart-framework:3.17.0.2r139-external"
    implementation 'com.tuya.smart:tuyasmart-uispecs:0.0.5'
    implementation "com.tuya.smart:tuyasmart-picture:3.12.0r123"
    implementation "com.tuya.smart:tuyasmart-rpc:3.12.0r123"
    implementation "com.tuya.smart:tuyasmart-video:3.12.6r125"
    implementation "com.tuya.smart:tuyasmart-ipc-videoview:3.13.0r125-open-SNAPSHOT"
    //require end

    implementation 'com.alibaba:fastjson:1.1.67.android'
    implementation 'org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.0'
    implementation 'com.squareup.okhttp3:okhttp-urlconnection:3.12.3'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.2.9'
    implementation 'com.facebook.fresco:fresco:1.13.0'
    implementation "com.facebook.fresco:imagepipeline-okhttp3:1.3.0"
  	}
  ```

4. Configure the styles

    ``` xml styles.xml
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="app_bg_color">#FFFFFF</item>
    </style>
    ```

5. Configure the progurad

  ``` 
  # Configure all third dependencies progurad rules

  #fastJson
  -keep class com.alibaba.fastjson.**{*;}
  -dontwarn com.alibaba.fastjson.**    
    
  #mqtt
  -keep class com.tuya.smart.mqttclient.mqttv3.** { *; }
  -dontwarn com.tuya.smart.mqttclient.mqttv3.** 

  -keep class com.squareup.okhttp.** { *; }
  -keep interface com.squareup.okhttp.** { *; }
  -dontwarn com.squareup.okhttp.**    

  -keep class okio.** { *; }
  -dontwarn okio.**    
  
  -keep class com.tuya.**{*;}
  -dontwarn com.tuya.**
  ```

6. Init Tuya Smart Mall Biz Bundle in the Application

    ``` java
    public class TuyaSmartApp extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            Fresco.initialize(this);
            TuyaWrapper.init(this);
            TuyaHomeSdk.init(this);
            TuyaOptimusSdk.init(this);
        }
    }
    ```