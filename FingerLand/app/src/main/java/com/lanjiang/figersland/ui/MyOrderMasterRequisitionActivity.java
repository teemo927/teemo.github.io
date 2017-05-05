package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lin on 2017/1/5.
 */

public class MyOrderMasterRequisitionActivity extends BaseToolbarActivity {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_appearance)
    TextView tvAppearance;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.tv_requisitioned_again)
    TextView tvRequisitionedAgain;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_requisitioned_time)
    TextView tvRequisitionedTime;
    @BindView(R.id.tv_requisitioned_start_time)
    TextView tvRequisitionedStartTime;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.tv_requisitioned_end_time)
    TextView tvRequisitionedEndTime;
    @BindView(R.id.rl_requisition_time)
    LinearLayout rlRequisitionTime;
    @BindView(R.id.tv_number_of_requisitioned)
    TextView tvNumberOfRequisitioned;
    @BindView(R.id.iv_star1)
    ImageView ivStar1;
    @BindView(R.id.iv_star2)
    ImageView ivStar2;
    @BindView(R.id.iv_star3)
    ImageView ivStar3;
    @BindView(R.id.iv_star4)
    ImageView ivStar4;
    @BindView(R.id.iv_star5)
    ImageView ivStar5;
    @BindView(R.id.evaluate)
    TextView evaluate;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_on_list_reason)
    TextView tvOnListReason;
    @BindView(R.id.tv_order_requirements)
    TextView tvOrderRequirements;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_other_agreed)
    TextView tvOtherAgreed;
    @BindView(R.id.tv_trance_title)
    TextView tvTranceTitle;
    @BindView(R.id.tv_money_reward)
    TextView tvMoneyReward;
    @BindView(R.id.tv_transaction_area)
    TextView tvTransactionArea;
    @BindView(R.id.tv_start_time2)
    TextView tvStartTime2;
    @BindView(R.id.tv_end_time2)
    TextView tvEndTime2;
    @BindView(R.id.tv_reasons_for_requisition)
    TextView tvReasonsForRequisition;
    @BindView(R.id.tv_start_time3)
    TextView tvStartTime3;
    @BindView(R.id.tv_end_time3)
    TextView tvEndTime3;
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
    @BindView(R.id.iv_status6)
    ImageView ivStatus6;
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
    @BindView(R.id.tv_status6)
    TextView tvStatus6;
    @BindView(R.id.ll_order_status)
    RelativeLayout llOrderStatus;
    @BindView(R.id.rl_order_status)
    RelativeLayout rlOrderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_master_requisition);
        super.unbinder = ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化方法
     */
    private void initView() {
        rlOrderStatus.setVisibility(View.VISIBLE);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.master_requisition_details));
    }

    @OnClick({R.id.iv_message, R.id.tv_requisitioned_again, R.id.iv_star1, R.id.iv_star2, R.id.iv_star3, R.id.iv_star4, R.id.iv_star5, R.id.iv_status1, R.id.iv_status2, R.id.iv_status3, R.id.iv_status4, R.id.iv_status5, R.id.iv_status6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_message:
                break;
            case R.id.tv_requisitioned_again:
                break;
            case R.id.iv_star5:
                changeStar(ivStar5, R.drawable.collection_select);
            case R.id.iv_star4:
                changeStar(ivStar4, R.drawable.collection_select);
            case R.id.iv_star3:
                changeStar(ivStar3, R.drawable.collection_select);
            case R.id.iv_star2:
                changeStar(ivStar2, R.drawable.collection_select);
            case R.id.iv_star1:
                changeStar(ivStar1, R.drawable.collection_select);
                break;
            case R.id.iv_status6:
                changeOrderState(tvStatus6, ivStatus6, R.drawable.dingdan_last_select);
            case R.id.iv_status5:
                changeOrderState(tvStatus5, ivStatus5, R.drawable.dingdan_select);
            case R.id.iv_status4:
                changeOrderState(tvStatus4, ivStatus4, R.drawable.dingdan_select);
            case R.id.iv_status3:
                changeOrderState(tvStatus3, ivStatus3, R.drawable.dingdan_select);
            case R.id.iv_status2:
                changeOrderState(tvStatus2, ivStatus2, R.drawable.dingdan_select);
            case R.id.iv_status1:
                changeOrderState(tvStatus1, ivStatus1, R.drawable.dingdan_select);
                break;
        }
    }

    /**
     * 改变订单状态
     *
     * @param tv
     * @param iv
     * @param resId
     */
    private void changeOrderState(TextView tv, ImageView iv, int resId) {
        tv.setTextColor(getResources().getColor(R.color.color_main));
        iv.setImageResource(resId);
    }

    /**
     * 改变选中之后的星星
     *
     * @param iv
     * @param resId
     */
    private void changeStar(ImageView iv, int resId) {
        iv.setImageResource(resId);
    }

}
