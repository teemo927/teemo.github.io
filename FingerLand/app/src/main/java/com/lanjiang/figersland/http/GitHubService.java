package com.lanjiang.figersland.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit网络请求接口
 * Created by Asus on 2017/3/6.
 */

public interface GitHubService {
    @POST("user/submit.html")
    Call<String> getString(@Query("loginname") String loginname,
                           @Query("nloginpwd") String nloginpwd);

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributors>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);

    @Headers("Authorization: authorization")
    Call getUser();

    Call getUser2(@Header("Authorization") String authorization);
}
