package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.fragment.InterestedTopicFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lin on 2016/12/13.
 */

public class ReleaseRewardClassificationActivity extends BaseToolbarActivity {
    @BindView(R.id.btn_next_step)
    Button btnNextStep;
    @BindView(R.id.rl_classification)
    RelativeLayout rlClassification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_reward_classification);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rl_classification, InterestedTopicFragment.newInstance()).commit();

    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.choise_type));
    }

    @OnClick(R.id.btn_next_step)
    public void onClick() {
        String type = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(type)) {
            return;
        }
        Intent intent = new Intent(mContext, ReleaseRewardActivity.class);
        if ("tender".equals(type)) {
            intent.putExtra("type", "tender");
        } else {
            intent.putExtra("type", "rewards");
        }
        startActivity(intent);
    }
}
