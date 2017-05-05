package com.lanjiang.figersland.http;

import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求管理类
 * Created by Asus on 2017/3/6.
 */

public class HttpManager {

    public static final String API_URL = "https://api.github.com";
    private static HttpManager mInstance;

    public HttpManager() {
    }

    public static class SingletonInstance {
        private static final HttpManager instance = new HttpManager();
    }

    //静态内部类
    public static HttpManager getInstance() {
        return SingletonInstance.instance;
    }

    private GitHubService retrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GitHubService.class);
    }

    public static void testNet() throws IOException {
        GitHubService github = HttpManager.getInstance().retrofit();
        Call<List<Contributors>> call = github.contributors("square","retrofit");
        call.enqueue(new Callback<List<Contributors>>() {
            @Override
            public void onResponse(Call<List<Contributors>> call, Response<List<Contributors>> response) {

                Log.d("TAG", "testNet: "+"--ok--");
                for (Contributors object : response.body()) {
                    Log.d("TAG", "testNet: "+object.toString()+"/n");
                }
            }

            @Override
            public void onFailure(Call<List<Contributors>> call, Throwable t) {
                Log.d("TAG", "testNet: "+"--failure--");
            }
        });

    }










    // 双重检查锁定不是线程安全的，如果要用这种方式，需要使用volatile关键字。
//    private static volatile HttpManager mInstance = null;

//    //双重检查
//    public static HttpManager getInstance() {
//        if (mInstance == null) {
//            synchronized (HttpManager.class) {
//                if (mInstance == null) {
//                    mInstance = new HttpManager();
//                }
//            }
//        }
//        return mInstance;
//    }

}
