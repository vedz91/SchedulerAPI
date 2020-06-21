package com.hubspot.testapi.core.retrofit;

public class RetrofitConfiguration {
    String url;

    public RetrofitConfiguration() {
    }

    public RetrofitConfiguration(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
