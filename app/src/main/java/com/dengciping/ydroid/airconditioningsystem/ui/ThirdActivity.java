package com.dengciping.ydroid.airconditioningsystem.ui;

import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.common.BaseActivity;

/**
 * 空调控制界面容器
 */
public class ThirdActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_third;
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);

        replace(ControlFragment.newInstance(1), R.id.contentPanel);
    }
}
