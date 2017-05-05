package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lin on 2017/1/4.
 */

public class MyOrderBaPingDetailActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_transaction_area)
    TextView tvTransactionArea;
    @BindView(R.id.tv_money_reward)
    TextView tvMoneyReward;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_transaction_promise)
    TextView tvTransactionPromise;
    @BindView(R.id.tv_default_compensation)
    TextView tvDefaultCompensation;
    @BindView(R.id.tv_shipping_address)
    TextView tvShippingAddress;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.iv_status1)
    ImageView ivStatus1;
    @BindView(R.id.iv_status2)
    ImageView ivStatus2;
    @BindView(R.id.iv_status3)
    ImageView ivStatus3;
    @BindView(R.id.iv_status4)
    ImageView ivStatus4;
    @BindView(R.id.iv_status5)
    ImageView ivStatus5;
    @BindView(R.id.tv_status1)
    TextView tvStatus1;
    @BindView(R.id.tv_status2)
    TextView tvStatus2;
    @BindView(R.id.tv_status3)
    TextView tvStatus3;
    @BindView(R.id.tv_status4)
    TextView tvStatus4;
    @BindView(R.id.tv_status5)
    TextView tvStatus5;
    @BindView(R.id.ll_order_status)
    RelativeLayout llOrderStatus;
    @BindView(R.id.rl_order_status)
    RelativeLayout rlOrderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_baping_detail);
        super.unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.baping_release_details));
    }

    @OnClick({R.id.iv_status1, R.id.iv_status2, R.id.iv_status3, R.id.iv_status4, R.id.iv_status5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                break;
            case R.id.iv_status5:
                changeOrderState(tvStatus5, ivStatus5, R.drawable.dingdan_last_select);
            case R.id.iv_status4:
                changeOrderState(tvStatus4, ivStatus4, R.drawable.dingdan_select);
            case R.id.iv_status3:
                changeOrderState(tvStatus3, ivStatus3, R.drawable.dingdan_select);
            case R.id.iv_status2:
                changeOrderState(tvStatus2, ivStatus2, R.drawable.dingdan_select);
            case R.id.iv_status1:
                changeOrderState(tvStatus1, ivStatus1, R.drawable.dingdan_select);
                break;
            default:
                break;
        }
    }

    /**
     * 改变订单状态
     *
     * @param iv
     */
    private void changeOrderState(TextView tv, ImageView iv, int resId) {
        tv.setTextColor(getResources().getColor(R.color.color_main));
        iv.setImageResource(resId);
    }
}

