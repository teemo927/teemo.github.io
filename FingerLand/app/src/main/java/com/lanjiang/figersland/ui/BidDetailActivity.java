package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.view.WindowManager;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.Constant;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.fragment.BidDetailFragment;
import com.lanjiang.figersland.utils.LogUtils;

public class BidDetailActivity extends BaseToolbarActivity {

    //页面类型，Constant.TYPE_BID 招标信息 ，Constant.TYPE_REWARD 投标信息
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_detail);
        handleIntent();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_bid_detail, BidDetailFragment.newInstance(type, "")).commit();

        initView();
    }

    @Override
    public void setActionBar() {
//        setActivityTitle(getString(R.string.bid_detail));
    }

    private void initView() {

    }

    /**
     * 处理上一界面参数
     */
    private void handleIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            LogUtils.e(TAG, TAG + getString(R.string.no_intent));
            finish();
            return;
        }

        type = bundle.getInt(Constant.ACTIVITY_TYPE);

        // Constant.TYPE_BID 招标信息 ，Constant.TYPE_REWARD 投标信息
        setActivityTitle(type == Constant.TYPE_BID ? getString(R.string.reward_details)
                                                   : getString(R.string.bid_detail));
    }

    @Override
    protected void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 1.0f;
        this.getWindow().setAttributes(params);
    }
}
