package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerityNextActivity extends BaseToolbarActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_produce_service)
    EditText etProduceService;
    @BindView(R.id.et_company_tel)
    EditText etCompanyTel;
    @BindView(R.id.et_company_address)
    EditText etCompanyAddress;
    @BindView(R.id.tv_business_licence)
    TextView tvBusinessLicence;
    @BindView(R.id.iv_business_licence)
    ImageView ivBusinessLicence;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_company_logo)
    TextView tvCompanyLogo;
    @BindView(R.id.iv_company_logo)
    ImageView ivCompanyLogo;
    @BindView(R.id.tv_calling_card)
    TextView tvCallingCard;
    @BindView(R.id.iv_calling_card)
    ImageView ivCallingCard;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.activity_verity_next)
    LinearLayout activityVerityNext;

    @OnClick({R.id.btn_submit})
    public void click(View v){
        switch (v.getId()){
            case R.id.btn_submit:
//                startActivity(new Intent(mContext,SettingActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verity_next);
        ButterKnife.bind(this);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.enterprise_certification);
    }
}
