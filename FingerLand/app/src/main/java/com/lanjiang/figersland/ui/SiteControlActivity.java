package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 现场管控页面
 */
public class SiteControlActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_control);
        ButterKnife.bind(this);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.site_control);
        setRightMenu();
    }

    protected void initView() {
        tvContent.setText("待开发");
    }
}
