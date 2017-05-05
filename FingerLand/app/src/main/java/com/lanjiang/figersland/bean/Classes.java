package com.lanjiang.figersland.bean;

import java.util.List;

/**
 *
 * 班级实体类
 * Created by MH on 2016/6/16.
 */
public class Classes {


    // 班级名
    public String name;


    // 班级中的学生列表
    public List<String> students;

    public Classes(String name, List<String> students) {
        this.name = name;
        this.students = students;
    }
}
