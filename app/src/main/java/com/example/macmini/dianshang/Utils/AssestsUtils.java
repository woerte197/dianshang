package com.example.macmini.dianshang.Utils;

import android.util.Log;

import com.example.macmini.dianshang.JavaBean.City;
import com.example.macmini.dianshang.JavaBean.Country;
import com.example.macmini.dianshang.MyApplication;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * Created by macmini on 2018/6/5.
 */

public class AssestsUtils {
    private static  AssestsUtils assestsUtils = null;
    private static final String TAG = "AssestsUtils";
    public static  AssestsUtils ins() {
        if (assestsUtils == null) {
            synchronized (AssestsUtils.class) {
                assestsUtils = new AssestsUtils();
            }
        }
        return assestsUtils;
    }

    public Country getCountry() {
        Country country = null;
        try {
            StringBuilder builder = new StringBuilder();
            InputStream inputStream = MyApplication.getContext().getAssets().open("city.json");

            InputStreamReader reader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = "";
                while ((line=bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
            Gson gson = new Gson();
            Log.i(TAG, "getCountry: "+builder.toString());
            country= gson.fromJson(builder.toString(), Country.class);
            Log.i(TAG, "getCountry: "+country);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return country;
    }
}
