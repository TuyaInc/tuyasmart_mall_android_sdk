# Mall Order URL

If the mall service is available, you can get the URL of the order of the mall in user's area

**Declaration**

get the URL of the order of the mall in user's area,This method is an asynchronous method.

``` java
requestUserCenterPageUrl(IQueryMallPageUrlCallback callback)
```
**Parameter**

| Parameter                 | Description                     |
| -------------------- | ------------------------ |
| IQueryMallPageUrlCallback | mall order url callbacks |

**Example**
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