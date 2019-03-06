package com.limh.baselibs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author limh
 * @function 通用RecyclerView适配器
 * @date 2019/3/6 10:28
 */
public abstract class CommRvAdapter<T> extends RecyclerView.Adapter<ViewRvHolder> {
    protected Context mContext;
    private int mLayoutId;
    private List<T> mDatas;

    protected CommRvAdapter(Context mContext, int mLayoutId, List<T> mDatas) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;
    }

    @Override
    public ViewRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewRvHolder.get(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(ViewRvHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(ViewRvHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
