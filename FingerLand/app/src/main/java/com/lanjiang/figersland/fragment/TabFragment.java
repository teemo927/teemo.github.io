package com.lanjiang.figersland.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanjiang.figersland.R;

/**
 * Created by Lin on 2017/1/12.
 */

public class TabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab, null);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        Bundle args = getArguments();
        String title = args.getString("args");
        textView.setText(title);
        return view;
    }
}