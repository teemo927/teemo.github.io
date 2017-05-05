package com.lanjiang.figersland.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanjiang.figersland.R;

/**
 * Created by Lin on 2017/2/9.
 */

public class WidgetUtils {
    /**
     * 改变控件可见性
     *
     * @param view
     */
    public static void changeVisibility(View view) {
        if (view == null) {
            return;
        }
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 改变选中图片
     *
     * @param imageView
     */
    public static void changeCheckedImage(Context context, ImageView imageView, int imgCheckedRes,
                                          int imgUncheckedRes) {
        if (imageView == null || imgCheckedRes == 0 || imgUncheckedRes == 0) {
            return;
        }
        // 控件图片
        Drawable.ConstantState drawable1 = imageView.getDrawable().getConstantState();
        // 对比图片
        Drawable.ConstantState drawable2 = context.getResources().getDrawable(imgCheckedRes).
                getConstantState();
        if (drawable1 == drawable2) {
            imageView.setImageResource(imgUncheckedRes);
        } else {
            imageView.setImageResource(imgCheckedRes);
        }
    }

    /**
     * 改变按钮背景和文字颜色
     *
     * @param context
     * @param tvChange
     * @param clickResBg
     * @param defaultResBg
     */
    public static void changeCheckedButton(Context context, TextView tvChange, int clickResBg,
                                           int defaultResBg) {
        if (tvChange == null || clickResBg == 0 || defaultResBg == 0) {
            return;
        }
        // 控件背景
        Drawable.ConstantState drawable1 = tvChange.getBackground().getConstantState();
        // 对比背景
        Drawable.ConstantState drawable2 = context.getResources().getDrawable(clickResBg)
                .getConstantState();
        if (drawable1 == drawable2) {
            tvChange.setBackgroundResource(defaultResBg);
            tvChange.setTextColor(context.getResources().getColor(R.color.text_gray));
        } else {
            tvChange.setBackgroundResource(clickResBg);
            tvChange.setTextColor(context.getResources().getColor(R.color.color_main));
        }
    }
}
