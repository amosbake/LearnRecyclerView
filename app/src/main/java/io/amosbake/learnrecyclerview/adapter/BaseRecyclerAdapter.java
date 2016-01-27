package io.amosbake.learnrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * RecyclerAdapter 的基类, 继承需要实现其绑定数据的方法
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2015-10-12
 * Time: 15:31
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {
    private final int itemLayoutId;
    private boolean isScrolling;
    protected final Context context;
    protected List<T> datas;


    public BaseRecyclerAdapter(RecyclerView v, Collection<T> datas, int itemLayoutId) {
        if (datas == null) {
            this.datas = new ArrayList<>();
        } else if (datas instanceof List) {
            this.datas = (List<T>) datas;
        } else {
            this.datas = new ArrayList<>(datas);
        }
        this.itemLayoutId = itemLayoutId;
        this.context = v.getContext();
        v.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isScrolling = !(newState == RecyclerView.SCROLL_STATE_IDLE);
            }
        });
    }

    public Collection<T> getDatas() {
        return datas;
    }

    /**
     * RecyclerView 适配器数据填充方法
     *
     * @param holder      viewholder
     * @param item        javabean
     * @param position    position
     * @param isScrolling 是否正在滚动
     */
    protected abstract void convert(RecyclerHolder holder, T item, int position, boolean isScrolling);

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(context).inflate(itemLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        convert(holder, datas.get(position), position, isScrolling);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public BaseRecyclerAdapter<T> refresh(Collection<T> datas) {
        if (datas == null) {
            this.datas = new ArrayList<>();
        } else if (datas instanceof List) {
            this.datas = (List<T>) datas;
        } else {
            this.datas = new ArrayList<>(datas);
        }
        return this;
    }


}
