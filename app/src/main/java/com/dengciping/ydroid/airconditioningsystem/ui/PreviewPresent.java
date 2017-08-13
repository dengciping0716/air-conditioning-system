package com.dengciping.ydroid.airconditioningsystem.ui;

import com.dengciping.ydroid.airconditioningsystem.data.bean.AirData;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.mvp.IPresent;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/6 下午7:42
 */
public class PreviewPresent implements IPresent<PreviewFragment> {

    private PreviewFragment previewFragment;

    @Override
    public void attachV(PreviewFragment view) {
        previewFragment = view;
    }

    @Override
    public void detachV() {
        previewFragment = null;
    }

    public void loadData(int type) {
        List<AirData> list = new ArrayList<>(4);
        if (type == 0) {
            list.add(new AirData("1", 11.5f, 20.6f));
            list.add(new AirData("4", 11.5f, 20.6f));
            list.add(new AirData("5", 11.5f, 20.6f));
            list.add(new AirData("6", 11.5f, 20.6f));
        } else {
            list.add(new AirData("9", 11.5f, 20.6f));
            list.add(new AirData("10", 11.5f, 20.6f));
            list.add(new AirData("11", 11.5f, 20.6f));
            list.add(new AirData("12", 11.5f, 20.6f));
            list.add(new AirData("13", 11.5f, 20.6f));
            list.add(new AirData("14", 11.5f, 20.6f));
            list.add(new AirData("15", 11.5f, 20.6f));
        }

        previewFragment.setData(list);
    }
}
