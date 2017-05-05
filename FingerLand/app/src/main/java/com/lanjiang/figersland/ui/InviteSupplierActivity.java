package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lin on 2016/12/16.
 */

public class InviteSupplierActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_charge_standard)
    TextView tvChargeStandard;
    @BindView(R.id.rl_optional_supplier)
    RelativeLayout rlOptionalSupplier;
    @BindView(R.id.iv_check_supplier1)
    ImageView ivCheckSupplier1;
    @BindView(R.id.iv_supplier_logo)
    ImageView ivSupplierLogo;
    @BindView(R.id.rl_supplier1)
    RelativeLayout rlSupplier1;
    @BindView(R.id.iv_check_supplier2)
    ImageView ivCheckSupplier2;
    @BindView(R.id.iv_supplier_logo2)
    ImageView ivSupplierLogo2;
    @BindView(R.id.rl_supplier2)
    RelativeLayout rlSupplier2;
    @BindView(R.id.iv_check_supplier3)
    ImageView ivCheckSupplier3;
    @BindView(R.id.iv_supplier_logo3)
    ImageView ivSupplierLogo3;
    @BindView(R.id.rl_supplier3)
    RelativeLayout rlSupplier3;
    @BindView(R.id.iv_check_supplier4)
    ImageView ivCheckSupplier4;
    @BindView(R.id.iv_supplier_logo4)
    ImageView ivSupplierLogo4;
    @BindView(R.id.rl_supplier4)
    RelativeLayout rlSupplier4;
    @BindView(R.id.iv_select_all)
    ImageView ivSelectAll;
    @BindView(R.id.tv_select_all)
    TextView tvSelectAll;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_currency_China)
    TextView tvCurrencyChina;
    @BindView(R.id.immediate_payment)
    TextView immediatePayment;

    private Unbinder unbinder;
    private boolean selectAll = false;
    private boolean selectFirst = false;
    private boolean selectSecond = false;
    private boolean selectThird = false;
    private boolean selectFourth = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_supplier);
        unbinder = ButterKnife.bind(this);

    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.invite_supplier));
    }

    @OnClick({R.id.tv_charge_standard, R.id.iv_check_supplier1, R.id.rl_supplier1,
            R.id.iv_check_supplier2, R.id.rl_supplier2, R.id.iv_check_supplier3, R.id.rl_supplier3,
            R.id.iv_check_supplier4, R.id.rl_supplier4, R.id.iv_select_all, R.id.immediate_payment})
    public void onClick(View view) {
        changeSelectionState(view.getId());
        switch (view.getId()) {
            case R.id.tv_charge_standard:
                ToastUtils.show(this, "点击了收费标准");
                break;
            case R.id.iv_select_all:
                doSelectAll();
                break;
            case R.id.immediate_payment:
                startActivity(new Intent(mContext, ReleaseRewardPayActivity.class));
                break;
        }
    }

    /**
     * 改变选中后的状态
     *
     * @param id
     */
    private void changeSelectionState(int id) {
        switch (id) {
            // 供应商一
            case R.id.iv_check_supplier1:
            case R.id.rl_supplier1:
                boolean flag1 = selectFirst ? false : true;
                selectFirst = flag1;
                setSelectedImage(ivCheckSupplier1, selectFirst);
                break;
            // 供应商二
            case R.id.iv_check_supplier2:
            case R.id.rl_supplier2:
                boolean flag2 = selectSecond ? false : true;
                selectSecond = flag2;
                setSelectedImage(ivCheckSupplier2, selectSecond);
                break;
            // 供应商三
            case R.id.iv_check_supplier3:
            case R.id.rl_supplier3:
                boolean flag3 = selectThird ? false : true;
                selectThird = flag3;
                setSelectedImage(ivCheckSupplier3, selectThird);
                break;
            // 供应商四
            case R.id.iv_check_supplier4:
            case R.id.rl_supplier4:
                boolean flag4 = selectFourth ? false : true;
                selectFourth = flag4;
                setSelectedImage(ivCheckSupplier4, selectFourth);
                break;
            default:
                break;
        }
    }

    /**
     * 选择所有
     */
    private void doSelectAll() {
        if (selectAll) {
            selectAll = false;
            ivSelectAll.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            ivCheckSupplier1.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            ivCheckSupplier2.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            ivCheckSupplier3.setImageDrawable(getResources().getDrawable(R.drawable.checked));
            ivCheckSupplier4.setImageDrawable(getResources().getDrawable(R.drawable.checked));
        } else {
            selectAll = true;
            ivSelectAll.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
            ivCheckSupplier1.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
            ivCheckSupplier2.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
            ivCheckSupplier3.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
            ivCheckSupplier4.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
        }
    }

    /**
     * 设置背景图片
     *
     * @param imageView
     * @param which
     */
    private void setSelectedImage(ImageView imageView, boolean which) {
        if (which) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.checked));
        } else {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
