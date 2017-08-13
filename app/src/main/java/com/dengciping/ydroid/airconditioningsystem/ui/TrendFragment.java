package com.dengciping.ydroid.airconditioningsystem.ui;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.data.bean.AirData;
import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.databinding.FragmentTrendBinding;

import java.util.List;

import cn.droidlover.xdroidmvp.base.databinding.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;

/**
 * 一期、二期 趋势界面
 */
public class TrendFragment extends XLazyFragment<FragmentTrendBinding, TrendPresent> {
    private static final String ARG_TYPE = "param1";
    private int type;
    private SimpleRecAdapter<AirData> adapter;


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
        getP().loadData(type);
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

        binding.rvContent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.rvContent.setHasFixedSize(true);
        binding.rvContent.setItemAnimator(new DefaultItemAnimator());

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.rvContent);

        adapter = new TrendAdapter(getActivity());
        binding.rvContent.setAdapter(adapter);

    }

    public void setData(List<AirData> airDatas) {
        adapter.clearData();
        adapter.addData(airDatas);
    }


}
