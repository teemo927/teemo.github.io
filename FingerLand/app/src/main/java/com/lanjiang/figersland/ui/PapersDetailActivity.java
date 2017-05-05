package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 证件我来详情
 * Created by Lin on 2016/12/14.
 */

public class PapersDetailActivity extends BaseToolbarActivity {

    @BindView(R.id.iv_local)
    ImageView ivLocal;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.ll_land_use)
    LinearLayout llLandUse;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_details_content)
    LinearLayout llDetailsContent;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.llt_share)
    LinearLayout lltShare;
    @BindView(R.id.iv_talk)
    ImageView ivTalk;
    @BindView(R.id.tv_talk)
    TextView tvTalk;
    @BindView(R.id.llt_talk)
    LinearLayout lltTalk;
    @BindView(R.id.tv_transaction)
    TextView tvTransaction;
    @BindView(R.id.llt_bottom)
    LinearLayout llBottom;

    @OnClick({R.id.llt_share, R.id.llt_talk, R.id.tv_transaction})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.llt_share:
                share("",true);
                break;
            case R.id.llt_talk:
                talk();
                break;
            case R.id.tv_transaction:
                startActivity(new Intent(mContext,PaperRequisitionActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers_detail);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.detail));
        setRight(R.drawable.selector_search_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSearchAction();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 1.0f;
        this.getWindow().setAttributes(params);
    }
}
