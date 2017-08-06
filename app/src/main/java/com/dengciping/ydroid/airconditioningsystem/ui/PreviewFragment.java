package com.dengciping.ydroid.airconditioningsystem.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.common.BaseFragment;
import com.dengciping.ydroid.airconditioningsystem.databinding.FragmentPreviewBinding;

import cn.droidlover.xdroidmvp.mvp.IPresent;

/**
 * 一期、二期 主界面
 */
public class PreviewFragment extends BaseFragment<FragmentPreviewBinding, IPresent> {
    private static final String ARG_TYPE = "param1";
    private int type;

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
    public int getLayoutId() {
        return R.layout.fragment_preview;
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);

        if (type == 0) {
            binding.tvTitle.setText("一期预览界面");
        } else {
            binding.tvTitle.setText("二期预览界面");
        }

        LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvContent.setLayoutManager(layout);
    }
}
