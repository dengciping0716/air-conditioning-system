package com.dengciping.ydroid.airconditioningsystem;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.dengciping.ydroid.airconditioningsystem.data.netwark.HeaderRequestHandler;

import java.util.concurrent.TimeUnit;

import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.NetProvider;
import cn.droidlover.xdroidmvp.net.RequestHandler;
import cn.droidlover.xdroidmvp.net.XApi;
import io.reactivex.Observable;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/3 下午5:03
 */
public class App extends Application {
    private static Context context;

    public static boolean isExit = false;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        XApi.init(Config.BaseUrl);
        XApi.registerProvider(new NetProvider() {

            @Override
            public Interceptor[] configInterceptors() {
                return new Interceptor[0];
            }

            @Override
            public void configHttps(OkHttpClient.Builder builder) {

            }

            @Override
            public CookieJar configCookie() {
                return null;
            }

            @Override
            public RequestHandler configHandler() {
                return new HeaderRequestHandler();
            }

            @Override
            public long configConnectTimeoutMills() {
                return 30 * 1000;
            }

            @Override
            public long configReadTimeoutMills() {
                return 60 * 1000;
            }

            @Override
            public boolean configLogEnable() {
                return BuildConfig.DEBUG;
            }

            @Override
            public boolean handleError(NetError error) {
                return false;
            }
        });
    }

    public static Context getContext() {
        return context;
    }

    public static void exit(Activity activity) {
        isExit = true;
        activity.finish();
        Observable.timer(1, TimeUnit.SECONDS).subscribe(l -> {
            isExit = false;
            kill();
        });
    }

    /**
     * 杀死应用
     */
    public static void kill() {
        //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
