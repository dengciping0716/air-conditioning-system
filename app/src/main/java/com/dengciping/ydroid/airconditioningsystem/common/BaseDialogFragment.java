package com.dengciping.ydroid.airconditioningsystem.common;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.XDialogFragment;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/6 下午7:02
 */
public abstract class BaseDialogFragment<B extends ViewDataBinding, P extends IPresent> extends XDialogFragment<B, P> {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public P newP() {
        return null;
    }
}
