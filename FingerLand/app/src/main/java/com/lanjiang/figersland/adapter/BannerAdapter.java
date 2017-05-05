package com.lanjiang.figersland.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * 无限循环Banner，适用于第三方Indicator
 * 当循环到最大时，切换为最小值，反之亦然。
 * Created by Asus on 2017/2/28.
 */

public class BannerAdapter extends PagerAdapter implements View.OnTouchListener, Runnable, ViewPager.OnPageChangeListener {

    private static final int DEFAULT_DELAY_MILLIS = 2000;
    private static final String TAG = "BannerAdapter";

    private Context mContext;
    private List<View> mViewList;
    private ViewPager mViewPager;
    private CirclePageIndicator indicator;

    private final Handler mHandler;
    private boolean mRunning;
    private int mDelayMillis = DEFAULT_DELAY_MILLIS;

    public BannerAdapter(final Context mContext, List<View> lists, ViewPager viewpager, CirclePageIndicator indicator) {

        this.mContext = mContext;
        this.mViewList = lists;
        this.mViewPager = viewpager;
        this.indicator = indicator;
        mHandler = new Handler();

        init();
    }

    private void init() {
        mViewPager.setOnTouchListener(this);
        indicator.setOnPageChangeListener(this);
        mViewPager.setOnPageChangeListener(this);

        stop();
        start();
    }

    /**
     * 设置数据，并刷新
     *
     * @param lists 。
     */
    public void setDatas(List<View> lists) {
        this.mViewList = lists;
        notifyDataSetChanged();
    }

//    private List<View> newData(List<View> lists) {
//        List<View> newList = new ArrayList<>();
//        //如果数据大于一条
//        if (lists != null && lists.size() > 1) {
//            newList.addAll(lists);
//            //添加最后一页到第一页
//            newList.add(0, lists.get(lists.size() - 1));
//            //添加第一页(经过上行的添加已经是第二页了)到最后一页
//            newList.add(lists.get(1));
//        }
//        return newList;
//    }

    /**
     * 设置轮播延时
     *
     * @param delayMillis 时间/毫秒
     */
    public void setDelayMillis(int delayMillis) {
        this.mDelayMillis = delayMillis;
        if (delayMillis <= 0) {
            mDelayMillis = DEFAULT_DELAY_MILLIS;
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mViewList.size();//页卡数
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方推荐写法
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = mViewList.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "position - " + position);
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });
        container.addView(view);//添加页卡
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));//删除页卡
    }

    /**
     * start loop
     */
    public void start() {
        if (!mRunning) {
            post();
            mRunning = true;
        }
    }

    /**
     * stop loop
     */
    public void stop() {
        if (mRunning) {
            mHandler.removeCallbacks(this);
            mRunning = false;
        }
    }

    private void post() {
        mHandler.postDelayed(this, mDelayMillis);
    }

    @Override
    public final boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                stop();
                break;
            case MotionEvent.ACTION_UP:
                start();
                break;
        }
        return false;
    }

    @Override
    public void run() {

        int currentPosition = mViewPager.getCurrentItem();
        //交替循环算法
        if (0 <= currentPosition && currentPosition <= mViewList.size() - 1) {
            if (currentPosition + 1 == mViewList.size()) {
                currentPosition = 0;
            } else {
                currentPosition++;
            }
            mViewPager.setCurrentItem(currentPosition, true);

            post();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        LogUtils.i(TAG, "--onPageSelected--" + position);
        currentPosition = position;
    }

    boolean isDragging = false;
    private int currentPosition;

    @Override
    public void onPageScrollStateChanged(int state) {
        LogUtils.e(TAG, "--onPageScrollStateChanged--" + state);
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            if (currentPosition == mViewPager.getAdapter().getCount() - 1) {
                mViewPager.setCurrentItem(1, false);
            } else if (currentPosition == 0) {
                mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 2, false);
            }
        }
//        switch (state) {
//            case ViewPager.SCROLL_STATE_IDLE:
//                if (isDragging) {
//                    int max = mViewPager.getAdapter().getCount() - 1;
//                    int currentPosition = mViewPager.getCurrentItem();
//                    if (currentPosition == max) {
//                        currentPosition = 0;
//                    } else if (currentPosition == 0) {
//                        currentPosition = max;
//                    }
//                    mViewPager.setCurrentItem(currentPosition, false);
//                }
//                break;
//            case ViewPager.SCROLL_STATE_DRAGGING:
//                isDragging = true;
//                break;
//            case ViewPager.SCROLL_STATE_SETTLING:
//                isDragging = false;
//                break;
//        }
    }
}
