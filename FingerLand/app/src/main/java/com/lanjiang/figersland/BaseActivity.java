package com.lanjiang.figersland;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.lanjiang.figersland.utils.ScreenUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Asus on 2016/12/6.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mInflater = LayoutInflater.from(mContext);

        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);

        ScreenUtils.setTransparentStatusBar(this);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
