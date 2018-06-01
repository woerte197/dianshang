package com.example.macmini.dianshang.Utils;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.example.macmini.dianshang.Activity.UserActivity;
import com.example.macmini.dianshang.Fragment.DialogFragment.ImageFragment;
import com.example.macmini.dianshang.MyApplication;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Created by macmini on 2018/6/1.
 */

public class SystemUtils {
    public static SystemUtils systemUtils = null;
    public static final int RESULT_CAMERA = 200;
    private Uri imageUri,zoomImageUri;
    private String filename;

    public static SystemUtils Ins() {
        if (systemUtils == null) {
            synchronized (SystemUtils.class) {
                systemUtils = new SystemUtils();
            }
        }
        return systemUtils;
    }

    public Uri disVersion(File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(MyApplication.getContext(), "dream.goto.provider", file);//添加这一句表示对目标应用临时授权该Uri所代表的文件
        } else {
            imageUri = Uri.fromFile(file);
        }
        return imageUri;
    }

    public void startCream(Context context, Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        ((UserActivity) context).startActivityForResult(intent, RESULT_CAMERA);
    }
    public void startPhotoZoom(Context context,Uri uri) throws IOException {
        if (SdUtils.isSD()) {
            filename = SdUtils.getSdCard() + "/ZoomImage/" + "zoom.jpg";
        }
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        zoomImageUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, zoomImageUri);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        ((UserActivity) context).startActivityForResult(intent, 3);
    }
}
