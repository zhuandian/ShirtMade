package com.zhuandian.shirtmade.business;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhuandian.base.BaseActivity;
import com.zhuandian.shirtmade.MainActivity;
import com.zhuandian.shirtmade.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void setUpView() {

    }



    @OnClick({R.id.tv_start, R.id.tv_user_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start:
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                break;
            case R.id.tv_user_info:
                startActivity(new Intent(HomeActivity.this,UserInfoActivity.class));
                break;
        }
    }
}
