package com.example.macmini.dianshang;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by macmini on 2018/5/30.
 */

public interface ApiService {

    @Streaming
    @GET
    Observable<ResponseBody> down(@Header("Range") String apikey,@Url String url);
}
