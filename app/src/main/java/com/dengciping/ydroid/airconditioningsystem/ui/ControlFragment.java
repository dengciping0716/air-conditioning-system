package com.dengciping.ydroid.airconditioningsystem.ui;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.common.BaseFragment;
import com.dengciping.ydroid.airconditioningsystem.databinding.FragmentControlBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ControlFragment extends BaseFragment<FragmentControlBinding, ControlPresent> {
    private static final String ARG_ID = "ID";

    private int mID;

    public static ControlFragment newInstance(int param1) {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mID = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getP().loadData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_control;
    }

    @Override
    public ControlPresent newP() {
        return new ControlPresent();
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);
    }


    public void loadChart() {

    }
}
