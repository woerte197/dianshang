package com.example.macmini.dianshang.Utils;

import android.os.Environment;

/**
 * Created by macmini on 2018/6/1.
 */

public class SdUtils {
    public static boolean isSD() {
        boolean b = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        return b;
    }

    public static String getSdCard() {
        return Environment.getExternalStorageDirectory().getPath();
    }


}

