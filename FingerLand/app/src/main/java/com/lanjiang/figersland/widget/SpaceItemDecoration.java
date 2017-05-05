package com.lanjiang.figersland.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * GridLayoutManager间距设置
 * Created by Asus on 2016/12/29.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int spanCount;

    /**
     * @param space 间距
     * @param spanCount The number of columns in the grid
     */
    public SpaceItemDecoration(int space, int spanCount) {
        this.space = space;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
        //由于每行都只有spanCount个，所以第一个都是spanCount的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) % spanCount == 0) {
            outRect.left = 0;
        }
    }

}
