# 商城可用性

当前用户所在区的商城业务是否可用

**接口说明**

当前用户所在区商城是否可用，此接口为异步方法

``` java
requestSupportMall(ResultListener<Boolean> listener)
```
**参数说明**

| 参数         | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| listener     | 商城可用请求异步回调 |

**示例代码**
``` java
ITuyaMallSdk iTuyaMallSdk = TuyaOptimusSdk.getManager(ITuyaMallSdk.class);
iTuyaMallSdk.requestSupportMall(new Business.ResultListener<Boolean>() {
    @Override
    public void onFailure(BusinessResponse businessResponse, Boolean aBoolean, String s) {
            L.e("requestSupportMall", s);
    }
    @Override
    public void onSuccess(BusinessResponse businessResponse, Boolean aBoolean, String s) {
            //aBoolean = true 表示商城可用
            L.d("requestSupportMall", String.valueOf(aBoolean));
    }
});
```
