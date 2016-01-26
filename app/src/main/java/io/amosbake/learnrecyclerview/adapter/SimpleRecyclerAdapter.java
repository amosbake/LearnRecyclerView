package io.amosbake.learnrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.amosbake.learnrecyclerview.R;
import io.amosbake.learnrecyclerview.Utils;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-21
 * Time: 09:40
 */
public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.Holder> implements AdapterDataHelper<String> {
    private List<String> datas;
    private int layoutRes;
    private Context mContext;

    public SimpleRecyclerAdapter(RecyclerView rv) {
        datas = new ArrayList<>();
        layoutRes = R.layout.item_str;
        mContext = rv.getContext();
    }

    @Override
    public void addData(String data) {
        if (data != null) {
            datas.add(data);
            notifyItemInserted(datas.size() - 1);
        }
    }

    @Override
    public void setDatas(Collection<String> data) {
        if (!Utils.isCollectionNullOrEmpty(data)) {
            datas.clear();
            datas.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void addDatas(Collection<String> data) {
        if (!Utils.isCollectionNullOrEmpty(data)) {
            datas.addAll(data);
            notifyItemRangeInserted(datas.size() - data.size(), datas.size() - 1);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(mContext).inflate(layoutRes,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String data=datas.get(position);
        holder.mTextView.setText(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class Holder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public Holder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView;
        }
    }
}
