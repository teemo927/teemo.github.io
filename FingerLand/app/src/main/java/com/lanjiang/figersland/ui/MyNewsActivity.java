package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.widget.ListView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.CommonAdapter;
import com.lanjiang.figersland.adapter.ViewHolder;
import com.lanjiang.figersland.bean.MyNewsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lin on 2017/1/5.
 */

public class MyNewsActivity extends BaseToolbarActivity {
    @BindView(R.id.lv_my_news)
    ListView lvMyNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_news);
        ButterKnife.bind(this);
        init();
    }

    // 初始化方法
    private void init() {
        // 模拟数据
        List<MyNewsBean> data = new ArrayList<>();

        MyNewsBean myNewsBean = new MyNewsBean();
        myNewsBean.setNewsImage(R.drawable.join_icon);
        myNewsBean.setNewsTitle("兰江集团参与竞标");
        myNewsBean.setNewProjectName("中铁·溪源花园三期桩基检测工程");
        myNewsBean.setNewsCircile(R.drawable.news_circle_s);
        myNewsBean.setNewsCount("1");

        MyNewsBean myNewsBean2 = new MyNewsBean();
        myNewsBean2.setNewsImage(R.drawable.join_icon);
        myNewsBean2.setNewsTitle("兰江集团参与悬赏");
        myNewsBean2.setNewProjectName("中铁·溪源花园三期桩基检测工程");
        myNewsBean2.setNewsCircile(R.drawable.news_circle_m);
        myNewsBean2.setNewsCount("36");

        MyNewsBean myNewsBean3 = new MyNewsBean();
        myNewsBean3.setNewsImage(R.drawable.zhongbiao_icon);
        myNewsBean3.setNewsTitle("兰江集团中标");
        myNewsBean3.setNewProjectName("中铁·溪源花园三期桩基检测工程");
        myNewsBean3.setNewsCircile(R.drawable.news_circle_s);
        myNewsBean3.setNewsCount("1");

        MyNewsBean myNewsBean4 = new MyNewsBean();
        myNewsBean4.setNewsImage(R.drawable.cuoshi_icon);
        myNewsBean4.setNewsTitle("兰江集团错失");
        myNewsBean4.setNewProjectName("中铁·溪源花园三期桩基检测工程");
        myNewsBean4.setNewsCircile(R.drawable.news_circle_s);
        myNewsBean4.setNewsCount("1");

        data.add(myNewsBean);
        data.add(myNewsBean2);
        data.add(myNewsBean3);
        data.add(myNewsBean4);

        CommonAdapter<MyNewsBean> commonAdapter = new CommonAdapter<MyNewsBean>(this, data, R.layout.my_news_item) {
            @Override
            public void convert(ViewHolder helper, MyNewsBean item) {
                helper.setImageResource(R.id.iv_news_icon, item.getNewsImage());
                helper.setImageResource(R.id.iv_news_circle, item.getNewsCircile());
                helper.setText(R.id.tv_news_count, item.getNewsCount());
                helper.setText(R.id.tv_news_title, item.getNewsTitle());
                helper.setText(R.id.tv_news_project_name, item.getNewProjectName());
            }
        };

        lvMyNews.setAdapter(commonAdapter);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.my_news));
    }

}
