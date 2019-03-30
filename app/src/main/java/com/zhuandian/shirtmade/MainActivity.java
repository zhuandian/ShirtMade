package com.zhuandian.shirtmade;


import android.content.Intent;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhuandian.base.BaseActivity;
import com.zhuandian.shirtmade.business.MadeShirtActivity;
import com.zhuandian.shirtmade.entity.ShirtEntity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rb_shape_standard)
    RadioButton rbShapeStandard;
    @BindView(R.id.rb_shape_fat)
    RadioButton rbShapeFat;
    @BindView(R.id.rb_shape_thin)
    RadioButton rbShapeThin;
    @BindView(R.id.rb_bian_fu)
    RadioButton rbBianFu;
    @BindView(R.id.rb_tu_fu)
    RadioButton rbTuFu;
    @BindView(R.id.rb_song_jian)
    RadioButton rbSongJian;
    @BindView(R.id.rb_chui_jian)
    RadioButton rbChuiJian;
    @BindView(R.id.rb_bu_dui_cheng_jian)
    RadioButton rbBuDuiChengJian;
    @BindView(R.id.rb_qian_qing_jian)
    RadioButton rbQianQingJian;
    @BindView(R.id.acs_ban_xing)
    AppCompatSpinner acsBanXing;
    @BindView(R.id.acs_size)
    AppCompatSpinner acsSize;
    @BindView(R.id.acs_material)
    AppCompatSpinner acsMaterial;
    @BindView(R.id.acs_color)
    AppCompatSpinner acsColor;
    @BindView(R.id.acs_fit_season)
    AppCompatSpinner acsFitSeason;
    @BindView(R.id.acs_occasion)
    AppCompatSpinner acsOccasion;
    @BindView(R.id.tv_sure)
    TextView tvSure;

    private String[] tixing; //体型
    private String[] fubu;// 腹部
    private String[] jian;//肩
    private String[] banxing;//版型
    private String[] chima;//尺码
    private String[] caizhi;//材质
    private String[] yanse;//颜色
    private String[] jijie;//季节
    private String[] changhe;//场合

    private String chimaStr;
    private String caizhiStr;
    private String yanseStr;
    private String jijieStr;
    private String changheStr;
    private String banxiangeStr;
    private String tixingStr;
    private String fubuStr;
    private String jianStr;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        banxing = getResources().getStringArray(R.array.ban_xing);//版型
        chima = getResources().getStringArray(R.array.size);//尺码
        caizhi = getResources().getStringArray(R.array.material);//材质
        yanse = getResources().getStringArray(R.array.clothe_color);//颜色
        jijie = getResources().getStringArray(R.array.fit_season);//季节
        changhe = getResources().getStringArray(R.array.occasion);//场合
        initAllSpinner();

    }

    private void initAllSpinner() {
        acsBanXing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                banxiangeStr = banxing[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        acsColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yanseStr = yanse[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        acsFitSeason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jijieStr = jijie[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        acsMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                caizhiStr = caizhi[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        acsSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chimaStr = chima[0];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        acsOccasion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changheStr = changhe[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.tv_sure, R.id.rb_shape_standard, R.id.rb_shape_fat, R.id.rb_shape_thin, R.id.rb_bian_fu, R.id.rb_tu_fu, R.id.rb_song_jian, R.id.rb_chui_jian, R.id.rb_bu_dui_cheng_jian, R.id.rb_qian_qing_jian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_shape_standard:
                tixingStr = tixing[0];
                break;
            case R.id.rb_shape_fat:
                tixingStr = tixing[1];
                break;
            case R.id.rb_shape_thin:
                tixingStr = tixing[2];
                break;
            case R.id.rb_bian_fu:
                fubuStr = fubu[0];
                break;
            case R.id.rb_tu_fu:
                fubuStr = fubu[1];
                break;
            case R.id.rb_song_jian:
                jianStr = jian[0];
                break;
            case R.id.rb_chui_jian:
                jianStr = jian[1];
                break;
            case R.id.rb_bu_dui_cheng_jian:
                jianStr = jian[2];
                break;
            case R.id.rb_qian_qing_jian:
                jianStr = jian[3];
                break;
            case R.id.tv_sure:

                go2ShirtMadePage();
                break;
        }
    }

    private void go2ShirtMadePage() {
        ShirtEntity shirtEntity = new ShirtEntity();
        shirtEntity.setBanxing(banxiangeStr);
        shirtEntity.setTixing(tixingStr);
        shirtEntity.setFubu(fubuStr);
        shirtEntity.setJian(jianStr);
        shirtEntity.setCaizhi(chimaStr);
        shirtEntity.setCaizhi(caizhiStr);
        shirtEntity.setYanse(yanseStr);
        shirtEntity.setJijie(jijieStr);
        shirtEntity.setChanghe(changheStr);

        Intent intent = new Intent(MainActivity.this, MadeShirtActivity.class);
        intent.putExtra("data", shirtEntity);
        startActivity(intent);

    }


}
