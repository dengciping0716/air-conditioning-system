package com.dengciping.ydroid.airconditioningsystem.common;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/6 上午9:56
 */
public abstract class BaseFragment<B extends ViewDataBinding, P extends IPresent> extends XFragment<B, P> {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public P newP() {
        return null;
    }
}
