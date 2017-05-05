package com.lanjiang.figersland.ui;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.lanjiang.figersland.BaseActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.fragment.SearchFragment;
import com.lanjiang.figersland.utils.SystemBarTintManager;

public class SearchActivity extends BaseActivity implements SearchFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用第三方解决，弊端颜色变暗（需同时在style中设置透明状态栏）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int color = R.color.colorPrimary;
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintResource(color);
            tintManager.setStatusBarTintEnabled(true);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_search, SearchFragment.newInstance("", "")).commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
