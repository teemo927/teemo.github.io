package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 报名详情页
 */
public class SignUpDetailActivity extends BaseToolbarActivity {

    @BindView(R.id.et_company_name)
    EditText etCompanyName;
    @BindView(R.id.et_service_zone)
    EditText etServiceZone;
    @BindView(R.id.et_register_money)
    EditText etRegisterMoney;
    @BindView(R.id.et_contact_information)
    EditText etContactInformation;
    @BindView(R.id.et_contact_addr)
    EditText etContactAddr;
    @BindView(R.id.et_main_business)
    EditText etMainBusiness;
    @BindView(R.id.tv_doc_name)
    TextView tvDocName;
    @BindView(R.id.llt_file_upload)
    LinearLayout lltFileUpload;

    @OnClick({R.id.llt_file_upload, R.id.btn_submit})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.llt_file_upload:
                ToastUtils.show(mContext, "上传文件");
                tvDocName.setText("测试文件.doc");
                break;
            case R.id.btn_submit:
                ToastUtils.show(mContext, "提交");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_details);
        ButterKnife.bind(this);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.sign_up_details);
    }
}
