package com.lanjiang.figersland.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.lanjiang.figersland.utils.LogUtils;

/**
 * 屏蔽 滑动事件
 */
public class MyScrollView extends NestedScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;
    private Context context;

    public final static int ACTION_SCROLL_TOP = -1;
    public final static int ACTION_SCROLL_BOTTOM = 1;
    public final static int ACTION_SCROLL_UP = 10;
    public final static int ACTION_SCROLL_STOP = 11;
    public final static int ACTION_SCROLL_DOWN = 12;

    private final static String TAG_ACTION = "ACTION";
    private final static String TAG_SCROLL_Y = "SCROLL_Y";
    private final static int HANDLE_WHAT_ACTION = 1;
    private final static int HANDLE_WHAT_TIME = 2;
    private final static int msg = 0;
    private long delayedTime = 0;
    private boolean msgLock;

    private int lastScrollY = 0;
    private ScrollHandler handler = new ScrollHandler();

    private OnScrollActionListener onScrollActionListener;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setFillViewport(true);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.context = context;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    public interface OnScrollActionListener {
        void onScroll(int action, int scrollY);
    }

    private class ScrollHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HANDLE_WHAT_ACTION) {
                int action = msg.getData().getInt(TAG_ACTION);
                int scrollY = msg.getData().getInt(TAG_SCROLL_Y);
                onScrollActionListener.onScroll(action, scrollY);
                msgLock = true;
                handler.sendEmptyMessageDelayed(HANDLE_WHAT_TIME, delayedTime);
            } else if (msg.what == HANDLE_WHAT_TIME) {
                msgLock = false;
                lastScrollY = getScrollY();
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        checkScrollAction();
    }

    private void checkScrollAction() {
        if (onScrollActionListener == null || msgLock)
            return;
        if (handler == null)
            handler = new ScrollHandler();
        int action = -0x1024;
        int scrollY = getScrollY();
        View childView = getChildAt(0);
        if (getScrollY() <= 0) {
            action = ACTION_SCROLL_TOP;
        } else if (childView != null && childView.getHeight() <= scrollY + getHeight()) {
            action = ACTION_SCROLL_BOTTOM;
        } else if (scrollY - lastScrollY < 0) {
            action = ACTION_SCROLL_UP;
        } else if (scrollY - lastScrollY > 0) {
            action = ACTION_SCROLL_DOWN;
        } else if (scrollY - lastScrollY == 0) {
            action = ACTION_SCROLL_STOP;
        }
        if (action != -0x1024) {
            Message msg = new Message();
            msg.what = HANDLE_WHAT_ACTION;
            Bundle bundle = new Bundle();
            bundle.putInt(TAG_ACTION, action);
            bundle.putInt(TAG_SCROLL_Y, scrollY);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
//        LogUtils.e("ActionScrollView", "ScrollY : " + getScrollY() + "lastScrollY : " + lastScrollY);
        lastScrollY = getScrollY();
    }

    public void setActionDelayedTime(long delayedTime) {
        this.delayedTime = delayedTime;
    }

    @Override
    protected void onDetachedFromWindow() {
        try {
            LogUtils.e("ScrollView", " onDetachedFromWindow() ");
            handler.removeMessages(0);
            handler = null;
        } catch (Exception e) {

        }
        super.onDetachedFromWindow();
    }

    public void setOnScrollActionListener(OnScrollActionListener listener) {
        this.onScrollActionListener = listener;
    }

}
