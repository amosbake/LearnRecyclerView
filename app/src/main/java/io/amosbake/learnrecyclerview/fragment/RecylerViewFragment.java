package io.amosbake.learnrecyclerview.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.amosbake.learnrecyclerview.DataManager;
import io.amosbake.learnrecyclerview.R;
import io.amosbake.learnrecyclerview.adapter.StrRecycleAdapter;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-20
 * Time: 17:44
 */
public class RecylerViewFragment extends Fragment implements Handler.Callback{
    private RecyclerView rv;
    private static final int MSG_ADD=0x11;
    private Handler mHandler;
    private StrRecycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> tempDatas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        rv = (RecyclerView) view;
        return rv;
    }

    @Override
    public void onStart() {
        super.onStart();
        initComponents();
        initDatas();
    }

    private void initDatas() {
        tempDatas=new ArrayList<>();
        fetchDatas(0);
    }

    private void initComponents() {
        mHandler=new Handler(this);
        mAdapter=new StrRecycleAdapter(rv);
        mLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(mAdapter);
    }

    private void fetchDatas(final int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                tempDatas.clear();
                tempDatas.addAll(DataManager.generatrStrDatas("DB",page));
                mHandler.sendEmptyMessageDelayed(MSG_ADD,1000);
//                mHandler.obtainMessage(MSG_ADD).sendToTarget();
            }
        }).start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case MSG_ADD:{
                mAdapter.addDatas(tempDatas);
                break;
            }
            default:
        }
        return true;
    }
}
