package com.lanjiang.figersland.bean;

/**
 * Created by Lin on 2017/2/20.
 */

public class MyCollectionBean {
    private int logo;
    private String title;
    private String other1;
    private String other2;
    private String other3;
    private String currency;

    public MyCollectionBean(int logo, String title, String other1) {
        this.logo = logo;
        this.title = title;
        this.other1 = other1;
    }

    public MyCollectionBean(int logo, String title, String other1, String other2, String other3, String currency) {
        this.logo = logo;
        this.title = title;
        this.other1 = other1;
        this.other2 = other2;
        this.other3 = other3;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }
}
