package com.hubspot.testapi.core.retrofit;

import com.hubspot.testapi.core.exception.HubspotAPIException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class RetrofitClient<T> {

    final static Logger LOGGER = LoggerFactory.getLogger(RetrofitClient.class);

    final static OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    if (response.code() != 200) {
                        throw new HubspotAPIException(String.format("Exception from Hubspot API: %s", response.message()),
                                javax.ws.rs.core.Response.Status.fromStatusCode(response.code()));
                    }
                    return response;
                }
            })
            .build();

    final Class<T> apiType;

    public RetrofitClient(final Class<T> apiType) {
        this.apiType = apiType;
    }

    public static <T> RetrofitClient<T> retrofitClient(final Class<T> apiType) {
        return new RetrofitClient<>(apiType);
    }

    public T get(final RetrofitConfiguration configuration) {
        LOGGER.info("[Retrofit Configuration] URL: " + configuration.getUrl());
        return new Retrofit.Builder()
                .baseUrl(configuration.getUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(HTTP_CLIENT)
                .build()
                .create(this.apiType);
    }
}
