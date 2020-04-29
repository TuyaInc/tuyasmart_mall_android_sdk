# 商城订单 URL

若商城可用时，可获取用户所在区商城订单 URL

**接口说明**

 获取用户所在区商城订单 URL，此接口为异步接口

``` java
requestUserCenterPageUrl(IQueryMallPageUrlCallback callback)
```
**参数说明**

| 参数                 | 说明                     |
| -------------------- | ------------------------ |
| IQueryMallPageUrlCallback | 商城订单请求异步回调 |

**示例代码**
``` java
ITuyaMallSdk iTuyaMallSdk = TuyaOptimusSdk.getManager(ITuyaMallSdk.class);
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
```