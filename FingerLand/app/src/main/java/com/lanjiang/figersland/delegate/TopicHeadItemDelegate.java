package com.lanjiang.figersland.delegate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.BannerAdapter;
import com.lanjiang.figersland.adapter.BannerLoopAdapter;
import com.lanjiang.figersland.adapter.MainPagerAdapter;
import com.lanjiang.figersland.bean.BannerItem;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.utils.PopUtils;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.CirclePageIndicator;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 话题头部布局
 * Created by Asus on 2017/2/20.
 */
public class TopicHeadItemDelegate implements ItemViewDelegate{

    private static String TAG = "TopicHeadItemDelegate";
    private Context mContext;
    private List<BannerItem> mViewList;

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.dots_layout)
    LinearLayout mDotsLayout;
    @BindView(R.id.tv_publish_time)
    TextView tvPublishTime;
    @BindView(R.id.tv_zone_filter)
    TextView tvZoneFilter;
    @BindView(R.id.tv_deadline)
    TextView tvDeadline;

    private BannerLoopAdapter adapter;

    public BannerLoopAdapter getAdapter() {
        return adapter;
    }

    @OnClick({R.id.tv_publish_time, R.id.tv_zone_filter, R.id.tv_deadline, R.id.llt_publish_time, R.id.llt_zone_filter, R.id.llt_deadline})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_publish_time:
            case R.id.llt_publish_time:
                switchState(tvPublishTime);
                break;
            case R.id.tv_zone_filter:
            case R.id.llt_zone_filter:
                PopUtils.showCityWindow(LayoutInflater.from(mContext), mContext, new PopUtils.PopClickListener() {
                    @Override
                    public void click(String city) {
                        tvZoneFilter.setText(city);
                    }
                });
                switchState(tvZoneFilter);
                break;
            case R.id.tv_deadline:
            case R.id.llt_deadline:
                switchState(tvDeadline);
                break;
        }
    }

    public TopicHeadItemDelegate(Context mContext, List<BannerItem> mViewList) {
        this.mContext = mContext;
        this.mViewList = mViewList;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_topic_detail_head;
    }

    @Override
    public boolean isForViewType(Object item, int position) {
        return position == 0;
    }

    @Override
    public void convert(ViewHolder holder, Object o, int position) {

        View root = holder.getView(R.id.fragment_topic_detail_head);
        ButterKnife.bind(this, root);

        initViewPager(viewpager);
        switchState(tvPublishTime);

    }

    private void initViewPager(ViewPager viewpager) {
        adapter = new BannerLoopAdapter(mContext, mViewList,viewpager,mDotsLayout);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(1);
    }

    /**
     * 设置当前按钮颜色 并 更换按钮状态
     *
     * @param view 被点击按钮
     */
    private void switchState(TextView view) {
        //重置筛选按钮
        Drawable unfocused = mContext.getResources().getDrawable(R.drawable.fabu_unselect_icon);
        unfocused.setBounds(0, 0, unfocused.getMinimumWidth(), unfocused.getMinimumHeight());
        tvPublishTime.setCompoundDrawables(null, null, unfocused, null);
        tvPublishTime.setTextColor(mContext.getResources().getColor(R.color.text_gray));
        tvDeadline.setCompoundDrawables(null, null, unfocused, null);
        tvDeadline.setTextColor(mContext.getResources().getColor(R.color.text_gray));

        //设置当前点击控件
        Drawable selector = mContext.getResources().getDrawable(R.drawable.selector_bid_text);
        selector.setBounds(0, 0, selector.getMinimumWidth(), selector.getMinimumHeight());
        if (!view.equals(tvZoneFilter)) {//筛选地区不更改
            view.setCompoundDrawables(null, null, selector, null);
            view.setTextColor(mContext.getResources().getColor(R.color.color_main));
        }

        //位置
        Drawable location = mContext.getResources().getDrawable(R.drawable.location_icon);
        location.setBounds(0, 0, location.getMinimumWidth(), location.getMinimumHeight());
        tvZoneFilter.setCompoundDrawables(null, null, location, null);
        tvZoneFilter.setTextColor(mContext.getResources().getColor(R.color.text_gray));
        //设置位置点击
        Drawable selectorLocation = mContext.getResources().getDrawable(R.drawable.selector_location_icon);
        selectorLocation.setBounds(0, 0, selectorLocation.getMinimumWidth(), selectorLocation.getMinimumHeight());
        if (view.equals(tvZoneFilter)) {//筛选地区不更改
            view.setCompoundDrawables(null, null, selectorLocation, null);
            view.setTextColor(mContext.getResources().getColor(R.color.color_main));
        }

        //改变所有状态
        tvPublishTime.setSelected(view.equals(tvPublishTime) ? !tvPublishTime.isSelected() : false);
        tvZoneFilter.setSelected(view.equals(tvZoneFilter));
        tvDeadline.setSelected(view.equals(tvDeadline) ? !tvDeadline.isSelected() : false);

        //根据状态展示功能
        switch (view.getId()) {
            case R.id.tv_publish_time://发布时间
                if (tvPublishTime.isSelected()) {
                    LogUtils.i(TAG, "tvPublishTime , 正排序" + view.getId());
                } else {
                    LogUtils.i(TAG, "tvPublishTime , 反排序" + view.getId());
                }
                break;
            case R.id.tv_zone_filter://地区筛选
                if (tvZoneFilter.isSelected()) {
                    LogUtils.i(TAG, "tvZoneFilter , 正排序" + view.getId());
                } else {
                    LogUtils.i(TAG, "tvZoneFilter , 反排序" + view.getId());
                }
                break;
            case R.id.tv_deadline://截止时间
                if (tvDeadline.isSelected()) {
                    LogUtils.i(TAG, "tvDeadline , 正排序" + view.getId());
                } else {
                    LogUtils.i(TAG, "tvDeadline , 反排序" + view.getId());
                }
                break;
            default:
                break;
        }

    }

}
