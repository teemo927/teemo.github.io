package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lin on 2016/12/13.
 */

public class MyOrderRewardDetailActivity extends BaseToolbarActivity {

    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_release_date_content)
    TextView tvReleaseDateContent;
    @BindView(R.id.tv_other1)
    TextView tvOther1;
    @BindView(R.id.tv_other2)
    TextView tvOther2;
    @BindView(R.id.tv_reward_result)
    TextView tvRewardResult;
    @BindView(R.id.rl_project)
    RelativeLayout rlProject;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_end_date_content)
    TextView tvEndDateContent;
    @BindView(R.id.tv_other3)
    TextView tvOther3;
    @BindView(R.id.tv_other4)
    TextView tvOther4;
    @BindView(R.id.tv_company_service_area_content)
    TextView tvCompanyServiceAreaContent;
    @BindView(R.id.tv_company_registered_capital_content)
    TextView tvCompanyRegisteredCapitalContent;
    @BindView(R.id.tv_company_contact_content)
    TextView tvCompanyContactContent;
    @BindView(R.id.tv_company_main_business_content)
    TextView tvCompanyMainBusinessContent;
    @BindView(R.id.tv_sign_up_description_content)
    TextView tvSignUpDescriptionContent;
    @BindView(R.id.tv_file_download)
    TextView tvFileDownload;
    @BindView(R.id.tv_reward_result_content)
    TextView tvRewardResultContent;

    private String titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fromWhere();
        setContentView(R.layout.activity_my_order_reward_detail);
        super.unbinder = ButterKnife.bind(this);
        changeLayout();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(titleName);
    }

    /**
     * 从哪个页面过来
     */
    private void fromWhere() {
        String type = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(type)) {
            return;
        }
        if ("tender".equals(type)) {
            titleName = getString(R.string.tender_order_details);
        } else {
            titleName = getString(R.string.post_reward_details);
        }
    }

    /**
     * 招标订单详情页和悬赏订单详情页共用页面的不同显示
     */
    private void changeLayout() {
        String type = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(type)) {
            return;
        }
        if ("tender".equals(type)) {
            titleName = getString(R.string.tender_order_details);
            tvRewardResult.setText(R.string.tender_result);
            tvRewardResultContent.setText(R.string.success);
        }
    }


    @OnClick(R.id.tv_file_download)
    public void onClick() {
    }
}
