package com.lanjiang.figersland;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.adapter.MainPagerAdapter;
import com.lanjiang.figersland.http.HttpManager;
import com.lanjiang.figersland.ui.LoginActivity;
import com.lanjiang.figersland.ui.VerifyActivity;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.utils.ScreenUtils;
import com.lanjiang.figersland.utils.SizeUtils;
import com.lanjiang.figersland.widget.CirclePageIndicator;
import com.lanjiang.figersland.widget.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.lanjiang.figersland.R.id.login_tv;

public class MainActivity extends BaseActivity {

    //第一个展示的项
    public static int FIRST_APPEARED_ITEM = 1;

    private List<View> mViewList = new ArrayList<>();

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    @BindView(login_tv)
    TextView loginTv;
    @BindView(R.id.verify_btn)
    Button verifyBtn;
    @BindView(R.id.activity_land)
    LinearLayout activityLand;

    private String from;

    @OnClick({login_tv, R.id.verify_btn})
    public void enter(View view) {
        switch (view.getId()) {
            case login_tv:
                if (BuildConfig.DEBUG) {
                    try {
                        LogUtils.e(TAG, "enter: " + "try1");
                        HttpManager.testNet();
                        LogUtils.e(TAG, "enter: " + "try2");
                    } catch (IOException e) {
                        e.printStackTrace();
                        LogUtils.e(TAG, "enter: " + "catch");
                        return;
                    } finally {
                        LogUtils.e(TAG, "enter: " + "finally");
                    }
                }else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.verify_btn:
                int height = ScreenUtils.getScreenHeight(mContext);
                int width = ScreenUtils.getScreenWidth(mContext);
                int barHeight = ScreenUtils.getStatusBarHeight(mContext);
                int statusBarHeight = ScreenUtils.getActionBarHeight(MainActivity.this);
                LogUtils.i(TAG, "---height:" + height + "width" + width + "barHeight" + barHeight + "statusBarHeight" + statusBarHeight);
                startActivity(new Intent(mContext, VerifyActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //从登录界面进来，隐藏登录按钮
        from = getIntent().getStringExtra("from");
        if (!TextUtils.isEmpty(from) || "login".equals(from)) {
            loginTv.setVisibility(View.INVISIBLE);
        }

        getLastIntent();
        initData();

        viewpager.setAdapter(new MainPagerAdapter(mContext, mViewList, viewpager, false));
        indicator.setViewPager(viewpager, FIRST_APPEARED_ITEM);
        viewpager.setPageMargin(SizeUtils.dp2px(mContext, getResources().getDimension(R.dimen.dp_4)));

    }

    private void getLastIntent() {
    }

    /**
     * 初始化数据: 视图，图片资源，文字资源, 并设置点击事件
     */
    private void initData() {

        int[] resIds = new int[]{R.drawable.qianqi_image, R.drawable.tendering_image, R.drawable.accoune_image};
        String[] titleStr = new String[]{getString(R.string.pre_develop), getString(R.string.bid), getString(R.string.me)};
        String[] titleEngStr = new String[]{getString(R.string.pre_develop_en), getString(R.string.bid_en), getString(R.string.me_en)};

        int gap = getResources().getDimensionPixelOffset(R.dimen.dp_8);
        //左右距离屏幕距离之和 40dp
        int rest = getResources().getDimensionPixelOffset(R.dimen.dp_40);
        int adapterWidth = ScreenUtils.getScreenWidth(mContext) - rest;

        for (int i = 0; i < resIds.length; i++) {

            View root = mInflater.inflate(R.layout.adapter_main, null);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(adapterWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            root.setLayoutParams(param);

            RoundedImageView ivTitle = (RoundedImageView) root.findViewById(R.id.iv_title);
            ivTitle.setImageResource(resIds[i]);

            TextView tvChinese = (TextView) root.findViewById(R.id.tv_title_ch);
            tvChinese.setText(titleStr[i]);

            TextView tvEnglish = (TextView) root.findViewById(R.id.tv_title_en);
            tvEnglish.setText(titleEngStr[i]);

            mViewList.add(root);

        }

    }
}
