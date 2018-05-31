package com.example.macmini.dianshang.ApiFactory;

import com.example.macmini.dianshang.ApiService;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by macmini on 2018/3/27.
 */

public class ApiFactory {

    //    void setOkHttpPersent(){
//
//    }
//    void getOkhttp()
//
//    {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder().url("").build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });
//    }
//    void PostOkhttp(){
//
//    }
    public static HashMap<Class, ApiService> apiServiceHashMap = new HashMap<>();

    public static ApiService Ins() {
        ApiService service=apiServiceHashMap.get(ApiService.class);
        if (service==null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);
            builder.connectTimeout(60, TimeUnit.SECONDS);
            OkHttpClient client = builder.build();
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://innerapi.jiemo.net/v2/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            service = retrofit.create(ApiService.class);
            apiServiceHashMap.put(ApiService.class, service);
        }
            return service;
    }

}
