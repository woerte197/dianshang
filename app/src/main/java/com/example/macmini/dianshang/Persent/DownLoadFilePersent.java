package com.example.macmini.dianshang.Persent;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.widget.Toast;

import com.example.macmini.dianshang.JavaBean.UpdateInfo;
import com.example.macmini.dianshang.Modle.DownLoadFileModle;
import com.example.macmini.dianshang.Tool.ShowMessege;
import com.example.macmini.dianshang.View.updateDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by macmini on 2018/3/27.
 */

public class DownLoadFilePersent {
    private DownLoadFileModle downLoadFileModle;
    private Context mcontext;


    public void upDateApk(final Context context) {
        this.mcontext = context;
        downLoadFileModle = DownLoadFileModle.getInstance();
        downLoadFileModle.get("url", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsebody = response.body().toString();
                StringBuffer stringBuffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new StringReader(responsebody));
                UpdateInfo updateInfo = new UpdateInfo();
                updateInfo.setDescription(reader.readLine());
                updateInfo.setUrl(reader.readLine());
                updateInfo.setVersion(reader.readLine());
                String now_version = " ";
                try {
                    PackageManager packageManager = mcontext.getPackageManager();
                    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                    now_version = packageInfo.versionName;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (updateInfo.getVersion() == now_version) {
                    ShowMessege.getShowMessege().showString("已经是最新版本");
                } else {

                }
            }
        });

    }

    public void downFile(final Context context, String url) {
        DownLoadFileModle.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                updateDialog.downfiled();
                ShowMessege.getShowMessege().showString("更新失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = null;
                FileOutputStream fileoutputStream = null;
                try {
                    inputStream = response.body().byteStream();
                    long length = response.body().contentLength();
                    updateDialog.setMax(length);
                    if (inputStream != null) {
                        File file = new File(Environment.getExternalStorageDirectory(), "Earn.apk");
                        fileoutputStream = new FileOutputStream(file);
                        byte[] bytes = new byte[1024];
                        int ch = -1;
                        int process = 0;
                        while ((ch = inputStream.read(bytes)) != -1) {
                            fileoutputStream.write(bytes, 0, ch);
                            process += ch;
                            updateDialog.downLoading(process);
                        }
                    }
                    fileoutputStream.flush();
                    if (fileoutputStream != null) {
                        fileoutputStream.close();
                    }
                    ShowMessege.getShowMessege().showString("下载成功");
                     updateDialog.downSuccess(context);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (Exception e) {
                        e.fillInStackTrace();
                    }
                    try {
                        if (fileoutputStream != null) {
                            fileoutputStream.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
