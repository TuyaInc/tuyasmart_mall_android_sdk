# Mall service availability

Availability of mall service in the current user's area

**Declaration**

Availability of mall service in the current user's area,This method is an asynchronous method.

``` java
requestSupportMall(ResultListener<Boolean> listener)
```
**Parameter**

| Parameter         | Description                                                         |
| ------------ | ------------------------------------------------------------ |
| listener     |  mall service  availability callbacks |

**Example**
``` java
ITuyaMallSdk iTuyaMallSdk = TuyaOptimusSdk.getManager(ITuyaMallSdk.class);
iTuyaMallSdk.requestSupportMall(new Business.ResultListener<Boolean>() {
    @Override
    public void onFailure(BusinessResponse businessResponse, Boolean aBoolean, String s) {
            L.e("requestSupportMall", s);
    }
    @Override
    public void onSuccess(BusinessResponse businessResponse, Boolean aBoolean, String s) {
            //aBoolean = true mall service is availabe
            L.d("requestSupportMall", String.valueOf(aBoolean));
    }
});
```
