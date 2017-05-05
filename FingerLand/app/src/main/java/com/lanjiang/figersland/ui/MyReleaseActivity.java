package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.MyReleaseAdapter;
import com.lanjiang.figersland.fragment.MyReleaseFragment;
import com.lanjiang.figersland.widget.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lanjiang.figersland.R.id.vp_mine_content;

/**
 * 我的发布
 * Created by Lin on 2016/12/28.
 */

public class MyReleaseActivity extends BaseToolbarActivity {
    @BindView(vp_mine_content)
    ViewPager vpMineContent;
    @BindView(R.id.indicator)
    TabPageIndicator indicator;

    private List<Fragment> data = new ArrayList<>();
    private String[] tabs;
    private String title;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("title");
        tabs = getIntent().getStringArrayExtra("tabs");
        setContentView(R.layout.activity_my_release);
        super.unbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(title);
    }

    /**
     * 初始化方法
     */
    private void initView() {
        for (int i = 0; i < 5; i++) {
            MyReleaseFragment myReleaseFragment = new MyReleaseFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            bundle.putString("title", title);
            myReleaseFragment.setArguments(bundle);
            data.add(myReleaseFragment);

            MyReleaseAdapter adapter = new MyReleaseAdapter(getSupportFragmentManager(), data, tabs);
            vpMineContent.setAdapter(adapter);
            indicator.setViewPager(vpMineContent);
            adapter.notifyDataSetChanged();
        }
    }

}
