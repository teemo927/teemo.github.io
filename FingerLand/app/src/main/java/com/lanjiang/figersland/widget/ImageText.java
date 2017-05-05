package com.lanjiang.figersland.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.R;

/**
 * Created by Asus on 2016/12/12.
 */

public class ImageText extends LinearLayout {


    private static int textDefaultColor = R.color.word_important;
    private static int textDefaultSize = R.dimen.sp_16;
    private static int viewDefaultColor = R.color.color_main;

    /**
     * 左侧颜色
     */
    private View mView;
    /**
     * 标题
     */
    private TextView mTv;

    public ImageText(Context context) {
        this(context, null);
    }

    public ImageText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_text, this);
        mView = findViewById(R.id.view);
        mTv = (TextView) findViewById(R.id.tv);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageBtnWithText);

        CharSequence text = a.getText(R.styleable.ImageBtnWithText_android_text);
        if (text != null) mTv.setText(text);

        float textSize = a.getDimension(R.styleable.ImageBtnWithText_android_textSize, getResources().getDimension(textDefaultSize));
        if (textSize != 0) mTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        int textColor = a.getColor(R.styleable.ImageBtnWithText_android_textColor, getResources().getColor(textDefaultColor));
        if (textColor != 0) mTv.setTextColor(textColor);

        int color = a.getColor(R.styleable.ImageBtnWithText_android_color, getResources().getColor(viewDefaultColor));
        if (color != 0) mView.setBackgroundColor(color);

        a.recycle();
    }

    /**
     * 设置左边view 颜色
     *
     * @param color 颜色
     */
    public void setViewBackground(int color) {
        mView.setBackgroundColor(color);
    }

    /**
     * 设置标题
     *
     * @param text 标题
     */
    public void setText(String text) {
        mTv.setText(text);
    }

    public void setTextColor(int color) {
        mTv.setTextColor(color);
    }

    public void setTextSize(float size) {
        mTv.setTextSize(size);
    }

}
