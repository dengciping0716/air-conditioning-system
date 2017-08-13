package com.dengciping.ydroid.airconditioningsystem.ui;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import com.dengciping.ydroid.airconditioningsystem.App;
import com.dengciping.ydroid.airconditioningsystem.data.bean.UserBean;
import com.dengciping.ydroid.airconditioningsystem.R;
import com.dengciping.ydroid.airconditioningsystem.common.BaseActivity;
import com.dengciping.ydroid.airconditioningsystem.data.netwark.UserRepository;
import com.dengciping.ydroid.airconditioningsystem.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

import cn.droidlover.xdroidmvp.event.BusProvider;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.IPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity<ActivityMainBinding, IPresent> {

    boolean isLogin = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);
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


        BusProvider.getBus().toFlowable(UserBean.class)
                .compose(this.bindToLifecycle())
                .subscribe(userBean -> {
                    onLogin();
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLogin();
    }

    private void checkLogin() {
        String name = UserRepository.getUserName();
        String token = UserRepository.getToken();
        String refreshToken = UserRepository.getRefreshToken();

        if (TextUtils.isEmpty(token)) {
            isLogin = false;
            binding.tvUser.setText("未登录");
            if (!TextUtils.isEmpty(refreshToken)) {
                refreshToken(refreshToken);
            }
        } else {
            isLogin = true;
            name = TextUtils.isEmpty(name) ? "未登录" : name;
            binding.tvUser.setText(name);
        }
    }

    public void refreshToken(String token) {
        new UserRepository()
                .refreshToken(token)
                .retry(3)
                .compose(this.bindToLifecycle())
                .subscribe(new ApiSubscriber<UserBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        Snackbar.make(binding.btnEnter, error.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        onLogin();
                    }
                });
    }

    private void onLogin() {
        isLogin = true;

        String name = UserRepository.getUserName();
        name = TextUtils.isEmpty(name) ? "未登录" : name;

        binding.tvUser.setText(name);
        Snackbar.make(binding.btnEnter, "欢迎进入系统~~~！", Snackbar.LENGTH_SHORT).show();
    }

    public void onClickExit(View view) {
        App.exit(this);
    }

    public void onClickEnter(View view) {
        if (isLogin) {
            Router.newIntent(this).to(SecondActivity.class).launch();
        } else {
            LoginFragment.newInstance().show(getSupportFragmentManager(), "login");
        }
    }

    public void onClickLogin(View view) {
        LoginFragment.newInstance().show(getSupportFragmentManager(), "login");
    }


}
