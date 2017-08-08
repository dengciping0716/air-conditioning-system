package com.dengciping.ydroid.airconditioningsystem.ui;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.Bean.AirData;
import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.databinding.FragmentPreviewBinding;
import com.dengciping.ydroid.airconditioningsystem.databinding.ItemPreviewBinding;

import java.util.List;

import cn.droidlover.xdroidmvp.base.SimpleDataBindingRecAdapter;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;

/**
 * 一期、二期 主界面
 */
public class PreviewFragment extends XLazyFragment<FragmentPreviewBinding, PreviewPresent> {
    private static final String ARG_TYPE = "param1";
    private int type;
    private SimpleDataBindingRecAdapter adapter;

    /**
     * @return A new instance of fragment PreviewFragment.
     */
    public static PreviewFragment newInstance(int type) {
        PreviewFragment fragment = new PreviewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.type = getArguments().getInt(ARG_TYPE);
        }
    }

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();

        getP().loadData(this.type);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_preview;
    }

    @Override
    public PreviewPresent newP() {
        return new PreviewPresent();
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);

        if (type == 0) {
            binding.tvTitle.setText("一期预览界面");
        } else {
            binding.tvTitle.setText("二期预览界面");
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        layout.setStackFromEnd(true);
//        new LinearSnapHelper().attachToRecyclerView(binding.rvContent);

        binding.rvContent.setLayoutManager(gridLayoutManager);
        adapter = new SimpleDataBindingRecAdapter<AirData, ItemPreviewBinding>(getContext()) {
            @Override
            protected int getLayoutIdForPosition(int position) {
                return R.layout.item_preview;
            }

            @Override
            protected void onBind(ItemPreviewBinding binding, AirData data) {
                String title = getString(R.string.preview_kt_desc);
                binding.tvTitle.setText(String.format(title, data.name));
                String temp = getString(R.string.preview_temp_desc);
                binding.tvTemp.setText(String.format(temp, data.temp));
                String humidity = getString(R.string.preview_moisture_desc);
                binding.tvMoisture.setText(String.format(humidity, data.humidity) + "%");
            }
        };

        binding.rvContent.setAdapter(adapter);
        binding.rvContent.setHasFixedSize(true);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    public void setData(List<AirData> data) {
        adapter.addData(data);
    }
}
