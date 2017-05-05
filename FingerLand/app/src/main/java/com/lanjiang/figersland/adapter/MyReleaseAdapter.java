package com.lanjiang.figersland.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 我的发布适配器
 * Created by Lin on 2016/12/29.
 */

public class MyReleaseAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> data;
    private String[] tabs;

    public MyReleaseAdapter(FragmentManager fm, List<Fragment> data, String[] tabs) {
        super(fm);
        this.data = data;
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return tabs.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position % tabs.length].toUpperCase();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
