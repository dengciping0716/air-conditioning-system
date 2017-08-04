package cn.droidlover.xdroidmvp.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import cn.droidlover.xdroidmvp.log.XLog;

/**
 * @author MaTianyu
 * @date 2015-04-19
 */
public class DisplayUtil {
    private static final String TAG = DisplayUtil.class.getSimpleName();

    /**
     * 获取 显示信息
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }

    /**
     * 打印 显示信息
     */
    public static DisplayMetrics printDisplayInfo(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        StringBuilder sb = new StringBuilder();
        sb.append("_______  显示信息:  ");
        sb.append("\ndensity         :").append(dm.density);
        sb.append("\ndensityDpi      :").append(dm.densityDpi);
        sb.append("\nheightPixels    :").append(dm.heightPixels);
        sb.append("\nwidthPixels     :").append(dm.widthPixels);
        sb.append("\nscaledDensity   :").append(dm.scaledDensity);
        sb.append("\nxdpi            :").append(dm.xdpi);
        sb.append("\nydpi            :").append(dm.ydpi);
        XLog.d(TAG, sb.toString());
        return dm;
    }
}
