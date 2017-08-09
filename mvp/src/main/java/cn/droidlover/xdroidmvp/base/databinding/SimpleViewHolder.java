package cn.droidlover.xdroidmvp.base.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/7 下午9:36
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder {
    public ViewDataBinding dataBinding;

    public SimpleViewHolder(View view) {
        super(view);
        dataBinding = DataBindingUtil.bind(itemView);
    }

    public ViewDataBinding getBinding() {
        return dataBinding;
    }

}
