package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.AnimUtils;
import com.lanjiang.figersland.utils.SizeUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 证件争霸
 */
public class PapersActivity extends BaseToolbarActivity {

    private List<String> mDataList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    TextView fab;
    private int mStartY;

    @OnClick(R.id.fab)
    public void click(View view) {
        startActivity(new Intent(mContext, AddPaperActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.papers_foremost);
        setRight(R.drawable.selector_search_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSearchAction();
            }
        });
    }

    private void initView() {

        setDataList();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        CommonAdapter adapter = new CommonAdapter<String>(mContext, R.layout.adapter_papers, mDataList) {
            @Override
            protected void convert(ViewHolder holder, String s, final int position) {
                holder.setText(R.id.tv_msg, s);
                holder.setOnClickListener(R.id.tv_detail, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, PapersDetailActivity.class));
                    }
                });
                holder.setOnClickListener(R.id.adapter_papers, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, PapersDetailActivity.class));
                    }
                });
            }
        };

        EmptyWrapper mEmptyWrapper = new EmptyWrapper(adapter);
        mEmptyWrapper.setEmptyView(R.layout.empty_view);

        recyclerView.setAdapter(mEmptyWrapper);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mScrollThreshold = 5;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
                if (isSignificantDelta) {
                    if (dy > 0) {
                        onScrollUp();
                    } else {
                        onScrollDown();
                    }
                }
            }

            public void setScrollThreshold(int scrollThreshold) {
                mScrollThreshold = scrollThreshold;
            }

            /**
             * 下滑监听
             */
            private void onScrollDown() {
                AnimUtils.executeAnimation(true, fab, recyclerView);
                AnimUtils.animatorShow(mContext, fab, mStartY);
            }

            /**
             * 上滑监听
             */
            private void onScrollUp() {
                int move = SizeUtils.dp2px(mContext, 40) + fab.getHeight();
                AnimUtils.animatorHide(mContext, fab, move,mStartY);
            }
        });
//        mPullRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
//
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
//                Toast.makeText(mContext, "Pull Down!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
//                Toast.makeText(mContext, "Pull Up!", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void setDataList() {
        mDataList = new ArrayList<>();
        mDataList.add("已提交所有资料，但久久不可得，求帮忙");
        mDataList.add("华侨城项目，已拿地，土地使用权证办太久哪位兄弟搭把手华侨城项目，已拿地，土地使用权证办太久哪位兄弟搭把手华侨城项目，已拿地，土地使用权证办太久哪位兄弟搭把手华侨城项目，已拿地，土地使用权证办太久哪位兄弟搭把手");
        mDataList.add("已提交所有资料，但久久不可，求帮忙");
        mDataList.add("已提交所有资料，但久久不可，求帮忙");
        mDataList.add("已提交所有资料，但久久不可，求帮忙");
        mDataList.add("华侨城项目，已拿地，土地使用权证办太久哪位兄弟搭把手");
        mDataList.add("华侨城项目，已拿地，土地使用权证办太久哪位兄弟搭把手");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mStartY = AnimUtils.getCurrentFabY(fab);
        super.onWindowFocusChanged(hasFocus);
    }

}
