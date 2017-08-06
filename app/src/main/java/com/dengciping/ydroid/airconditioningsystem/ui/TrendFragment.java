package com.dengciping.ydroid.airconditioningsystem.ui;


import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.dengciping.ydroid.airconditioningsystem.R;

import cn.droidlover.xdroidmvp.mvp.XLazyFragment;

/**
 * 一期、二期 趋势界面
 */
public class TrendFragment extends XLazyFragment<ViewDataBinding, TrendPresent> {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static TrendFragment newInstance(String param1, String param2) {
        TrendFragment fragment = new TrendFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_trend;
    }

    @Override
    public TrendPresent newP() {
        return new TrendPresent();
    }
}
