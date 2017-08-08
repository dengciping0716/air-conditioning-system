package cn.droidlover.xdroidmvp.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * Created by wanglei on 2016/11/29.
 */

public abstract class SimpleDataBindingRecAdapter<T, B extends ViewDataBinding> extends RecyclerAdapter<T, SimpleViewHolder<T, B>> {

    public SimpleDataBindingRecAdapter(Context context) {
        super(context);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        B binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new SimpleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder<T, B> holder, int position) {
        onBind(holder.binding, getObjForPosition(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract int getLayoutIdForPosition(int position);

    protected T getObjForPosition(int position) {
        List<T> dataSource = getDataSource();
        if (dataSource.size() > position) {
            return dataSource.get(position);
        }
        return null;
    }

    protected abstract void onBind(B binding, T data);


}
