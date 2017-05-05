package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MasterDetailActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.llt_share)
    LinearLayout lltShare;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.llt_call)
    LinearLayout lltCall;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.llt_bottom)
    LinearLayout lltBottom;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_use_count)
    TextView tvUseCount;
    @BindView(R.id.tv_see_record)
    TextView tvSeeRecord;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.tv_contact_information)
    TextView tvContactInformation;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;

    @OnClick({R.id.tv_see_record, R.id.llt_share, R.id.llt_call, R.id.tv_sign_up})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_see_record:
                startActivity(new Intent(mContext, RecordMasterActivity.class));
                break;
            case R.id.llt_share:
                share(null, false);
                break;
            case R.id.llt_call:
                talk();
                break;
            case R.id.tv_sign_up:
                handle();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaoren_detail);
        ButterKnife.bind(this);

        tvEndTime.setText(getString(R.string.project_time,"2017-01-17"));
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.gaoren_detail);
    }

    private void handle() {
        startActivity(new Intent(mContext, MasterRequisitionActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 1.0f;
        this.getWindow().setAttributes(params);
    }
}
