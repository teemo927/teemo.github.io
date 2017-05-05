package com.lanjiang.figersland.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lanjiang.figersland.adapter.BannerAdapter;
import com.lanjiang.figersland.adapter.BannerLoopAdapter;

import butterknife.Unbinder;

/**
 * Created by Asus on 2016/12/8.
 */

public class BFragment extends Fragment {

    protected String TAG = this.getClass().getSimpleName();

    protected Context mContext;
    protected Activity mActivity;
    protected LayoutInflater mInflater;
    protected Unbinder unbinder;
    protected BannerLoopAdapter mBannerAdapter;//轮播

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mActivity = getActivity();
        mInflater = LayoutInflater.from(mContext);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    protected <T extends View> T findView(Activity context, int id) {

        return (T) context.findViewById(id);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBannerAdapter != null) {
            mBannerAdapter.stop();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBannerAdapter != null) {
            mBannerAdapter.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mBannerAdapter != null) {
            mBannerAdapter.stop();
            mBannerAdapter = null;
        }
    }

}
