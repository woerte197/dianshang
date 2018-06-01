package com.example.macmini.dianshang.Fragment.DialogFragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macmini.dianshang.Activity.MainActivity;
import com.example.macmini.dianshang.Activity.UserActivity;
import com.example.macmini.dianshang.Bottom.BaseActivity;
import com.example.macmini.dianshang.Fragment.FIrstFragment;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.Tool.ShowMessege;
import com.example.macmini.dianshang.Utils.SdUtils;
import com.example.macmini.dianshang.Utils.SystemUtils;
import com.example.macmini.dianshang.databinding.FragmentImageBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */

@SuppressLint("ValidFragment")
public class ImageFragment extends BaseDialogFragment<FragmentImageBinding> {
    private String filename, afterfilename;
    private Uri imageUri, zoomImageUri;
    private static final int RESULT_CAMERA = 200;
    private static final int CROP_PICTURE = 3;
    private ShowImage showImage;
    private static final String TAG = "ImageFragment";
    private static final int RESULT_IMAGE = 100;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
    }


    private void initEvent() {
        bindview.quxiao.setOnClickListener(view -> {
            dismiss();
        });
        bindview.fromxiangce.setOnClickListener(view -> {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            startActivityForResult(intent, RESULT_IMAGE);//打开相册
        });
        bindview.paizhao.setOnClickListener(view -> {
            if (SdUtils.isSD()) {
                filename = SdUtils.getSdCard() + "/Image/" + "test.jpg";
                try {
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
                    imageUri = SystemUtils.Ins().disVersion(file);
                   // SystemUtils.Ins().startCream(getActivity(), imageUri);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, RESULT_CAMERA);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                ShowMessege.getShowMessege().showString("没有sd卡");
            }
        });
    }

    @Override
    protected int setlayout() {
        return R.layout.fragment_image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: " + requestCode);
        switch (requestCode) {
            case RESULT_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    dismiss();
                    ShowMessege.getShowMessege().showString("图片拍摄成功");
                    if (imageUri != null) {
                        try {
                            SystemUtils.Ins().startPhotoZoom(getActivity(), imageUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case RESULT_IMAGE:
                if (Build.VERSION.SDK_INT >= 19) {
                    handlerImageOnKitKat(data);
                }
                break;
            default:
                break;
        }
    }


    private void handlerImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (DocumentsContract.isDocumentUri(getActivity(), uri)) {
                //如果是document类型的Uri,则通过document id处理
                String docId = DocumentsContract.getDocumentId(uri);
                if ("com.android.providers.media.documents" .equals(uri.getAuthority())) {
                    String id = docId.split(":")[1];//解析出数字格式的id
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } else if ("com.android.providers.downloads.documents" .equals(uri.getAuthority())) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    imagePath = getImagePath(contentUri, null);
                }
            } else if ("content" .equalsIgnoreCase(uri.getScheme())) {
                //如果是content类型的URI，则使用普通方式处理
                imagePath = getImagePath(uri, null);
            } else if ("file" .equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的Uri,直接获取图片路径即可
                imagePath = uri.getPath();
            }
        }
        File file = new File(imagePath);
        try {
            file.createNewFile();
            Uri uri1;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri1 = FileProvider.getUriForFile(getActivity(),
                        "dream.goto.provider", file);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            } else {
                uri1 = Uri.fromFile(file);
            }
            SystemUtils.Ins().startPhotoZoom(getActivity(), uri1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


    public interface ShowImage {
        void showimage(Bitmap b);
    }
}
