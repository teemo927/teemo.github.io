package com.lanjiang.figersland.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.DialogUtils;
import com.lanjiang.figersland.utils.PopUtils;
import com.lanjiang.figersland.utils.TimeUtils;
import com.lanjiang.figersland.utils.ToastUtils;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPaperActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.llt_area)
    LinearLayout lltArea;
    @BindView(R.id.et_reward)
    EditText etReward;
    @BindView(R.id.btn_start_time)
    TextView btnStartTime;
    @BindView(R.id.btn_end_time)
    TextView btnEndTime;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.et_reason)
    EditText etReason;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_rules)
    TextView tvRules;
    @BindView(R.id.rlt_agree)
    RelativeLayout rltAgree;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.activity_add_paper)
    LinearLayout activityAddPaper;
    @BindView(R.id.tv)
    TextView tv;



    @OnClick({R.id.llt_area,R.id.btn_start_time, R.id.btn_end_time, R.id.btn_submit, R.id.rlt_agree, R.id.tv_rules})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.llt_area:
                showCityWindow();
                break;
            case R.id.btn_start_time:
                selectDate(btnStartTime);
                break;
            case R.id.btn_end_time:
                selectDate(btnEndTime);
                break;
            case R.id.btn_submit:
                ToastUtils.show(mContext, "提交");
                break;
            case R.id.rlt_agree:
                cbAgree.setChecked(!cbAgree.isChecked());
                break;
            case R.id.tv_rules:
                ToastUtils.show(mContext, "查看" + getString(R.string.on_list_rules));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paper);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.bid);
    }

    private void initView() {
//        tvRules.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
//        tv.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
//        tv.setTextColor(Color.YELLOW);
//        getResources().getColor(R.color.yellow);
    }

    private void showCityWindow() {
        PopUtils.showCityWindow(mInflater, mContext, new PopUtils.PopClickListener() {
            @Override
            public void click(String city) {
                tvCity.setText(city);
            }
        });
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
