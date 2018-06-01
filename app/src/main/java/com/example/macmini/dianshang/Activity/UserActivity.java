package com.example.macmini.dianshang.Activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.example.macmini.dianshang.Bottom.BaseActivity;
import com.example.macmini.dianshang.Fragment.DialogFragment.ImageFragment;
import com.example.macmini.dianshang.R;
import com.example.macmini.dianshang.databinding.ActivityUserBinding;

public class UserActivity extends BaseActivity {
    ActivityUserBinding binding;
    private static final String TAG = "UserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        binding.image.setOnClickListener(view -> {
            ImageFragment imageFragment = new ImageFragment();
            imageFragment.show(this.getSupportFragmentManager(), "image");
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: "+requestCode);
        switch (requestCode) {
            case 3:
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    binding.image.setImageBitmap(image);
                }
                break;
        }
    }
}
