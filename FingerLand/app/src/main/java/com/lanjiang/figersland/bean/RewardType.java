package com.lanjiang.figersland.bean;

import java.util.List;

/**
 * 悬赏类型实体
 * Created by Asus on 2016/12/14.
 */

public class RewardType {
    private String rewardGroup;
    private List<String> typeList;

    public RewardType(String rewardGroup, List<String> typeList) {
        this.rewardGroup = rewardGroup;
        this.typeList = typeList;
    }

    public String getRewardGroup() {
        return rewardGroup;
    }

    public void setRewardGroup(String rewardGroup) {
        this.rewardGroup = rewardGroup;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    @Override
    public String toString() {
        return "RewardType{" +
                "rewardGroup='" + rewardGroup + '\'' +
                ", typeList=" + typeList +
                '}';
    }
}
