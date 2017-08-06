package com.dengciping.ydroid.airconditioningsystem.common;

import android.os.Bundle;

import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/6 上午9:56
 */
public class BaseFragment<T extends IPresent> extends XFragment<T> {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public T newP() {
        return null;
    }
}
