package com.zhuandian.shirtmade.business;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhuandian.base.BaseActivity;
import com.zhuandian.shirtmade.App;
import com.zhuandian.shirtmade.R;
import com.zhuandian.shirtmade.entity.UserEntity;
import com.zhuandian.shirtmade.utils.PictureSelectorUtils;
import com.zhuandian.shirtmade.view.CircleImageView;
import com.zhuandian.utils.AppManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UserInfoActivity extends BaseActivity {


    @BindView(R.id.et_nick_name)
    EditText etNickName;
    @BindView(R.id.et_user_desc)
    EditText etUserDesc;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.iv_header)
    CircleImageView ivHeader;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    private UserEntity userEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void setUpView() {
        userEntity = BmobUser.getCurrentUser(UserEntity.class);
        etNickName.setText(userEntity.getNikeName());
        etUserDesc.setText(userEntity.getUserInfo());
        tvUserName.setText(userEntity.getUsername());
    }


    @OnClick({R.id.tv_submit, R.id.iv_header, R.id.tv_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if (!TextUtils.isEmpty(etNickName.getText().toString()) || !TextUtils.isEmpty(etUserDesc.getText().toString())) {
                    userEntity.setNikeName(etNickName.getText().toString());
                    userEntity.setUserInfo(etUserDesc.getText().toString());
                    userEntity.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(UserInfoActivity.this, "更新成功...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            case R.id.iv_header:
                PictureSelectorUtils.selectImg(this, 1);
                break;

            case R.id.tv_logout:
                new AlertDialog.Builder(UserInfoActivity.this)
                        .setTitle("确定退出吗")
                        .setMessage("确定是否要退出软件？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppManager.getInstance().finishAllActivity();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
                break;
        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() > 0) {
                    String imagePath = selectList.get(0).getCompressPath();
                    decodePath2Bitmap(imagePath);

                }
            }
        }
    }

    /**
     * 把指定路径的image资源转成Bitmap
     *
     * @param path
     */
    private void decodePath2Bitmap(String path) {
        Bitmap bm = BitmapFactory.decodeFile(path);
        if (bm != null) {
            bm = ImageCrop(bm);
            ivHeader.setImageBitmap(bm);
        }
    }

    /**
     * 按正方形裁切图片
     */
    public Bitmap ImageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int wh = w > h ? h : w;// 裁切后所取的正方形区域边长

        int retX = w > h ? (w - h) / 2 : 0;//基于原图，取正方形左上角x坐标
        int retY = w > h ? 0 : (h - w) / 2;

        Matrix matrix = new Matrix();
        matrix.postScale(0.5f, 0.5f); //长和宽放大缩小的比例

        //下面这句是关键
        Bitmap result = Bitmap.createBitmap(bitmap, retX, retY, wh, wh, matrix, false);
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
            System.gc();
        }
        return result;
    }

}
