package com.lanjiang.figersland.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.lanjiang.figersland.ui.BidActivity;
import com.lanjiang.figersland.ui.MineActivity;
import com.lanjiang.figersland.ui.PreDevelopmentActivity;
import com.lanjiang.figersland.ui.SiteControlActivity;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.utils.ToastUtils;

import java.util.List;

//ViewPager适配器
public class MainPagerAdapter extends PagerAdapter implements View.OnTouchListener ,Runnable{

    private static final int DEFAULT_DELAY_MILLIS = 2000;

    private Context mContext;
    private List<View> mViewList;
    private boolean loop = false;
    private List<String> mTitleList;
    //从主界面传过来
    private ViewPager mViewPager;

    private final Handler mHandler;
    private int mDelayMillis = DEFAULT_DELAY_MILLIS;
    private boolean mRunning;

    public MainPagerAdapter(final Context mContext, List<View> mViewList, ViewPager viewpager) {
        this(mContext, mViewList, viewpager, false);
    }

    public MainPagerAdapter(final Context mContext, List<View> mViewList, ViewPager viewpager, boolean loop) {
        this.mContext = mContext;
        this.mViewList = mViewList;
        this.loop = loop;
        this.mViewPager = viewpager;
        viewpager.setOnTouchListener(this);
        mHandler = new Handler();
        stop();
        start();
    }

    public void setDatas(List<View> mViewList) {
        this.mViewList = mViewList;
        notifyDataSetChanged();
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
        if (!loop) {//主界面不轮播,
            view.setOnClickListener(new MainAdapterItemClickListen(position, mViewPager));
        }
        container.addView(view);//添加页卡
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));//删除页卡
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList == null ? "" : mTitleList.get(position);//页卡标题
    }

    /**
     * 主界面viewpager子项点击事件 ，进入对应页面或者跳转页面
     */
    private class MainAdapterItemClickListen implements View.OnClickListener {
        //当前设置点击事件 的子项索引
        private int current;
        private ViewPager viewpager;

        public MainAdapterItemClickListen(int current, ViewPager viewpager) {
            this.current = current;
            this.viewpager = viewpager;
        }

        @Override
        public void onClick(View v) {

            if (current != viewpager.getCurrentItem()) {
                //当前居中页面和点击页面不一致时，切换页面
                viewpager.setCurrentItem(current, true);
                return;
            }

            switch (current) {
                case 0://前期开发
                    mContext.startActivity(new Intent(mContext, PreDevelopmentActivity.class));
                    break;
                case 1://招投标
                    mContext.startActivity(new Intent(mContext, BidActivity.class));
                    break;
//                case 3://现场管控
//                    mContext.startActivity(new Intent(mContext, SiteControlActivity.class));
//                    break;
                case 2://偶的账户
                    mContext.startActivity(new Intent(mContext, MineActivity.class));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * start loop
     */
    public void start() {
        if (!mRunning &&loop) {
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

    public void setDelayMillis(int delayMillis) {
        this.mDelayMillis = delayMillis;
        if (delayMillis <= 0) {
            mDelayMillis = DEFAULT_DELAY_MILLIS;
        }
    }

    @Override
    public final boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
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
}
