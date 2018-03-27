package com.example.macmini.dianshang.View;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;

import com.example.macmini.dianshang.JavaBean.UpdateInfo;
import com.example.macmini.dianshang.Persent.DownLoadFilePersent;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.Tool.ShowMessege;

/**
 * Created by macmini on 2018/3/27.
 */

public class updateDialog {
    private static ProgressDialog progressDialog;
    private static DownLoadFilePersent presenter = new DownLoadFilePersent();

    public static void showUpdateDialog(final Context context, final UpdateInfo updateInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("请升级APP至版本" + updateInfo.getVersion());
        builder.setMessage(updateInfo.getDescription());
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    downFile(context, updateInfo.getUrl());
                } else {
                    ShowMessege.getShowMessege().showString("SD卡不可用，请插入SD卡");
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    public static void downFile(Context context, String url) {
        progressDialog = new ProgressDialog(context);    //进度条，在下载的时候实时更新进度，提高用户友好度
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载");
        progressDialog.setMessage("请稍候...");
        progressDialog.setProgress(0);
        progressDialog.show();
        presenter.downFile(context, url);
        Log.d("SettingActivity", "downFile: ");
    }

    public static void downfiled() {
        ShowMessege.getShowMessege().showString("下载失败");
        progressDialog.cancel();
    }

    public static void setMax(long l) {
        progressDialog.setMax((int) l);
    }

    public static void downLoading(int i) {
        progressDialog.setProgress(i);
    }

    public static void downSuccess(Context context) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.black_background);
        builder.setTitle(R.string.app_name);
        builder.setMessage("是否安装");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}

