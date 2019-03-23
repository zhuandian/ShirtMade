package com.zhuandian.shirtmade;

import android.app.Application;

import com.zhuandian.shirtmade.utils.Constant;

import cn.bmob.v3.Bmob;

/**
 * desc :
 * author：xiedong
 * date：2019/3/23
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, Constant.APP_ID);
    }
}
