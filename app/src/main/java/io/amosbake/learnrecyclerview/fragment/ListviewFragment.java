package io.amosbake.learnrecyclerview.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.amosbake.learnrecyclerview.DataManager;
import io.amosbake.learnrecyclerview.R;
import io.amosbake.learnrecyclerview.adapter.StrListAdapter;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-20
 * Time: 16:19
 */
public class ListviewFragment extends Fragment implements Handler.Callback {
    private static final String TAG = "ListviewFragment";
    private static final int MSG_ADD=0x11;
    private ListView lv;
    private Handler mHandler;
    private StrListAdapter mAdapter;
    private List<String> tempDatas=new ArrayList<>();
    private int pageIndex=0;
    private boolean isLoading=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_listview, container, false);
        lv = (ListView) view.findViewById(R.id.lv);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initComponents();
        fetchDatas(pageIndex);
    }

    private void fetchDatas(final int page) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isLoading=true;
                    tempDatas.clear();
                    tempDatas.addAll(DataManager.generatrStrDatas("DB",page));
                    mHandler.sendEmptyMessageDelayed(MSG_ADD,1000);
//                mHandler.obtainMessage(MSG_ADD).sendToTarget();
                }
            }).start();
    }

    private void initComponents() {
        mHandler = new Handler(this);
        mAdapter = new StrListAdapter(lv);
        lv.setAdapter(mAdapter);
//        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.view_header, lv, false);
        final View footView = LayoutInflater.from(getActivity()).inflate(R.layout.view_footer, lv, false);
        lv.addFooterView(footView);
//        lv.addHeaderView(headView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int headerViewsCount = lv.getHeaderViewsCount();
                String item = (String) mAdapter.getItem(position-headerViewsCount);
                Log.i(TAG, "onItemClick: "+item);
            }
        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
                        Log.i(TAG, "onScroll: scrollToEnd");
                        if (!isLoading){
                            pageIndex++;
                            fetchDatas(pageIndex);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case MSG_ADD:{
                mAdapter.addDatas(tempDatas);
                isLoading=false;
                break;
            }
            default:
        }
        return true;
    }
}
