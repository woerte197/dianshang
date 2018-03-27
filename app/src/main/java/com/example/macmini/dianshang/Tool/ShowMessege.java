package com.example.macmini.dianshang.Tool;

import android.content.Context;
import android.widget.Toast;

import com.example.macmini.dianshang.MyApplication;

/**
 * Created by macmini on 2018/3/27.
 */

public class ShowMessege {
    private static ShowMessege showMessege = null;

    public static ShowMessege getShowMessege() {
        if (showMessege == null) {
            synchronized (ShowMessege.class) {
                showMessege = new ShowMessege();
            }
        }
        return showMessege;
    }

    public void showString(String s){
        Toast.makeText(MyApplication.getContext(),s,Toast.LENGTH_LONG).show();
    }
}
