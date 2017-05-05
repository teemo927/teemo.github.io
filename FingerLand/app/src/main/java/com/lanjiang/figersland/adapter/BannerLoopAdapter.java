package com.lanjiang.figersland.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.bean.BannerItem;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 无限循环Banner， 适用于自定义圆点（LinearLayout）
 * 当循环到最大时，切换为最小值，反之亦然。
 * Created by Asus on 2017/2/28.
 */

public class BannerLoopAdapter extends PagerAdapter implements View.OnTouchListener, Runnable, ViewPager.OnPageChangeListener {

    private static final int DEFAULT_DELAY_MILLIS = 2000;

    private static final String TAG = "BannerAdapter";

    private Context mContext;

    private ViewPager mViewPager;

    private LinearLayout mDots;

    //管理图片的
    private List<View> mViewList;

    //管理圆点的
    private List<ImageView> dots;

    // 记录圆点上一次点的位置
    private int oldPosition = 0;

    private final Handler mHandler;

    private boolean mRunning;

    private int mDelayMillis = DEFAULT_DELAY_MILLIS;

    public BannerLoopAdapter(final Context mContext, List<BannerItem> lists, ViewPager viewpager, LinearLayout mDots) {

        this.mContext = mContext;
        this.mViewPager = viewpager;
        this.mDots = mDots;

        mHandler = new Handler();
        init();
        refreshData(lists);
    }

    private void init() {
        mViewList = new ArrayList<>();
        dots = new ArrayList<>();

        mViewPager.setOnTouchListener(this);
        mViewPager.setOnPageChangeListener(this);

        stop();
        start();
    }

    private void refreshData(List<BannerItem> list) {
        mViewList.clear();
        dots.clear();

        //增加首尾（用于过度效果）
        list = newData(list);
        for (int i = 0; i < list.size(); i++) {
            View root = new ImageView(mContext);
            root.setBackgroundResource(list.get(i).getId());
            mViewList.add(root);
        }

        //圆点比实际数少两个
        for (int i = 0; i < mViewList.size() - 2; i++) {
            ImageView iv = new ImageView(mContext);
            setParam(iv, false);

            dots.add(iv);

            mDots.addView(iv);
        }
        //因为开始页面不响应页面滑动，所以先把开始页面设置为选中背景, 并变大，setBackgroundResource不可以
        setParam(dots.get(0), true);
    }

    /**
     * 设置数据，并刷新
     *
     * @param lists 。
     */
    public void setData(List lists) {
        refreshData(lists);
        notifyDataSetChanged();
    }

    /**
     * 前后各插入一条
     * @param lists
     * @return
     */
    private List newData(List lists) {
        //如果数据大于一条
        if (lists != null && lists.size() > 1) {
            //添加最后一页到第一页
            lists.add(0, lists.get(lists.size() - 1));
            //添加第一页(经过上行的添加已经是第二页了)到最后一页
            lists.add(lists.get(1));
        }
        return lists;
    }

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
                        //不会再点击
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
        //可切换下一页的偏移（0.4和0.6）
        float truncator = position >= mViewPager.getCurrentItem() ? -0.1f : 0.1f;
        float offset = position + positionOffset;
        // 偏移一半可以移动圆点（实际0.4和0.6）
        int pos = Math.round(offset + truncator);

        //圆点新位置
        int newDotPosition = oldPosition;
        if (pos != 0 && pos != mViewPager.getAdapter().getCount() - 1) {
            //索引减少（头部增加了一个）
            newDotPosition = pos - 1;
        } else if (pos == 0) {
            //切换到最后一个
            newDotPosition = dots.size() - 1;
        } else if (pos == mViewPager.getAdapter().getCount() - 1) {
            //切换到第一个
            newDotPosition = 0;
        }

        setParam(dots.get(oldPosition), false);//更改旧的
        setParam(dots.get(newDotPosition), true);
        oldPosition = newDotPosition;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            //viewpager当前位置
            int currentItem = mViewPager.getCurrentItem();

            if (currentItem == mViewPager.getAdapter().getCount() - 1) {
                //尾——>头
                currentItem = 1;
            } else if (currentItem == 0) {
                //头——>尾
                currentItem = mViewPager.getAdapter().getCount() - 2;
            }
            mViewPager.setCurrentItem(currentItem, false);

            //此处圆点刷新较慢
//            setParam(dots.get(oldPosition), false);//更改旧的
//            setParam(dots.get(currentItem - 1), true);//刷新新的
//            oldPosition = currentItem - 1;

        }
    }

    /**
     * 设置圆点参数
     *
     * @param view 圆点
     * @param big  是否选中状态，
     */
    private void setParam(ImageView view, boolean big) {
        int dotSize = (int) mContext.getResources().getDimension(R.dimen.dp_10);
        int dotBigSize = (int) mContext.getResources().getDimension(R.dimen.dp_12);
        int dotMargin = (int) mContext.getResources().getDimension(R.dimen.dp_4);
        LinearLayout.LayoutParams params;
        if (big) {
            params = new LinearLayout.LayoutParams(dotBigSize, dotBigSize);
            int newMar = dotMargin - (dotBigSize - dotSize) / 2;
            params.setMargins(newMar, 0, newMar, 0);
            view.setImageResource(R.drawable.shape_dot_select);
        } else {
            params = new LinearLayout.LayoutParams(dotSize, dotSize);
            params.setMargins(dotMargin, 0, dotMargin, 0);
            view.setImageResource(R.drawable.shape_dot_normal);
        }
        view.setLayoutParams(params);
    }
}
