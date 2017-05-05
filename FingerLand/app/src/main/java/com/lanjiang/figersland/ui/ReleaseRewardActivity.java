package com.lanjiang.figersland.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.DialogUtils;
import com.lanjiang.figersland.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lin on 2016/12/13.
 */

public class ReleaseRewardActivity extends BaseToolbarActivity {

    @BindView(R.id.et_recruiting_range)
    EditText etRecruitingRange;
    @BindView(R.id.tv_recruiting_company)
    TextView tvRecruitingCompany;
    @BindView(R.id.tv_contact_way)
    TextView tvContactWay;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.tv_contact_address)
    TextView tvContactAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.tv_start_end_time)
    TextView tvStartEndTime;
    @BindView(R.id.btn_start_time)
    Button btnStartTime;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.btn_end_time)
    Button btnEndTime;
    @BindView(R.id.et_registered_capital)
    EditText etRegisteredCapital;
    @BindView(R.id.iv_folder)
    ImageView ivFolder;
    @BindView(R.id.tv_file_name)
    TextView tvFileName;
    @BindView(R.id.tv_folder)
    TextView tvFolder;
    @BindView(R.id.et_necessary_conditions)
    EditText etNecessaryConditions;
    @BindView(R.id.tv_rewards)
    TextView tvRewards;
    @BindView(R.id.tv_currency)
    TextView tvCurrency;
    @BindView(R.id.rl_rewards)
    RelativeLayout rlRewards;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;

    private Unbinder unbinder;
    private String titleName;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fromWhere();
        setContentView(R.layout.activity_release_reward);
        unbinder = ButterKnife.bind(this);

        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        // 如果是从招标界面进来，隐藏悬赏金额一整行
        if (getString(R.string.write_tender_info).equals(titleName)) {
            rlRewards.setVisibility(View.GONE);
        }
        etRegisteredCapital.setSelection(7);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(titleName);
    }

    @OnClick({R.id.iv_folder, R.id.btn_next_step, R.id.btn_start_time, R.id.btn_end_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_time:
                selectDate(btnStartTime);
                break;
            case R.id.btn_end_time:
                selectDate(btnEndTime);
                break;
            case R.id.iv_folder:
                ToastUtils.show(this, "上传文件");
                break;
            case R.id.btn_next_step:
                startActivity(intent);
                break;
        }
    }

    /**
     * 通过传值判断从哪个页面过来
     */
    private void fromWhere() {
        String type = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(type)) {
            return;
        }
        if ("tender".equals(type)) {
            titleName = getString(R.string.write_tender_info);
            intent.setClass(mContext, ChoiceOfSupplierActivity.class);
        } else {
            titleName = getString(R.string.write_reward_info);
            intent.setClass(mContext, ReleaseRewardPayActivity.class);
            intent.putExtra("type", "rewards");
        }
    }

    /**
     * 选择日期
     *
     * @param tv
     */
    private void selectDate(final TextView tv) {
        DialogUtils.selectDate(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tv.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        });
    }


    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

}
