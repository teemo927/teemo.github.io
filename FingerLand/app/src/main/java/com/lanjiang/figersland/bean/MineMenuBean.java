package com.lanjiang.figersland.bean;

/**
 * Created by Lin on 2016/12/27.
 */

public class MineMenuBean {
    private String name;
    private int icon;
    private String prompt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public MineMenuBean(String name, int icon, String prompt) {
        this.name = name;
        this.icon = icon;
        this.prompt = prompt;
    }

}
