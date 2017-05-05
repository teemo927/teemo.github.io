package com.lanjiang.figersland.bean;

/**
 * 循环viewpager数据类
 * Created by Asus on 2017/3/1.
 */

public class BannerItem {
    /**
     * 图片Url地址
     */
    private String url;
    /**
     * 图片本地资源
     */
    private int id;

    public BannerItem(int id) {
        this.id = id;
    }

    public BannerItem(String url, int id) {
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
