package io.amosbake.learnrecyclerview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.amosbake.learnrecyclerview.R;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-20
 * Time: 17:44
 */
public class RecylerViewFragment extends Fragment {
    private RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        rv = (RecyclerView) view;
        return rv;
    }
}
