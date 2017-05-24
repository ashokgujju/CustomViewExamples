package com.ashok.pizzaview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ashok on 24/5/17.
 */

public class Pizza extends View {
    private Paint paint;
    private int numWedges = 5;

    public Pizza(Context context) {
        super(context);
        init(context, null);
    }

    public Pizza(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Pizza(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int strokeWidth = 4;
        int color = Color.YELLOW;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Pizza);
            strokeWidth = array.getDimensionPixelSize(R.styleable.Pizza_stroke_width, strokeWidth);
            color = array.getColor(R.styleable.Pizza_color, color);
            numWedges = array.getInteger(R.styleable.Pizza_num_wedges, numWedges);
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        int cx = width / 2 + getPaddingLeft();
        int cy = height / 2 + getPaddingTop();

        float radius = (Math.min(width, height) - paint.getStrokeWidth()) / 2;
        canvas.drawCircle(cx, cy, radius, paint);

        canvas.save();
        float degrees = 360f / numWedges;
        for (int i = 0; i < numWedges; i++) {
            canvas.rotate(degrees, cx, cy);
            canvas.drawLine(cx, cy, cx, cy - radius, paint);
        }
        canvas.restore();
    }
}
