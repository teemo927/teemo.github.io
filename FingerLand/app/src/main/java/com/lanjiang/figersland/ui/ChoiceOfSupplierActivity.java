package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lin on 2016/12/14.
 */

public class ChoiceOfSupplierActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_invite_supplier)
    TextView tvInviteSupplier;
    @BindView(R.id.tv_invite_supplier_content)
    TextView tvInviteSupplierContent;
    @BindView(R.id.iv_invite_supplier)
    ImageView ivInviteSupplier;
    @BindView(R.id.rl_invite_supplier)
    RelativeLayout rlInviteSupplier;
    @BindView(R.id.tv_entrust_invite_supplier)
    TextView tvEntrustInviteSupplier;
    @BindView(R.id.tv_entrust_invite_supplier_content)
    TextView tvEntrustInviteSupplierContent;
    @BindView(R.id.iv_entrust_invite_supplier)
    ImageView ivEntrustInviteSupplier;
    @BindView(R.id.rl_entrust_invite_supplier)
    RelativeLayout rlEntrustInviteSupplier;
    @BindView(R.id.tv_bid_freely)
    TextView tvBidFreely;
    @BindView(R.id.tv_bid_freely_content)
    TextView tvBidFreelyContent;
    @BindView(R.id.iv_bid_freely)
    ImageView ivBidFreely;
    @BindView(R.id.rl_bid_freely)
    RelativeLayout rlBidFreely;
    @BindView(R.id.tv_description_content)
    TextView tvDescriptionContent;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;

    private int checked = 0;
    private static final int CHECKED_INVITE_SUPPLIERS = 0x001;
    private static final int CHECKED_ENTRUST_INVITE_SUPPLIERS = 0x002;
    private static final int CHECKED_BID_FREELY = 0x003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_of_supplier);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.project_type));
    }

    /**
     * 初始化
     */
    private void initView() {
        unbinder = ButterKnife.bind(this);
    }


    @OnClick({R.id.rl_invite_supplier, R.id.rl_entrust_invite_supplier, R.id.rl_bid_freely, R.id.btn_next_step})
    public void onClick(View view) {
        showDescription(view.getId());
        switch (view.getId()) {
            case R.id.btn_next_step:
                doNextStep();
                break;
            default:
                break;
        }
    }

    /**
     * 显示描述详情
     */
    private void showDescription(int id) {
        switch (id) {
            // 邀请供应商
            case R.id.rl_invite_supplier:
                rlEntrustInviteSupplier.setBackgroundColor(getResources().getColor(R.color.white));
                tvEntrustInviteSupplier.setTextColor(getResources().getColor(R.color.background_navigation));
                tvEntrustInviteSupplierContent.setTextColor(getResources().getColor(R.color.color_main));
                ivEntrustInviteSupplier.setImageDrawable(getResources().getDrawable(R.drawable.next_grey));

                rlBidFreely.setBackgroundColor(getResources().getColor(R.color.white));
                tvBidFreely.setTextColor(getResources().getColor(R.color.background_navigation));
                tvBidFreelyContent.setTextColor(getResources().getColor(R.color.color_main));
                ivBidFreely.setImageDrawable(getResources().getDrawable(R.drawable.next_grey));

                rlInviteSupplier.setBackgroundColor(getResources().getColor(R.color.group_select));
                tvInviteSupplier.setTextColor(getResources().getColor(R.color.white));
                tvInviteSupplierContent.setTextColor(getResources().getColor(R.color.white));
                ivInviteSupplier.setImageDrawable(getResources().getDrawable(R.drawable.next_white));

                tvDescriptionContent.setText(getResources().getText(R.string.description_invite_suppliers));
                checked = CHECKED_INVITE_SUPPLIERS;
                break;

            // 筛选供应商
            case R.id.rl_entrust_invite_supplier:
                rlInviteSupplier.setBackgroundColor(getResources().getColor(R.color.white));
                tvInviteSupplier.setTextColor(getResources().getColor(R.color.background_navigation));
                tvInviteSupplierContent.setTextColor(getResources().getColor(R.color.color_main));
                ivInviteSupplier.setImageDrawable(getResources().getDrawable(R.drawable.next_grey));

                rlBidFreely.setBackgroundColor(getResources().getColor(R.color.white));
                tvBidFreely.setTextColor(getResources().getColor(R.color.background_navigation));
                tvBidFreelyContent.setTextColor(getResources().getColor(R.color.color_main));
                ivBidFreely.setImageDrawable(getResources().getDrawable(R.drawable.next_grey));

                rlEntrustInviteSupplier.setBackgroundColor(getResources().getColor(R.color.group_select));
                tvEntrustInviteSupplier.setTextColor(getResources().getColor(R.color.white));
                tvEntrustInviteSupplierContent.setTextColor(getResources().getColor(R.color.white));
                ivEntrustInviteSupplier.setImageDrawable(getResources().getDrawable(R.drawable.next_white));

                tvDescriptionContent.setText(getResources().getText(R.string.description_entrust_suppliers));
                checked = CHECKED_ENTRUST_INVITE_SUPPLIERS;
                break;

            // 自由竞标
            case R.id.rl_bid_freely:
                rlInviteSupplier.setBackgroundColor(getResources().getColor(R.color.white));
                tvInviteSupplier.setTextColor(getResources().getColor(R.color.background_navigation));
                tvInviteSupplierContent.setTextColor(getResources().getColor(R.color.color_main));
                ivInviteSupplier.setImageDrawable(getResources().getDrawable(R.drawable.next_grey));

                rlEntrustInviteSupplier.setBackgroundColor(getResources().getColor(R.color.white));
                tvEntrustInviteSupplier.setTextColor(getResources().getColor(R.color.background_navigation));
                tvEntrustInviteSupplierContent.setTextColor(getResources().getColor(R.color.color_main));
                ivEntrustInviteSupplier.setImageDrawable(getResources().getDrawable(R.drawable.next_grey));

                rlBidFreely.setBackgroundColor(getResources().getColor(R.color.group_select));
                tvBidFreely.setTextColor(getResources().getColor(R.color.white));
                tvBidFreelyContent.setTextColor(getResources().getColor(R.color.white));
                ivBidFreely.setImageDrawable(getResources().getDrawable(R.drawable.next_white));

                tvDescriptionContent.setText(getResources().getText(R.string.description_bid_freely));
                checked = CHECKED_BID_FREELY;
                break;

        }
    }

    /**
     * 下一步
     */
    private void doNextStep() {
        if (checked == 0) {
            ToastUtils.show(mContext, "请先选择项目类型");
            return;
        }

        Intent intent = new Intent();
        switch (checked) {
            case CHECKED_INVITE_SUPPLIERS:
                intent.setClass(mContext, InviteSupplierActivity.class);
                startActivity(intent);
                break;
            case CHECKED_ENTRUST_INVITE_SUPPLIERS:
                intent.setClass(mContext, ScreeningSupplierActivity.class);
                startActivity(intent);
                break;
            case CHECKED_BID_FREELY:
                ToastUtils.show(mContext, "发布完成");
                break;
            default:
                break;
        }
    }

}
