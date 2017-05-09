package com.ashok.lengthpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LengthPicker.OnChangeListener {

    private LengthPicker mWidthPicker, mHeightPicker;
    private TextView mArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArea = (TextView) findViewById(R.id.area);
        mWidthPicker = (LengthPicker) findViewById(R.id.width_picker);
        mHeightPicker = (LengthPicker) findViewById(R.id.height_picker);

        mWidthPicker.setOnChangeListener(this);
        mHeightPicker.setOnChangeListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateArea();
    }

    private void updateArea() {
        int area = mWidthPicker.getmNumInches() * mHeightPicker.getmNumInches();
        mArea.setText(area + " sq in");
    }

    @Override
    public void onChange(int numInches) {
        updateArea();
    }
}