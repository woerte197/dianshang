package com.example.macmini.dianshang.Fragment.DialogFragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macmini.dianshang.ApiFactory.ApiFactory;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.Tool.ShowMessege;
import com.example.macmini.dianshang.Utils.SdUtils;
import com.example.macmini.dianshang.Utils.ShareUtils;
import com.example.macmini.dianshang.databinding.FragmentUpdateBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends BaseDialogFragment<FragmentUpdateBinding> {
    private String downloadUrl;
    private File file;
    String filePath;
    private long allsize;
    private boolean isfirst=true;
    boolean down = true;
    private String headers;
    private static final String TAG = "UpdateFragment";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    float f = (float) msg.obj;
                    int a = (int) f;
                    bindview.tvContent.setText("已下载" + a + "%");
                    bindview.pb.setProgress(a);
                    break;
                case 2:
                    bindview.hide.setText("继续下载");
                    break;
                case 3:
                    bindview.hide.setText("暂停下载");
                    break;
                case 4:
                    installApk(file, getActivity());
                    break;
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindview.stop.setEnabled(true);
        bindview.stop.setOnClickListener(view -> {
            Log.i(TAG, "onActivityCreated: "+isfirst);
            download(true);
            bindview.stop.setEnabled(false);
        });
        bindview.hide.setOnClickListener(view -> {
            if (down) {
                down = false;
                postjindu(2, 0);
            } else {
                postjindu(3, 0);
                down = true;
                download(false);

            }

        });
    }

    private void download(boolean isfirst) {
        if (!SdUtils.isSD()){
            ShowMessege.getShowMessege().showString("没有SD卡");
            return;
        }
        filePath = SdUtils.getSdCard() + "/TEST/Down/" + "gaokao.apk";
        downloadUrl = "http://p.gdown.baidu.com/3f2ace8614abfa661843ec66e6548aa982cf88d579607be812ad48c4b240044dd9387351e5dd5365c890271eb097b1d63f692ba823342d1cafe944aa064a5c3bae9da64a7a875d7a8801cf6e54dd6d527a61710e5b831f0e310c8dd439b8e43e827a013f3998f4f59d7a2796dd1fbf43d5a8d9675a8a94ca8089872dd3352befeec1ad2d7836467c570e7e38fe8a118772f43408ce2a28bb7631640953de66dbc58f29b5df2eeb11ce26a71c4ba0fd4dfb49904557d40d224909c0da1b44efa2924ad35a5c9d63d31f8cb79d369adce5faddafe2f5caa10917514d4fb96b3004cd6d929780baeeb677338830445ada965168c8926d00af2db8420f96f1a4d67c";
        Log.i(TAG, "download: " + ShareUtils.getIns().getDownload());
        if (ShareUtils.getIns().getDownload() >=allsize) {
            ShareUtils.getIns().setDownload(0);
        }
        if (isfirst) {
            headers = null;
        } else {
            headers="bytes=" + ShareUtils.getIns().getDownload() + "-" + allsize;
            Log.i(TAG, "download: "+allsize);
        }

        ApiFactory.Ins().down(headers, downloadUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(responseBody -> {
                    allsize = responseBody.contentLength() + ShareUtils.getIns().getDownload();
                })
                .doOnNext(responseBody ->
                        writeFile(responseBody, createFile(isfirst)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                }, throwable -> {
                    ShowMessege.getShowMessege().showString("失败");
                });

    }



    public static void installApk(File file, Context context) {
        try {
            String command = "chmod 777 " + file.getAbsolutePath();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                // 由于没有在Activity环境下启动Activity,设置下面的标签
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(context, "dream.goto.provider", file);
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                context.startActivity(intent);
                Process.killProcess(Process.myPid());
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(new File(file.getAbsolutePath())),
                        "application/vnd.android.package-archive");
                context.startActivity(intent);
                Process.killProcess(Process.myPid());

            }

        } catch (Exception e) {
            System.out.print("");
        }


    }

    private File createFile(boolean isfirst) {
        try {
            file = new File(filePath);
            if (isfirst){
                if (file.exists()){
                    file.delete();
                }
            }
            Log.i(TAG, "createFile: "+file);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    private void writeFile(ResponseBody body, File file) {
        Log.i(TAG, "writeFile: " + file);
        InputStream inputStream = body.byteStream();
        byte b[] = new byte[1024];
        try {
            FileOutputStream outputStream = new FileOutputStream(file, true);
            while (down) {
                int read = inputStream.read(b);
                if (read == -1) {
                    postjindu(4, 0);
                    break;
                }
                long downsize = ShareUtils.getIns().getDownload() + read;
                ShareUtils.getIns().setDownload(downsize);
                Log.i(TAG, "writeFile: " + ShareUtils.getIns().getDownload());
                float jindu = downsize / (allsize * 1.00f) * 100;
                Log.i(TAG, "writeFile: " + jindu);
                outputStream.write(b, 0, read);
                postjindu(1, jindu);
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    protected int setlayout() {
        return R.layout.fragment_update;
    }

    public void postjindu(int id, float j) {
        Message message = new Message();
        message.obj = j;
        message.what = id;
        handler.sendMessage(message);
    }

}
