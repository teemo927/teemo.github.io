package com.lanjiang.figersland.bean;

/**
 * Created by Lin on 2017/2/17.
 */

public class PositionInfoBean {
    public PositionInfoBean(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
