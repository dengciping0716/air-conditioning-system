package com.dengciping.ydroid.airconditioningsystem.ui;

import android.support.v4.app.Fragment;
import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.App;
import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.common.BaseActivity;
import com.dengciping.ydroid.airconditioningsystem.databinding.ActivitySecondBinding;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import cn.droidlover.xdroidmvp.base.XFragmentAdapter;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.IPresent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SecondActivity extends BaseActivity<ActivitySecondBinding, IPresent> {
    private final Fragment[] mTabContents = {
            PreviewFragment.newInstance(0),
            PreviewFragment.newInstance(1),
            TrendFragment.newInstance(0),
            TrendFragment.newInstance(1),
    };

    private final String[] titles = {
            "一期预览界面",
            "二期预览界面",
            "一期趋势",
            "二期趋势",
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);

        binding.tvUser.setText("普通权限");

        binding.tvTime.setText(Kits.Date.getHm(System.currentTimeMillis()));

        binding.vpContent.setAdapter(new XFragmentAdapter(getSupportFragmentManager(), Arrays.asList(mTabContents), titles));
        binding.vpContent.setOffscreenPageLimit(3);

        binding.tabLayout.setupWithViewPager(binding.vpContent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Observable.timer(10, TimeUnit.SECONDS)
                .repeat()
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    binding.tvTime.setText(Kits.Date.getHm(System.currentTimeMillis()));
                });

    }

    public void onClickExit(View view) {
        App.exit(this);
    }

}
