package com.lanjiang.figersland.bean;

/**
 *
 * Created by Asus on 2016/12/12.
 */

public class AllTopic {
    //需要唯一
    public Integer topicId;
    private boolean isSelect;
    public String topicName;

    public AllTopic(int topicId, boolean isSelect, String topicName) {
        this.topicId = topicId;
        this.isSelect = isSelect;
        this.topicName = topicName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void toggle() {
        setSelect(!isSelect());
    }
}