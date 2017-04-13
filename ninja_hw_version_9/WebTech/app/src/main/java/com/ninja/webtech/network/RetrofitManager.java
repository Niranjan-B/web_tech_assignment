package com.ninja.webtech.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by niranjanb on 13/04/17.
 */

public class RetrofitManager {
    private static RequestInterface requestInterface = new Retrofit.Builder()
            .baseUrl("https://20170412t140905-dot-anaadyanta2k16.appspot.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RequestInterface.class);

    private RetrofitManager() {
    }

    public static RequestInterface getRetrofitInstance() {
        return requestInterface;
    }

}
