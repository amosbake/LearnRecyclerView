package io.amosbake.learnrecyclerview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.amosbake.learnrecyclerview.MainActivity;
import io.amosbake.learnrecyclerview.R;

/**
 * Author: yanhao(amosbake@gmail.com)
 * Date : 2016-01-21
 * Time: 10:09
 */
public class GuideFragment extends Fragment {
    private Button btnList;
    private Button btnRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_guide, container, false);
        btnList = (Button) view.findViewById(R.id.btn_list);
        btnRecycler = (Button) view.findViewById(R.id.btn_recycler);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeFrg(new ListviewFragment());
            }
        });
        btnRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeFrg(new RecylerViewFragment());
            }
        });
    }
}
