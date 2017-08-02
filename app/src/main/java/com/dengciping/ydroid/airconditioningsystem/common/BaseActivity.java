package com.dengciping.ydroid.airconditioningsystem.common;

import com.dengciping.ydroid.airconditioningsystem.utils.UI;

import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/2 下午2:42
 */
public abstract class BaseActivity extends XActivity {

    @Override
    protected void onResume() {
        super.onResume();

        UI.hideSystemUI(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            UI.hideSystemUI(this);
        }
    }
}
