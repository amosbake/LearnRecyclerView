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
 * Created by amosb on 2016/1/23.
 */
public class StrRecycleAdapter extends RecyclerView.Adapter<StrRecycleAdapter.StrHolder> implements AdapterDataHelper<String> {
    private List<String> datas;
    private Context context;

    public StrRecycleAdapter(RecyclerView rv) {
        this.context = rv.getContext();
        datas = new ArrayList<>();
    }

    @Override
    public StrHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_str, parent, false);
        return new StrHolder(view);
    }

    @Override
    public void onBindViewHolder(StrHolder holder, int position) {
        holder.show.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
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
            notifyItemRangeInserted(datas.size() - data.size()-1, datas.size() - 1);
        }
    }

    static class StrHolder extends RecyclerView.ViewHolder {
        private TextView show;
        public StrHolder(View itemView) {
            super(itemView);
            show = (TextView) itemView;
        }
    }
}
