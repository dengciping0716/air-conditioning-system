package com.dengciping.ydroid.airconditioningsystem.ui;


import android.os.Bundle;
import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.databinding.FragmentTrendBinding;

import cn.droidlover.xdroidmvp.mvp.XLazyFragment;

/**
 * 一期、二期 趋势界面
 */
public class TrendFragment extends XLazyFragment<FragmentTrendBinding, TrendPresent> {
    private static final String ARG_TYPE = "param1";
    private int type;


    public static TrendFragment newInstance(int param1) {
        TrendFragment fragment = new TrendFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_TYPE);
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

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);
        if (type == 0) {
            binding.tvTitle.setText("一期趋势");
        } else {
            binding.tvTitle.setText("二期趋势");
        }
    }
}
