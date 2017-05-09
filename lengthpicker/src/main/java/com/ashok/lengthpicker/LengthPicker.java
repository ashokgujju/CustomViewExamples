package com.ashok.lengthpicker;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ashok on 9/5/17.
 */

public class LengthPicker extends LinearLayout implements View.OnClickListener {
    private static final String KEY_SUPER_STATE = "superState";
    private static final String KEY_NUM_INCHES = "numInches";
    private TextView mTextView;
    private Button mMinusBtn;
    private int mNumInches = 0;
    private OnChangeListener onChangeListener = null;

    public LengthPicker(Context context) {
        super(context);
        init();
    }

    public LengthPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.length_picker, this);
        setOrientation(LinearLayout.HORIZONTAL);

        mTextView = (TextView) findViewById(R.id.length);
        mMinusBtn = (Button) findViewById(R.id.minus);
        mMinusBtn.setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState());
        bundle.putInt(KEY_NUM_INCHES, mNumInches);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mNumInches = bundle.getInt(KEY_NUM_INCHES);
            super.onRestoreInstanceState(bundle.getParcelable(KEY_SUPER_STATE));
        } else {
            super.onRestoreInstanceState(state);
        }

        updateView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plus:
                mNumInches++;
                if (onChangeListener != null) {
                    onChangeListener.onChange(mNumInches);
                }
                updateView();
                break;
            case R.id.minus:
                mNumInches--;
                if (onChangeListener != null) {
                    onChangeListener.onChange(mNumInches);
                }
                updateView();
                break;
        }
    }

    public int getmNumInches() {
        return mNumInches;
    }

    private void updateView() {
        int feet = mNumInches / 12;
        int inches = mNumInches % 12;

        String text = String.format("%d' %d\"", feet, inches);
        if (feet == 0) {
            text = String.format("%d\"", inches);
        } else {
            if (inches == 0) {
                text = String.format("%d'", feet);
            }
        }
        mTextView.setText(text);
        mMinusBtn.setEnabled(mNumInches > 0);
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    public interface OnChangeListener {
        void onChange(int numInches);
    }
}