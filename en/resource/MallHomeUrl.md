# Mall Home URL

If the mall service is available, you can get the URL of the home page of the mall in user's area

**Declaration**

Get the URL of the home page of the mall in user's area,This method is an asynchronous method.

``` java
requestHomePageUrl(IQueryMallPageUrlCallback callback)
    ```
**Parameter**

| Parameter                          | Description                            |
| ----------------------------- | ------------------------------- |
| IQueryMallPageUrlCallback | mall home url callbacks |

**Example**
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
