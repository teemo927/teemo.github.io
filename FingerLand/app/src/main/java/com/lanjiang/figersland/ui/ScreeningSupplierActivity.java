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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lin on 2016/12/16.
 */

public class ScreeningSupplierActivity extends BaseToolbarActivity {

    @BindView(R.id.iv_check_plan1)
    ImageView ivCheckPlan1;
    @BindView(R.id.tv_plan1)
    TextView tvPlan1;
    @BindView(R.id.tv_plan1_price)
    TextView tvPlan1Price;
    @BindView(R.id.rl_plan1)
    RelativeLayout rlPlan1;
    @BindView(R.id.iv_check_plan2)
    ImageView ivCheckPlan2;
    @BindView(R.id.tv_plan2)
    TextView tvPlan2;
    @BindView(R.id.tv_plan2_price)
    TextView tvPlan2Price;
    @BindView(R.id.rl_plan2)
    RelativeLayout rlPlan2;
    @BindView(R.id.iv_check_plan3)
    ImageView ivCheckPlan3;
    @BindView(R.id.tv_plan3)
    TextView tvPlan3;
    @BindView(R.id.tv_plan3_price)
    TextView tvPlan3Price;
    @BindView(R.id.rl_plan3)
    RelativeLayout rlPlan3;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_supplier);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.screening_supplier));
    }

    @OnClick({R.id.iv_check_plan1, R.id.rl_plan1, R.id.iv_check_plan2, R.id.rl_plan2,
            R.id.iv_check_plan3, R.id.rl_plan3, R.id.btn_next_step})
    public void onClick(View view) {
        changeSelectionState(view.getId());
        switch (view.getId()) {
            case R.id.btn_next_step:
                startActivity(new Intent(mContext, ReleaseRewardPayActivity.class));
                break;
            default:
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
            // 方案一
            case R.id.rl_plan1:
            case R.id.iv_check_plan1:
                ivCheckPlan1.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                ivCheckPlan2.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
                ivCheckPlan3.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
                break;
            // 方案二
            case R.id.rl_plan2:
            case R.id.iv_check_plan2:
                ivCheckPlan2.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                ivCheckPlan1.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
                ivCheckPlan3.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
                break;
            // 方案三
            case R.id.rl_plan3:
            case R.id.iv_check_plan3:
                ivCheckPlan3.setImageDrawable(getResources().getDrawable(R.drawable.checked));
                ivCheckPlan1.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
                ivCheckPlan2.setImageDrawable(getResources().getDrawable(R.drawable.ssdk_oks_classic_check_default));
                break;
            default:
                break;
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
