package com.ninja.webtech.network;

import com.ninja.webtech.models.album.Albums;
import com.ninja.webtech.models.event.Events;
import com.ninja.webtech.models.group.Groups;
import com.ninja.webtech.models.page.Pages;
import com.ninja.webtech.models.place.Places;
import com.ninja.webtech.models.post.Posts;
import com.ninja.webtech.models.user.Users;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
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

    @GET("?")
    Observable<Places> getQueriedPlaces(@QueryMap Map<String, String> options);

    @GET
    Observable<Places> getNextPrevDataPlaces(@Url String url);

    @GET("?")
    Observable<Groups> getQueriedGroups(@QueryMap Map<String, String> options);

    @GET
    Observable<Groups> getNextPrevDataGroups(@Url String url);

    @GET("?")
    Observable<Albums> getAlbums(@QueryMap Map<String, String> options);

    @GET("?")
    Observable<Posts> getPosts(@QueryMap Map<String, String> options);
}
