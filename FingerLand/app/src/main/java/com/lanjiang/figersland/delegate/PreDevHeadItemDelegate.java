package com.lanjiang.figersland.delegate;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.BannerLoopAdapter;
import com.lanjiang.figersland.bean.BannerItem;
import com.lanjiang.figersland.ui.MasterActivity;
import com.lanjiang.figersland.ui.PapersActivity;
import com.lanjiang.figersland.ui.YipaiActivity;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 前期开发头部布局
 * Created by Asus on 2017/2/20.
 */
public class PreDevHeadItemDelegate implements ItemViewDelegate {

    private Context mContext;
    private List<BannerItem> mList;
    private BannerLoopAdapter adapter;

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.dots_layout)
    LinearLayout mDots;

    @OnClick({R.id.tv_zhengjian, R.id.tv_gaoren, R.id.tv_yipai})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_zhengjian:
                mContext.startActivity(new Intent(mContext, PapersActivity.class));
                break;
            case R.id.tv_gaoren:
                mContext.startActivity(new Intent(mContext, MasterActivity.class));
                break;
            case R.id.tv_yipai:
                mContext.startActivity(new Intent(mContext, YipaiActivity.class));
                break;
            default:
                break;
        }
    }

    public PreDevHeadItemDelegate(Context mContext, List<BannerItem> integers) {
        this.mContext = mContext;
        this.mList = integers;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.activity_pre_development_head;
    }

    @Override
    public boolean isForViewType(Object item, int position) {
        return position == 0;
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {

        View view = holder.getView(R.id.activity_pre_development_head);
        ButterKnife.bind(this, view);

        initViewPager(viewpager);

    }

    private void initViewPager(ViewPager viewpager) {
        adapter = new BannerLoopAdapter(mContext, mList, viewpager, mDots);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(1);//重要必须设置（应为前后都增加了1项）
    }

    public BannerLoopAdapter getAdapter() {
        return adapter;
    }


}
