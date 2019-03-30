package com.zhuandian.shirtmade.business;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuandian.base.BaseActivity;
import com.zhuandian.shirtmade.R;
import com.zhuandian.shirtmade.entity.ShirtEntity;

import java.util.List;

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

    private ShirtEntity shirtEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_made_shirt;
    }

    @Override
    protected void setUpView() {
        shirtEntity = (ShirtEntity) getIntent().getSerializableExtra("data");
        BmobQuery<ShirtEntity> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("yanse", shirtEntity.getYanse());
        bmobQuery.findObjects(new FindListener<ShirtEntity>() {
            @Override
            public void done(List<ShirtEntity> list, BmobException e) {
                if (e == null)
                    Glide.with(MadeShirtActivity.this).load(list.get(0).getClothesUrl()).into(ivShirt);
            }
        });

    }


    @OnClick({R.id.tv_next, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                break;
            case R.id.tv_save:
                break;
        }
    }
}
