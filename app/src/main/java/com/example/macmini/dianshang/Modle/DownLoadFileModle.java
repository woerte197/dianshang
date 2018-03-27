package com.example.macmini.dianshang.Modle;

import android.content.Context;

import com.example.macmini.dianshang.Persent.DownLoadFilePersent;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by macmini on 2018/3/27.
 */

public class DownLoadFileModle {
    private static DownLoadFileModle downLoadFileModle = null;

    public static DownLoadFileModle getInstance() {
        if (downLoadFileModle == null) {
            synchronized (DownLoadFileModle.class) {
                downLoadFileModle = new DownLoadFileModle();
            }
        }
        return downLoadFileModle;
    }

    public void get(String url, okhttp3.Callback callback ) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody body = builder.build();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
