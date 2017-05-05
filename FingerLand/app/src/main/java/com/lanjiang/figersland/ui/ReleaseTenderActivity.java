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
import butterknife.Unbinder;

/**
 * Created by Lin on 2016/12/14.
 */

public class ReleaseTenderActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_tender_project)
    TextView tvTenderProject;
    @BindView(R.id.tv_suitable_for_large)
    TextView tvSuitableForLarge;
    @BindView(R.id.iv_next_large)
    ImageView ivNextLarge;
    @BindView(R.id.rl_large)
    RelativeLayout rlLarge;
    @BindView(R.id.tv_reward_project)
    TextView tvRewardProject;
    @BindView(R.id.tv_suitable_for_small)
    TextView tvSuitableForSmall;
    @BindView(R.id.rl_small)
    RelativeLayout rlSmall;
    @BindView(R.id.iv_next_small)
    ImageView ivNextSmall;
    @BindView(R.id.tv_description_content)
    TextView tvDescriptionContent;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;

    private int checked = 0;
    private static final int CHECKED_TENDER = 0x001;
    private static final int CHECKED_REWARDS = 0x002;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_tender);
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

    @OnClick({R.id.iv_next_large, R.id.rl_large, R.id.rl_small, R.id.iv_next_small, R.id.btn_next_step})
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
            // 招投标项目
            case R.id.rl_large:
            case R.id.iv_next_large:
                rlSmall.setBackgroundColor(getResources().getColor(R.color.white));
                tvRewardProject.setTextColor(getResources().getColor(R.color.background_navigation));
                tvSuitableForSmall.setTextColor(getResources().getColor(R.color.color_main));
                ivNextSmall.setImageDrawable(getResources().getDrawable(R.drawable.next_grey));

                rlLarge.setBackgroundColor(getResources().getColor(R.color.group_select));
                tvTenderProject.setTextColor(getResources().getColor(R.color.white));
                tvSuitableForLarge.setTextColor(getResources().getColor(R.color.white));
                ivNextLarge.setImageDrawable(getResources().getDrawable(R.drawable.next_white));
                tvDescriptionContent.setText(getResources().getText(R.string.description_large));
                checked = CHECKED_TENDER;
                break;
            // 悬赏项目
            case R.id.rl_small:
            case R.id.iv_next_small:
                rlLarge.setBackgroundColor(getResources().getColor(R.color.white));
                tvTenderProject.setTextColor(getResources().getColor(R.color.background_navigation));
                tvSuitableForLarge.setTextColor(getResources().getColor(R.color.color_main));
                ivNextLarge.setImageDrawable(getResources().getDrawable(R.drawable.next_grey));

                rlSmall.setBackgroundColor(getResources().getColor(R.color.group_select));
                tvRewardProject.setTextColor(getResources().getColor(R.color.white));
                tvSuitableForSmall.setTextColor(getResources().getColor(R.color.white));
                ivNextSmall.setImageDrawable(getResources().getDrawable(R.drawable.next_white));
                tvDescriptionContent.setText(getResources().getText(R.string.description_small));
                checked = CHECKED_REWARDS;
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
        Intent intent = new Intent(mContext, ReleaseRewardClassificationActivity.class);
        switch (checked) {
            case CHECKED_TENDER:
                intent.putExtra("type", "tender");
                break;
            case CHECKED_REWARDS:
                intent.putExtra("type", "rewards");
                break;
            default:
                break;
        }
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
