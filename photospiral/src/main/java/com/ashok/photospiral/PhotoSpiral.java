package com.ashok.photospiral;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ashok on 23/5/17.
 */

public class PhotoSpiral extends ViewGroup {
    public PhotoSpiral(Context context) {
        super(context);
    }

    public PhotoSpiral(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoSpiral(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        View first = getChildAt(0);
        int size = first.getMeasuredWidth() + first.getMeasuredHeight();

        int width = ViewGroup.resolveSize(size, widthMeasureSpec);
        int height = ViewGroup.resolveSize(size, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            int x = 0;
            int y = 0;

            switch (i) {
                case 0:
                    x = childHeight;
                    y = 0;
                    break;
                case 1:
                    x = childHeight;
                    y = childWidth;
                    break;
                case 2:
                    x = 0;
                    y = childWidth;
                    break;
                case 3:
                    x = 0;
                    y = 0;
                    break;

            }

            child.layout(x, y, x + childWidth, y + childHeight);
        }
    }
}
