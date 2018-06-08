package com.example.macmini.dianshang.Activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.macmini.dianshang.Bottom.BaseActivity;
import com.example.macmini.dianshang.Fragment.DialogFragment.ImageFragment;
import com.example.macmini.dianshang.JavaBean.BuilderRelaize;
import com.example.macmini.dianshang.JavaBean.User;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.Utils.SdUtils;
import com.example.macmini.dianshang.databinding.ActivityUserBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UserActivity extends BaseActivity {
    ActivityUserBinding binding;
    private static final String TAG = "UserActivity";
    ImageFragment imageFragment = new ImageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        BuilderRelaize builderRelaize = new BuilderRelaize();
        User user = builderRelaize.password("sad").username("dsada").builde();
        Log.i(TAG, "onCreate: "+user.getPassword());
        binding.image.setOnClickListener(view -> {
            imageFragment.show(this.getSupportFragmentManager(), "image");
        });
        binding.button.setOnClickListener(view -> {
            if (SdUtils.isSD()) {
                String filename = SdUtils.getSdCard() + "/MYTEXT/" + "myfirst.txt";
                File file = new File(filename);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    String s = "我是新建的";
                    byte[] bytes1 = s.getBytes();
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(bytes1);
//                    FileInputStream inputStream = new FileInputStream(file);
//                    while (inputStream.read(bytes1) != -1) {
//                        inputStream.read(bytes1);
//                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.button1.setOnClickListener(view -> {

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: " + requestCode);
        switch (requestCode) {
            case 3:
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    imageFragment.dismiss();
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    binding.image.setImageBitmap(image);
                }
                break;
        }
    }

    @Override
    public void update() {
        Log.i(TAG, "update: " + TAG);
    }
}
