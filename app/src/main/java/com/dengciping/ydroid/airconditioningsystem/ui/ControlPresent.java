package com.dengciping.ydroid.airconditioningsystem.ui;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.annotation.NonNull;

import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import cn.droidlover.xdroidmvp.mvp.IPresent;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/6 下午7:26
 */
public class ControlPresent implements IPresent<ControlFragment> {
    private ControlFragment fragment;

    @Override
    public void attachV(ControlFragment view) {
        fragment = view;
    }

    @Override
    public void detachV() {

    }

    public void loadData() {

    }

    private void bindChart() {

    }


}
