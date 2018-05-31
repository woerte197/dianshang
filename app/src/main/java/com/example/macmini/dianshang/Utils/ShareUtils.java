package com.example.macmini.dianshang.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.macmini.dianshang.MyApplication;

/**
 * Created by macmini on 2018/5/31.
 */

public class ShareUtils {
    public static ShareUtils shareUtils=null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static  ShareUtils getIns(){
        if (shareUtils==null){
            synchronized (ShareUtils.class){
                shareUtils=new ShareUtils();
            }
        }
        return shareUtils;
    }
    public ShareUtils(){
       sharedPreferences= MyApplication.getContext().getSharedPreferences("App", Context.MODE_PRIVATE);
       editor=sharedPreferences.edit();
    }
    public void setDownload(long l){
        editor.putLong("downsize",l);
        editor.commit();
    }
    public long getDownload(){
        return sharedPreferences.getLong("downsize",0l);

    }
}
