package com.zhuandian.shirtmade.business;


import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuandian.base.BaseActivity;
import com.zhuandian.shirtmade.R;
import com.zhuandian.shirtmade.entity.ShirtEntity;
import com.zhuandian.shirtmade.utils.ImgDonwloads;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MadeShirtActivity extends BaseActivity {

    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_shirt)
    ImageView ivShirt;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private int madeCount;  //最多生成三次

    private ShirtEntity shirtEntity;
    private List<ShirtEntity> shirtEntityList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_made_shirt;
    }

    @Override
    protected void setUpView() {
        shirtEntity = (ShirtEntity) getIntent().getSerializableExtra("data");
        BmobQuery<ShirtEntity> bmobQuery = new BmobQuery<>();
//        bmobQuery.addWhereEqualTo("yanse", shirtEntity.getYanse());
        bmobQuery.findObjects(new FindListener<ShirtEntity>() {
            @Override
            public void done(List<ShirtEntity> list, BmobException e) {
                if (e == null) {
                    shirtEntityList = list;
                    Glide.with(MadeShirtActivity.this).load(list.get(0).getClothesUrl()).into(ivShirt);
                } else {
                    new AlertDialog.Builder(MadeShirtActivity.this)
                            .setTitle("抱歉")
                            .setMessage("系统库里暂时没有符合您要求的衬衣推荐")
                            .create()
                            .show();
                }

            }
        });

    }


    @OnClick({R.id.tv_next, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                madeCount++;
                if (madeCount > 2) {
                    new AlertDialog.Builder(MadeShirtActivity.this)
                            .setTitle("oh no ...")
                            .setMessage("当前没有您想满意的衬衣样式，很抱歉")
                            .create()
                            .show();
                    break;
                }
                if (shirtEntityList != null && shirtEntityList.size() > 0) {
                    int i = new Random().nextInt(shirtEntityList.size());
                    Glide.with(MadeShirtActivity.this).load(shirtEntityList.get(i).getClothesUrl()).into(ivShirt);
                }
                break;
            case R.id.tv_save:
                //检查写入权限
                if (ContextCompat.checkSelfPermission(MadeShirtActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    //拥有读写文件权限
                    ImgDonwloads.donwloadImg(MadeShirtActivity.this, shirtEntityList.get(madeCount).getClothesUrl());//iPath
                }else{
                    //没有读写权限
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MadeShirtActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        ActivityCompat.requestPermissions(MadeShirtActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                140);
                    }else{
                        ImgDonwloads.donwloadImg(MadeShirtActivity.this, shirtEntityList.get(madeCount).getClothesUrl());//iPath
                    }
                }
                break;
        }
    }

}
