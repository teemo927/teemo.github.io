package com.lanjiang.figersland.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Lin on 2016/12/9.
 */

public class LineEditText extends EditText {

    private Paint paint;

    public LineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        //设置画笔的属性
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**canvas画直线，从左下角到右下角，this.getHeight()-10是获得父edittext的高度，但是必须要-10
         * 这样才能保证画的横线在edittext上面，那样才看得见。
         */
        canvas.drawLine(0, this.getHeight() - 10, this.getWidth() - 10, this.getHeight() - 10, paint);
    }

}
