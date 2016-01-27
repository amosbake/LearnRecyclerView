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
public class SimpleRecyclerAdapter extends BaseRecyclerAdapter<String> implements AdapterDataHelper<String> {

    public SimpleRecyclerAdapter(RecyclerView rv) {
        super(rv,null,R.layout.item_str);
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
    protected void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
        holder.setText(R.id.show,item);
    }
}
