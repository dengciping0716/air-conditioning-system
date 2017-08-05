package com.dengciping.ydroid.airconditioningsystem.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.App;
import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.common.BaseActivity;
import com.dengciping.ydroid.airconditioningsystem.databinding.ActivitySecondBinding;

import java.util.concurrent.TimeUnit;

import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class SecondActivity extends BaseActivity {

    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);

        binding.tvUser.setText("普通权限");

        binding.tvTime.setText(Kits.Date.getHm(System.currentTimeMillis()));


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

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClickExit(View view) {
        App.exit(this);
    }

}
