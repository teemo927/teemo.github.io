package com.lanjiang.figersland.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 自定义ViewGroup
 * Created by Asus on 2017/2/16.
 */

public class TestViewGroup extends ViewGroup {

    public TestViewGroup(Context context) {
        this(context,null);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return super.generateLayoutParams(p);
    }

}
