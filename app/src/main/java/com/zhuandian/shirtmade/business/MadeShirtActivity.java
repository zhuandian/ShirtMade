package com.zhuandian.shirtmade.business;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuandian.base.BaseActivity;
import com.zhuandian.shirtmade.R;
import butterknife.BindView;
import butterknife.OnClick;

public class MadeShirtActivity extends BaseActivity {

    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_shirt)
    ImageView ivShirt;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_save)
    TextView tvSave;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_made_shirt;
    }

    @Override
    protected void setUpView() {

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
