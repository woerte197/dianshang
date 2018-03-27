package com.example.macmini.dianshang.ApiFactory;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by macmini on 2018/3/27.
 */

public class ApiFactory {
    void setOkHttpPersent(){

    }
    void setOkHttpClient()

    {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

}
