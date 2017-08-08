package cn.droidlover.xdroidmvp.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * $desc$
 *
 * @author DengCiPing
 * @date 2017/8/7 下午9:36
 */
public class SimpleViewHolder<T, B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public final B binding;

    public SimpleViewHolder(B dataBinding) {
        super(dataBinding.getRoot());
        this.binding = dataBinding;
    }

    public void bind(T item) {
//        binding.setVariable(item , item);
//        binding.executePendingBindings();
    }

}
