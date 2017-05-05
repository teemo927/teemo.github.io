package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lin on 2017/1/4.
 */

public class MyOrderYipaiDetailActivity extends BaseToolbarActivity {
    @BindView(R.id.applied_for_content)
    TextView appliedForContent;
    @BindView(R.id.order_status2_content)
    TextView orderStatus2Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_yipai_detail);
        super.unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.yipai_details));
    }

}
