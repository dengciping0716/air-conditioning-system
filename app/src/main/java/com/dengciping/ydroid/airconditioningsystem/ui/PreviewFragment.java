package com.dengciping.ydroid.airconditioningsystem.ui;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.data.bean.AirData;
import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.databinding.FragmentPreviewBinding;

import java.util.List;

import cn.droidlover.xdroidmvp.base.databinding.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 一期、二期 主界面
 */
public class PreviewFragment extends XLazyFragment<FragmentPreviewBinding, PreviewPresent> {
    private static final String ARG_TYPE = "param1";
    private int type;
    private SimpleRecAdapter adapter;

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
    public PreviewPresent newP() {
        return new PreviewPresent();
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        binding.rvContent.setLayoutManager(gridLayoutManager);
        adapter = new SimpleRecAdapter<AirData>(getContext()) {
            @Override
            protected int getLayoutIdForPosition(int position) {
                return R.layout.item_preview;
            }
        };
        adapter.setOnClickPresenter((v, item) -> {
            Router.newIntent(getActivity()).to(ThirdActivity.class).launch();
        });

        binding.rvContent.setAdapter(adapter);
        binding.rvContent.setHasFixedSize(true);
        binding.rvContent.setItemAnimator(new DefaultItemAnimator());

        binding.rvContent.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = 10;
                outRect.top = 10;
            }
        });

        binding.rvContent.setItemViewCacheSize(4);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getP().loadData(this.type);
    }

    public void setData(List<AirData> data) {
        adapter.clearData();
        adapter.addData(data);
    }
}
