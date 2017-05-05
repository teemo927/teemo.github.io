package com.lanjiang.figersland.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 兴趣话题
 * Created by Asus on 2016/12/9.
 */

public class Topics {
    public int group1Id;
    public String firstGroupName;
    public int firstGroupIcon;
    public List<TopicGroup2> group2s;
    public int firstGroupIconPress;
    public List<Integer> showTopicList = new ArrayList<>(7);

    public Topics(int group1Id, String firstGroupName, int firstGroupIcon, int firstGroupIconPress, List<TopicGroup2> group2s) {
        this.group1Id = group1Id;
        this.firstGroupName = firstGroupName;
        this.firstGroupIcon = firstGroupIcon;
        this.firstGroupIconPress = firstGroupIconPress;
        this.group2s = group2s;
    }



}
