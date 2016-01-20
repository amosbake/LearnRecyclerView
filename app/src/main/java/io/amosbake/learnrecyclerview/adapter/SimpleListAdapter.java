package io.amosbake.learnrecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.amosbake.learnrecyclerview.R;
import io.amosbake.learnrecyclerview.Utils;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-20
 * Time: 16:22
 */
public class SimpleListAdapter extends BaseAdapter implements AdapterDataHelper<String> {
    private List<String> datas;
    private LayoutInflater mInflater;

    public SimpleListAdapter(ListView listView) {
        mInflater = LayoutInflater.from(listView.getContext());
        datas = new ArrayList<>();
    }

    public void addData(String data) {
        if (data != null) {
            datas.add(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void setDatas(Collection<String> data) {
        if (!Utils.isCollectionNullOrEmpty(data)) {
            this.datas.clear();
            this.datas.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void addDatas(Collection<String> data) {
        if (!Utils.isCollectionNullOrEmpty(data)) {
            this.datas.addAll(data);
            notifyDataSetChanged();
        }
    }

    public List<String> getDatas() {
        return datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null==convertView){
            convertView=mInflater.inflate(R.layout.item_str,parent,false);
            holder=new ViewHolder();
            holder.mTextView= (TextView) convertView.findViewById(R.id.show);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(datas.get(position));
        return convertView;
    }

    static class ViewHolder{
        private TextView mTextView;
    }
}
