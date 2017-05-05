package com.lanjiang.figersland.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.utils.WidgetUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lanjiang.figersland.utils.WidgetUtils.changeVisibility;

/**
 * Created by Lin on 2016/12/13.
 */

public class MyReleaseRewardDetailActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_release_date_content)
    TextView tvReleaseDateContent;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_end_date_content)
    TextView tvEndDateContent;
    @BindView(R.id.tv_project_area)
    TextView tvProjectArea;
    @BindView(R.id.tv_project_area_content)
    TextView tvProjectAreaContent;
    @BindView(R.id.tv_classification_content)
    TextView tvClassificationContent;
    @BindView(R.id.tv_reward_result)
    TextView tvRewardResult;
    @BindView(R.id.tv_recruiting_range_content)
    TextView tvRecruitingRangeContent;
    @BindView(R.id.iv_click_contact_details)
    ImageView ivClickContactDetails;
    @BindView(R.id.iv_click_sign_up_details)
    ImageView ivClickSignUpDetails;
    @BindView(R.id.iv_click_tender_details)
    ImageView ivClickTenderDetails;
    @BindView(R.id.iv_check)
    ImageView ivCheck;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.iv_click_details)
    ImageView ivClickDetails;
    @BindView(R.id.iv_check2)
    ImageView ivCheck2;
    @BindView(R.id.iv_logo2)
    ImageView ivLogo2;
    @BindView(R.id.iv_click_details2)
    ImageView ivClickDetails2;
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
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.tv_contact_content)
    TextView tvContactContent;
    @BindView(R.id.tv_sign_up_description)
    TextView tvSignUpDescription;
    @BindView(R.id.tv_tender_detail)
    TextView tvTenderDetail;
    @BindView(R.id.ll_company_detail)
    LinearLayout llCompanyDetail;
    @BindView(R.id.ll_company_detail2)
    LinearLayout llCompanyDetail2;
    @BindView(R.id.tv_company_service_area_content2)
    TextView tvCompanyServiceAreaContent2;
    @BindView(R.id.tv_company_registered_capital_content2)
    TextView tvCompanyRegisteredCapitalContent2;
    @BindView(R.id.tv_company_contact_content2)
    TextView tvCompanyContactContent2;
    @BindView(R.id.tv_company_main_business_content2)
    TextView tvCompanyMainBusinessContent2;
    @BindView(R.id.tv_sign_up_description_content2)
    TextView tvSignUpDescriptionContent2;
    @BindView(R.id.tv_file_download2)
    TextView tvFileDownload2;
    @BindView(R.id.rl_company1)
    RelativeLayout rlCompany1;
    @BindView(R.id.rl_company2)
    RelativeLayout rlCompany2;


    boolean checked1 = false;
    boolean checked2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release_reward_detail);
        super.unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.post_reward_details));
    }

    @OnClick({R.id.tv_edit, R.id.iv_click_contact_details, R.id.iv_click_sign_up_details,
            R.id.iv_click_tender_details, R.id.tv_sure, R.id.iv_check, R.id.iv_click_details,
            R.id.iv_check2, R.id.iv_click_details2, R.id.tv_file_download, R.id.rl_company1,
            R.id.rl_company2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                Intent intent = new Intent(mContext, ReleaseRewardClassificationActivity.class);
                intent.putExtra("type", "rewards");
                startActivity(intent);
                break;
            case R.id.iv_click_contact_details:
                WidgetUtils.changeVisibility(tvContactContent);
                break;
            case R.id.iv_click_sign_up_details:
                WidgetUtils.changeVisibility(tvSignUpDescription);
                break;
            case R.id.iv_click_tender_details:
                WidgetUtils.changeVisibility(tvTenderDetail);
                break;
            case R.id.tv_sure:
                if ((checked1 == false) && (checked2 == false)) {
                    String prompt = "请选择之后再确定";
                    ToastUtils.show(mContext, prompt);
                    return;
                }
                tvSure.setBackgroundResource(R.drawable.shape_btn_blue);
                tvSure.setTextColor(getResources().getColor(R.color.color_main));
                break;
            case R.id.iv_check:
            case R.id.rl_company1:
                WidgetUtils.changeCheckedImage(mContext, ivCheck, R.drawable.checked, R.drawable.unchecked);
                changeCheckedImageState(mContext, ivCheck);
                break;
            case R.id.iv_click_details:
                changeVisibility(llCompanyDetail);
                break;
            case R.id.iv_check2:
            case R.id.rl_company2:
                WidgetUtils.changeCheckedImage(mContext, ivCheck2, R.drawable.checked, R.drawable.unchecked);
                changeCheckedImageState(mContext, ivCheck);
                break;
            case R.id.iv_click_details2:
                changeVisibility(llCompanyDetail2);
                break;
            case R.id.tv_file_download:
                break;
        }
    }

    /**
     * 改变选中图片状态
     *
     * @param imageView
     */
    public void changeCheckedImageState(Context context, ImageView imageView) {
        Drawable.ConstantState drawable1 = imageView.getDrawable().getConstantState();
        Drawable.ConstantState drawable2 = context.getResources().getDrawable(R.drawable.unchecked).getConstantState();
        if (drawable1 == drawable2) {
            checked1 = false;
        } else {
            checked1 = true;
        }
    }

}
