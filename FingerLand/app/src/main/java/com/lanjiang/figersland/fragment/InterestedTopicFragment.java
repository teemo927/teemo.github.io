package com.lanjiang.figersland.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.SimpleExpandableListViewAdapter;
import com.lanjiang.figersland.bean.AllTopic;
import com.lanjiang.figersland.bean.Classes;
import com.lanjiang.figersland.bean.College;
import com.lanjiang.figersland.bean.TopicGroup2;
import com.lanjiang.figersland.bean.Topics;
import com.lanjiang.figersland.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 2016/12/5.
 */

public class InterestedTopicFragment extends BFragment {

    @BindView(R.id.root_elv)
    ExpandableListView mRootElv;
    private SimpleExpandableListViewAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expandable, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();

        final List<Topics> data = initData();
        mAdapter = new SimpleExpandableListViewAdapter(mContext, data);
        mRootElv.setAdapter(mAdapter);

        mRootElv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < mAdapter.getGroupCount(); i++) {
                    if (groupPosition != i) {
                        mRootElv.collapseGroup(i);
                    } else {
                        mRootElv.setSelectedGroup(groupPosition);
                    }
                }
                //刷新选中数据
                mAdapter.refreshData(mAdapter.getData());
            }
        });

    }

    private List<Topics> initData() {
        List<Topics> topics = new ArrayList<>();

        String[] groups1 = new String[]{"勘探设计、监理及咨询服务", "前期、基础及主体工程", "市政、园林景观", "市政、园林景观2", "市政、园林景观3"};
        int[] iconRes = new int[]{R.drawable.kantan_normal_icon, R.drawable.qianqi_normal_icon, R.drawable.xiaofang_normal_icon, R.drawable.xiaofang_normal_icon, R.drawable.xiaofang_normal_icon};
        int[] iconPressRes = new int[]{R.drawable.kantan_select_normal, R.drawable.qianqi_select_icon, R.drawable.xiaofang_select_icon, R.drawable.xiaofang_select_icon, R.drawable.xiaofang_select_icon};

        //唯一topic值
        short random = 0;
        for (int i = 0; i < groups1.length; i++) {
            List<TopicGroup2> group2List = new ArrayList<>();
            String[] groups2 = new String[]{"建筑设计", "检验类"};

            for (int j = 0; j < groups2.length; j++) {
                String[] allTopic = new String[]{"精装修设计", "施工图设计", "规划设计"};
                List<AllTopic> allTopics = new ArrayList<>();

                for (int a = 0; a < allTopic.length; a++) {
                    LogUtils.e(TAG, "- " + random);
                    allTopics.add(new AllTopic(random, false, allTopic[a]));
                    random++;
                }
                group2List.add(new TopicGroup2(j, groups2[j], allTopics));
            }
            topics.add(new Topics(i, groups1[i], iconRes[i], iconPressRes[i], group2List));
        }
        return topics;
    }

    @NonNull
    private List<College> initDatas() {
        List<College> collegeList = new ArrayList<>();

        for (int j = 0; j < 5; j++) {
            List<Classes> classesList = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                List<String> stringList = new ArrayList<>();
                stringList.add("小明");
                stringList.add("小红");
                stringList.add("小弟");

                Classes classes = new Classes("班级 " + i, stringList);
                classesList.add(classes);
            }

            College college = new College();
            college.name = "深圳大学" + j;
            college.classList = classesList;

            collegeList.add(college);
        }
        return collegeList;
    }

    public static Fragment newInstance() {
        InterestedTopicFragment fragment = new InterestedTopicFragment();
        return fragment;
    }

}
