package io.amosbake.learnrecyclerview.fragment;

import android.graphics.Color;
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
import io.amosbake.learnrecyclerview.decoration.GridSpaceItemDecoration;
import io.amosbake.learnrecyclerview.R;
import io.amosbake.learnrecyclerview.decoration.DividerDecoration;
import io.amosbake.learnrecyclerview.adapter.SimpleRecyclerAdapter;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-20
 * Time: 17:44
 */

public class RecylerViewFragment extends Fragment implements Handler.Callback {
    private static final String TAG = "RecylerViewFragment";
    private static final int MSG_ADD = 0x21;
    private List<String> tempDatas;
    private boolean isLoading;
    private RecyclerView rv;
    private SimpleRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private RecyclerView.ItemDecoration mDecoration;
    private Handler mHandler;
    private int pageIndex;

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
        fetchDatas(pageIndex);
    }

    private void initComponents() {
        tempDatas = new ArrayList<>();
        isLoading = false;
        mManager = new LinearLayoutManager(rv.getContext());
        rv.setLayoutManager(mManager);
        mAdapter = new SimpleRecyclerAdapter(rv);
        rv.setAdapter(mAdapter);
        mDecoration = new GridSpaceItemDecoration(1, getResources().getDimensionPixelSize(R.dimen.simple_offset_outer_horizontal),
                getResources().getDimensionPixelSize(R.dimen.simple_offset_outer_vertical),
                getResources().getDimensionPixelSize(R.dimen.simple_offset_inner_horizontal),
                0);
        DividerDecoration dividerDecoration = new DividerDecoration.Builder(getContext()).setColor(Color.GREEN)
                .setHeight(R.dimen.simple_offset_inner_vertical)
                .setLeftPadding(10.0f)
                .setRightPadding(10.0f).build();
        rv.addItemDecoration(dividerDecoration);
        rv.addItemDecoration(mDecoration);
        mHandler = new Handler(this);

    }

    private void fetchDatas(final int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                isLoading = true;
                tempDatas.clear();
                tempDatas.addAll(DataManager.generatrStrDatas("DB", page));
                mHandler.sendEmptyMessageDelayed(MSG_ADD, 1000);
            }
        }).start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_ADD: {
                mAdapter.addDatas(tempDatas);
                isLoading = false;
                break;
            }
            default:
        }
        return true;
    }
}
