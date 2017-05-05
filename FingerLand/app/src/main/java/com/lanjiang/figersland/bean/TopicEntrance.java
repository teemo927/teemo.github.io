package com.lanjiang.figersland.bean;

/**
 * 首页话题入口类
 * Created by Asus on 2016/12/13.
 */

public class TopicEntrance {
    private String name;
    private int iconId;

    public TopicEntrance(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
