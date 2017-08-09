package cn.droidlover.xdroidmvp.base.databinding;

import android.view.View;

import java.io.Serializable;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/9 下午9:33
 */
public interface OnClickPresenter<T extends ItemModel> extends Serializable {
    void onClick(View view, T t);
}
