package com.lanjiang.figersland.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.DialogUtils;
import com.lanjiang.figersland.utils.TimeUtils;
import com.lanjiang.figersland.utils.ToastUtils;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMasterActivity extends BaseToolbarActivity {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_reason)
    EditText etReason;
    @BindView(R.id.et_request)
    EditText etRequest;
    @BindView(R.id.cb_pay)
    CheckBox cbPay;
    @BindView(R.id.btn_start_time)
    TextView btnStartTime;
    @BindView(R.id.btn_end_time)
    TextView btnEndTime;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_rules)
    TextView tvRules;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @OnClick({R.id.btn_start_time, R.id.btn_end_time, R.id.btn_submit, R.id.rlt_pay, R.id.rlt_agree, R.id.tv_rules})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_start_time:
                selectDate(btnStartTime);
                break;
            case R.id.btn_end_time:
                selectDate(btnEndTime);
                break;
            case R.id.btn_submit:
                break;
            case R.id.rlt_pay:
                cbPay.setChecked(!cbPay.isChecked());
                break;
            case R.id.rlt_agree:
                cbAgree.setChecked(!cbAgree.isChecked());
                break;
            case R.id.tv_rules:
                ToastUtils.show(mContext, "查看上榜规则");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gaoren);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.add_gaoren);
    }

    private void initView() {
        btnStartTime.setText(TimeUtils.getCurrentDate());
        btnEndTime.setText(TimeUtils.getCurrentDate());
        setBetweenDays();
    }

    private void selectDate(final TextView tv) {

        DialogUtils.selectDate(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tv.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                setBetweenDays();
            }
        });
    }

    private void setBetweenDays() {
        try {
            int days = TimeUtils.daysBetween(btnStartTime.getText().toString(), btnEndTime.getText().toString());
            tvDuration.setText(String.valueOf(days));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
