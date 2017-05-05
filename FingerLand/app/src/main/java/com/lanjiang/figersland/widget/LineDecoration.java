package com.lanjiang.figersland.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lanjiang.figersland.R;

/**
 * Created by wnw on 16-5-22.
 */

public class LineDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private Drawable mDivider;
    private Drawable mDefault;
    private int mOrientation;
    private int childStart;
    private int childEnd;
    /**
     * item之间分割线的size，默认为1
     */
    private int mItemSize = 1;
    //默认左边距
    public static final int DEFAULT_GAP = R.dimen.dp_12;
    public static final int DEFAULT_LINE_RES = R.drawable.shape_divider;
    public static final int DEFAULT_WHITE = R.drawable.shape_white;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    public static final int Default = -1;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int LEFT_RIGHT = 2;
    /**
     * 开始分割线位置
     */
    private int mStartPos;

    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    public static final int[] ATRRS = new int[]{
            android.R.attr.listDivider
    };

    public LineDecoration(Context context, int orientation) {
        this(context, orientation, DEFAULT_LINE_RES, Default);
    }

    /**
     * @param childType 左、右边距类型
     * @param childType 开始分割线位置
     */
    public LineDecoration(Context context, int orientation, int childType, boolean startFromZero) {
        this(context, orientation,childType);
        mStartPos = startFromZero ?  0 : 1;
    }

    /**
     * @param childType 是否增加默认左、右边距
     */
    public LineDecoration(Context context, int orientation, int childType) {
        this(context, orientation, DEFAULT_LINE_RES, childType);
    }

    /**
     * @param drawableRes 修改间隔样式
     * @param childType   左、右边距类型
     */
    public LineDecoration(Context context, int orientation, int drawableRes, int childType) {
        this(context, orientation, drawableRes, childType, DEFAULT_WHITE);
    }

    /**
     * @param drawableRes 修改间隔样式
     * @param childType   左、右边距类型
     */
    public LineDecoration(Context context, int orientation, int drawableRes, int childType, int backRes) {
        this.mContext = context;
        mDefault = context.getResources().getDrawable(backRes);
        setOrientation(orientation);
        this.mDivider = context.getResources().getDrawable(drawableRes);
        switch (childType) {
            case LEFT:
                childStart = context.getResources().getDimensionPixelOffset(DEFAULT_GAP);
                break;
            case RIGHT:
                childEnd = context.getResources().getDimensionPixelOffset(DEFAULT_GAP);
                break;
            case LEFT_RIGHT:
                childStart = context.getResources().getDimensionPixelOffset(DEFAULT_GAP);
                childEnd = childStart;
                break;
            default:
                childStart = 0;
                childEnd = 0;
                break;
        }
    }

    //设置屏幕的方向
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST) {
            drawVerticalLine(c, parent, state);
        } else {
            drawHorizontalLine(c, parent, state);
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = mStartPos; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left + childStart, top, right - childEnd, bottom);
            mDivider.draw(c);
            mDefault.setBounds(left, top, +childStart, bottom);
            mDefault.draw(c);
            mDefault.setBounds(right - childEnd, top, +right, bottom);
            mDefault.draw(c);
            //Log.d("wnw", left + " " + top + " "+right+"   "+bottom+" "+i);
        }
    }

    //画竖线
    public void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = mStartPos; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top + childStart, right, bottom - childEnd);
            mDivider.draw(c);
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST) {
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, mItemSize, 0);
        } else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, 0, mItemSize);
        }
    }
}
