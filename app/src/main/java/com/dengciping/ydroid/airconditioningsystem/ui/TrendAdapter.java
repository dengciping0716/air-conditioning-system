package com.dengciping.ydroid.airconditioningsystem.ui;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.dengciping.ydroid.airconditioningsystem.data.bean.AirData;
import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.databinding.ItemTrendBinding;
import com.github.mikephil.charting.charts.LineChart;

import cn.droidlover.xdroidmvp.base.databinding.SimpleRecAdapter;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/10 下午7:13
 */
public class TrendAdapter extends SimpleRecAdapter<AirData> {

    public TrendAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.item_trend;
    }

    @Override
    public void onBind(ViewDataBinding binding, AirData data) {
        ItemTrendBinding mBinding = (ItemTrendBinding) binding;
        LineChart mChart = mBinding.chart;

    }

}
