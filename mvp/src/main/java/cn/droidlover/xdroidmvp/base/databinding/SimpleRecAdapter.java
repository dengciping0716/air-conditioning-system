package cn.droidlover.xdroidmvp.base.databinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import cn.droidlover.xdroidmvp.BR;
import cn.droidlover.xrecyclerview.RecyclerAdapter;

/**
 * Created by wanglei on 2016/11/29.
 */

public abstract class SimpleRecAdapter<T extends ItemModel> extends RecyclerAdapter<T, SimpleViewHolder> {

    protected OnClickPresenter<T> mOnClickPresenter;
    protected OnLongClickPresenter<T> mOnLongClickPresenter;

    public SimpleRecAdapter(Context context) {
        super(context);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType <= 0) return null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SimpleViewHolder(inflater.inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        T item = getObjForPosition(position);

        holder.dataBinding.setVariable(BR.viewModel, item);

        if (mOnClickPresenter != null) {
            holder.dataBinding.setVariable(BR.onClickPresenter, mOnClickPresenter);
        }
        if (mOnLongClickPresenter != null) {
            holder.dataBinding.setVariable(BR.onLongClickPresenter, mOnClickPresenter);
        }

        holder.dataBinding.executePendingBindings();
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

    public OnClickPresenter<? extends ItemModel> getOnClickPresenter() {
        return mOnClickPresenter;
    }

    public void setOnClickPresenter(OnClickPresenter<T> onClickPresenter) {
        mOnClickPresenter = onClickPresenter;
    }

    public OnLongClickPresenter<? extends ItemModel> getOnLongClickPresenter() {
        return mOnLongClickPresenter;
    }

    public void setOnLongClickPresenter(OnLongClickPresenter<T> onLongClickPresenter) {
        mOnLongClickPresenter = onLongClickPresenter;
    }

}
