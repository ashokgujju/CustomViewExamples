package com.ashok.versionview;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by ashok on 9/5/17.
 */

public class VersionView extends android.support.v7.widget.AppCompatTextView {

    public VersionView(Context context) {
        super(context);
        setVersion();
    }

    public VersionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setVersion();
    }

    public VersionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVersion();
    }

    private void setVersion() {
        try {
            PackageInfo packageInfo = getContext().getPackageManager()
                    .getPackageInfo(getContext().getPackageName(), 0);

            setText(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
