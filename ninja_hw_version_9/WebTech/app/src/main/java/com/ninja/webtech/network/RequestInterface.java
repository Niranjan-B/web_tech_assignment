package com.ninja.webtech.network;

import com.ninja.webtech.models.user.Users;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by niranjanb on 13/04/17.
 */

public interface RequestInterface {
    @GET("?")
    Observable<Users> getQueriedUsers(@QueryMap Map<String, String> options);

}
