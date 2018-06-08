package com.example.macmini.dianshang.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.macmini.dianshang.DBhelper.DBHelper;
import com.example.macmini.dianshang.JavaBean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini on 2018/6/6.
 */

public class DBUtils {
    public static DBUtils dbUtils = null;
    private DBHelper dbHelper;
    SQLiteDatabase database;

    public static DBUtils ins(Context context) {
        if (dbUtils == null) {
            synchronized (DBUtils.class) {
                dbUtils = new DBUtils(context);
            }
        }
        return dbUtils;
    }

    public DBUtils(Context context) {
        dbHelper = new DBHelper(context);
    }

    public SQLiteDatabase getReadpre() {
        database = dbHelper.getReadableDatabase();
        return database;
    }

    public SQLiteDatabase getWrite() {
        database = dbHelper.getWritableDatabase();
        return database;
    }

    public void insertDb(String tablename, String username, String password) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        database.insert(tablename, null, values);
    }

    public List<User> queryDb(String tablename) {
        Cursor cursor = database.query(tablename, null, null, null, null, null, null);
        return queryUser(cursor);
    }

    private List<User> queryUser(Cursor cursor) {
        List<User> list = new ArrayList<>();
        if (cursor != null) {
            int c = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < c; i++) {
                User user = new User();
                user.setPassword(cursor.getString(cursor.getColumnIndex("passord")));
                user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                list.add(user);
                cursor.moveToNext();
            }
        }
        return list;

    }

}
