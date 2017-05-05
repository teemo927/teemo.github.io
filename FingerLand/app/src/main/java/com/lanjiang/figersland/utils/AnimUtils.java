package com.lanjiang.figersland.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Asus on 2017/1/11.
 */

public class AnimUtils {
    private static final String TAG = "AnimUtils";
    private static final int ANIM_DURATION = 200;// 动画时间

    private static final int STATUS_NORMAL = 1;// 正常状态。无意义
    private static final int STATUS_SHOW = 2;// 显示状态
    private static final int STATUS_DISMISS = 3;// 隐藏状态

    private static int currentStatus = STATUS_NORMAL; // 当前Float Button的状态
    private static boolean isExecutingAnim = false; // 是否正在执行动画

    //
    private static ObjectAnimator animatorHide;
    private static ObjectAnimator animatorShow;

    /**
     * 为Float button执行动画</br>
     *
     * @param show         显示 or 隐藏
     * @param mPostBtn
     * @param recyclerView
     */
    public static void executeAnimation(final boolean show, final View mPostBtn, RecyclerView recyclerView) {

//        if (isListViewEmpty(recyclerView)) {
//            return;
//        }

        if (isExecutingAnim || (show && currentStatus == STATUS_SHOW)
                || (!show && currentStatus == STATUS_DISMISS)) {
            return;
        }
        isExecutingAnim = true;
        int moveDis = ((RelativeLayout.LayoutParams) (mPostBtn.getLayoutParams())).bottomMargin
                + mPostBtn.getHeight();
        Animation animation = null;
        if (show) {
            animation = new TranslateAnimation(0, 0, moveDis, 0);
        } else {
            animation = new TranslateAnimation(0, 0, 0, moveDis);
        }
        animation.setDuration(300);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isExecutingAnim = false;
                if (show) {
                    currentStatus = STATUS_SHOW;
                } else {
                    currentStatus = STATUS_DISMISS;
                }
                mPostBtn.setClickable(show);
            }
        });
        mPostBtn.startAnimation(animation);
    }

    private static boolean isListViewEmpty(RecyclerView recyclerView) {
        // list view中没有数据时不隐藏发布按钮
        return recyclerView == null || recyclerView.getAdapter().getItemCount() == 0;
    }


    /**
     * 隐藏fab按钮的动画
     *
     * @param mContext 。
     * @param fab      控件
     * @param mStartY  控件原始位置
     */
    public static void animatorHide(Context mContext, View fab, int move, int mStartY) {
        if (animatorShow != null && animatorShow.isRunning()) {
            animatorShow.cancel();
        }

        int start = getCurrentFabY(fab) - mStartY;

        animatorHide = ObjectAnimator.ofFloat(fab, "translationY", start, move);
        animatorHide.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorHide.setDuration(ANIM_DURATION).start();
    }

    /**
     * 显示fab按钮的动画
     *
     * @param mContext 。
     * @param fab      控件
     * @param mStartY  控件原始位置
     */
    public static void animatorShow(Context mContext, View fab, int mStartY) {
        if (animatorHide != null && animatorHide.isRunning()) {
            animatorHide.cancel();
        }

        //从当前位置出发
        int move = getCurrentFabY(fab) - mStartY;

        animatorShow = ObjectAnimator.ofFloat(fab, "translationY", move, 0);
        animatorShow.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorShow.setDuration(ANIM_DURATION).start();
    }

    /**
     * 获取控件当前Y位置
     *
     * @param view
     * @return
     */
    public static int getCurrentFabY(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        return y;
    }
}
