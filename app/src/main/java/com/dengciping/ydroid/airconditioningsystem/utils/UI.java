package com.dengciping.ydroid.airconditioningsystem.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;

import cn.droidlover.xdroidmvp.kit.Kits;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/2 下午2:45
 */
public class UI {
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void hideSystemUI(Activity activity) {
        if (!Kits.Package.isSdkVerson(activity, Build.VERSION_CODES.HONEYCOMB)) return;

        View decorView = activity.getWindow().getDecorView();

        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
