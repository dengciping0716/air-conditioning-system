package com.dengciping.ydroid.airconditioningsystem.ui;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.annotation.NonNull;

import com.dengciping.ydroid.airconditioningsystem.Bean.AirData;
import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.databinding.ItemTrendBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import cn.droidlover.xdroidmvp.base.databinding.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.Kits;

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

        mChart.setDrawGridBackground(false);
        // no description text
        mChart.getDescription().setEnabled(false);
        // enable touch gestures
        mChart.setTouchEnabled(false);

        // enable scaling and dragging
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        // add data
        setData(mChart, 15, 100);

//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

//        mChart.animateX(1000);
        //mChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
    }

    private void setData(LineChart mChart, int count, float range) {

        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float val = Kits.Random.getRandom(40, 60);
            values.add(new Entry(i, val));
        }

        ArrayList<Entry> values2 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float val = Kits.Random.getRandom(40, 100);
            values2.add(new Entry(i, val));
        }


        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            LineDataSet set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);

            LineDataSet set2 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2.setValues(values);

            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            LineDataSet set1 = getTempDataSet(mChart.getContext());
            set1.setValues(values);

            LineDataSet set2 = getDataSet(mChart.getContext());
            set2.setValues(values2);

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets
            dataSets.add(set2); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }
    }

    @NonNull
    private LineDataSet getTempDataSet(Context context) {
        LineDataSet set1;
        set1 = new LineDataSet(null, "温度");

        set1.setDrawIcons(false);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.GREEN);
        set1.setCircleColor(Color.GREEN);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(true);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);
        set1.setFormLineWidth(1f);
        set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set1.setFormSize(15.f);

        set1.setFillColor(Color.GREEN);
        return set1;
    }

    @NonNull
    private LineDataSet getDataSet(Context context) {
        LineDataSet set1;
        set1 = new LineDataSet(null, "湿度");

        set1.setDrawIcons(false);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.RED);
        set1.setCircleColor(Color.RED);
        set1.setFillColor(Color.RED);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(true);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);
        set1.setFormLineWidth(1f);
        set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        set1.setFormSize(15.f);


        return set1;
    }

}
