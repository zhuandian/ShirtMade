package com.zhuandian.shirtmade.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * desc :
 * author：xiedong
 * date：2019/3/26
 */
public class ShirtEntity extends BmobObject implements Serializable {
    private String tixing; //体型
    private String fubu;// 腹部
    private String jian;//肩
    private String banxing;//版型
    private String chima;//尺码
    private String caizhi;//材质
    private String yanse;//颜色
    private String jijie;//季节
    private String changhe;//场合
    private String clothesUrl; //衣服链接

    public String getClothesUrl() {
        return clothesUrl;
    }

    public void setClothesUrl(String clothesUrl) {
        this.clothesUrl = clothesUrl;
    }

    public String getTixing() {
        return tixing;
    }

    public void setTixing(String tixing) {
        this.tixing = tixing;
    }

    public String getFubu() {
        return fubu;
    }

    public void setFubu(String fubu) {
        this.fubu = fubu;
    }

    public String getJian() {
        return jian;
    }

    public void setJian(String jian) {
        this.jian = jian;
    }

    public String getBanxing() {
        return banxing;
    }

    public void setBanxing(String banxing) {
        this.banxing = banxing;
    }

    public String getChima() {
        return chima;
    }

    public void setChima(String chima) {
        this.chima = chima;
    }

    public String getCaizhi() {
        return caizhi;
    }

    public void setCaizhi(String caizhi) {
        this.caizhi = caizhi;
    }

    public String getYanse() {
        return yanse;
    }

    public void setYanse(String yanse) {
        this.yanse = yanse;
    }

    public String getJijie() {
        return jijie;
    }

    public void setJijie(String jijie) {
        this.jijie = jijie;
    }

    public String getChanghe() {
        return changhe;
    }

    public void setChanghe(String changhe) {
        this.changhe = changhe;
    }
}
