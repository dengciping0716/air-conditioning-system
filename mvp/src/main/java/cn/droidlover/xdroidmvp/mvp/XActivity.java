package cn.droidlover.xdroidmvp.mvp;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import cn.droidlover.xdroidmvp.XDroidConf;
import cn.droidlover.xdroidmvp.event.BusProvider;

/**
 * Created by wanglei on 2016/12/29.
 */

public abstract class XActivity<B extends ViewDataBinding, P extends IPresent> extends RxAppCompatActivity implements IView<P> {

    private VDelegate vDelegate;
    private P p;
    protected Activity context;

    private RxPermissions rxPermissions;

    protected B binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        if (getLayoutId() > 0) {
            bindUI(null);
            bindEvent();
        }

        initData(savedInstanceState);

    }

    @Override
    public void bindUI(View rootView) {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusProvider.getBus().register(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        getvDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            BusProvider.getBus().unregister(this);
        }
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(XDroidConf.DEV);
        return rxPermissions;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public void bindEvent() {

    }

    ///////////////////////////////////////////////////////////////////////////
    // Fragment 操作
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @param fragment Fragment
     */
    public void addFragment(Fragment fragment, @IdRes int res) {
        try {
            String clazz = fragment.getClass().getSimpleName();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(res, fragment, clazz);
            transaction.addToBackStack(clazz);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fragment替换
     *
     * @param fragment 需要被替换的Fragment
     */
    public void replace(Fragment fragment, @IdRes int res) {
        replace(fragment, res, false);
    }

    /**
     * Fragment替换
     *
     * @param fragment 需要被替换的Fragment
     * @param needBack 是否需要被保存到回退
     */
    public void replace(Fragment fragment, @IdRes int res, boolean needBack) {
        try {
            String clazz = fragment.getClass().getSimpleName();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(res, fragment, clazz);
            if (needBack)
                transaction.addToBackStack(clazz);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回退到指定fragment
     *
     * @param name Fragment name
     */
    public boolean popToFragment(String name) {
        FragmentManager mgr = getSupportFragmentManager();
        if (mgr == null) {
            Log.d("popToFragment", "getSupportFragmentManager() returned null?!");
        }

        try {
            Fragment frag = mgr.findFragmentByTag(name);
            Log.d("popToFragment", String.valueOf(frag));
            if (frag != null && frag != getCurrentFragment()) {
                mgr.popBackStack(name, 0);
                return true;
            }
        } catch (Exception var4) {
            Log.e("popToFragment", var4.getMessage());
        }

        return false;
    }

    /**
     * @return 当前Fragment的名字
     */
    public String getCurrentFragmentName() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int count = fragmentManager.getBackStackEntryCount();
            if (count > 0) {
                --count;
            }
            if (count < 0) {
                return "";
            }
            return fragmentManager.getBackStackEntryAt(count).getName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * @return 当前Fragment
     */
    public Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.findFragmentByTag(getCurrentFragmentName());
    }

    /**
     * 重新加载当前Fragment
     */
    public void refreshCurrentFragment() {
        try {
            Fragment fragment = getCurrentFragment();
            if (fragment == null) return;

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
            fragTransaction.detach(fragment);
            fragTransaction.attach(fragment);
            fragTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回退到上一个Fragment
     */
    public void toBackFragment() {
        try {
            FragmentManager manager = getSupportFragmentManager();
            manager.popBackStack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
