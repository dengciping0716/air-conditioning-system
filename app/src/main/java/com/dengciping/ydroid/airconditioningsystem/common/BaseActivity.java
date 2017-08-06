package com.dengciping.ydroid.airconditioningsystem.common;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.MotionEvent;

import com.dengciping.ydroid.airconditioningsystem.App;
import com.dengciping.ydroid.airconditioningsystem.utils.UI;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.XActivity;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/2 下午2:42
 */
public abstract class BaseActivity<B extends ViewDataBinding, T extends IPresent> extends XActivity<B, T> {

    @Override
    protected void onResume() {
        super.onResume();
        if (App.isExit) {
            finish();
        }
        UI.hideSystemUI(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            UI.hideSystemUI(this);
        }
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        UI.hideSystemUI(this);
        return super.onGenericMotionEvent(event);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public T newP() {
        return null;
    }

}
