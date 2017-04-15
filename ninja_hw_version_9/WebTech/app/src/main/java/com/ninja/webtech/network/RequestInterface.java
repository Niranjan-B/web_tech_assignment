package com.ninja.webtech.network;

import com.ninja.webtech.models.event.Events;
import com.ninja.webtech.models.page.Pages;
import com.ninja.webtech.models.user.Users;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by niranjanb on 13/04/17.
 */

public interface RequestInterface {
    @GET("?")
    Observable<Users> getQueriedUsers(@QueryMap Map<String, String> options);

    @GET
    Observable<Users> getNextPrevData(@Url String url);

    @GET("?")
    Observable<Pages> getQueriedUsersPages(@QueryMap Map<String, String> options);

    @GET
    Observable<Pages> getNextPrevDataPages(@Url String url);

    @GET("?")
    Observable<Events> getQueriedUsersEvents(@QueryMap Map<String, String> options);

    @GET
    Observable<Events> getNextPrevDataEvents(@Url String url);

}
