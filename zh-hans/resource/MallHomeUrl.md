# 商城首页 URL

若商城可用时，可获取用户所在区商城首页 URL

**接口说明**

 获取用户所在区商城首页 URL，此接口为异步接口

``` java
requestHomePageUrl(IQueryMallPageUrlCallback callback)
    ```
**参数说明**

| 参数                          | 说明                            |
| ----------------------------- | ------------------------------- |
| IQueryMallPageUrlCallback | 商城首页请求异步回调 |

**示例代码**
``` java
ITuyaMallSdk iTuyaMallSdk = TuyaOptimusSdk.getManager(ITuyaMallSdk.class);
iTuyaMallSdk.requestHomePageUrl(new IQueryMallPageUrlCallback() {
    @Override
    public void onSuccess(String domain) {
        L.d("requestHomePageUrl", domain);
    }
    @Override
    public void onError(String code, String error) {
        L.e("requestHomePageUrl", error);
    }
});
```
