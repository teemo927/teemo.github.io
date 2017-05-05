package com.lanjiang.figersland.bean;

import java.util.List;

/**
 * 
 * Created by Asus on 2016/12/12.
 */

public class TopicGroup2 {
    public int group2Id;
    public String groupName;
    public List<AllTopic> allTopics;

    public TopicGroup2(int group2Id, String groupName, List<AllTopic> allTopics) {
        this.group2Id = group2Id;
        this.groupName = groupName;
        this.allTopics = allTopics;
    }

}
