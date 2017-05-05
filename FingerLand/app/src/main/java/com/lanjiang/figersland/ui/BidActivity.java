package com.lanjiang.figersland.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.fragment.BidFragment;
import com.lanjiang.figersland.fragment.RewardRankingFragment;
import com.lanjiang.figersland.utils.AnimUtils;
import com.lanjiang.figersland.utils.SizeUtils;
import com.lanjiang.figersland.utils.ToastUtils;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 招投标页面
 */
public class BidActivity extends BaseToolbarActivity implements BidFragment.OnFragmentInteractionListener, RewardRankingFragment.OnFragmentInteractionListener {

    private static final String ENTERPRISE_BID = "Enterprise_Bid";
    private static final String REWARD_RANKING = "Reward_ranking";

    private Fragment mCurrentFragment;
    private Fragment mBidFragment;
    private Fragment mRewardRankingFragment;

    /**
     * 发布按钮展开
     */
    private boolean isMenuOpen = false;
    private int mStartY;

    @BindView(R.id.iv_pen)
    ImageView ivPen;
    @BindView(R.id.llt_publish)
    RelativeLayout lltPublish;

    @BindView(R.id.bid_container)
    LinearLayout bidContainer;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    @BindView(R.id.view_toot)
    View viewRoot;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @OnClick({R.id.fab, R.id.view_toot, R.id.tv_publish})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showPublishView();
                break;
            case R.id.view_toot:
                closePublishView();
                break;
            case R.id.tv_publish:
                startActivity(new Intent(mContext, ReleaseTenderActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bid);
        ButterKnife.bind(this);

        initFragment(savedInstanceState);

        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.bid);
        setLeftImg(R.drawable.selector_search_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SearchActivity.class));
            }
        });
        setRightMenu();
    }

    private void initFragment(Bundle savedInstanceState) {
        //避免fragment重叠
        if (savedInstanceState == null) {
            mBidFragment = BidFragment.newInstance("", "");
            mRewardRankingFragment = RewardRankingFragment.newInstance("", "");

            getSupportFragmentManager().beginTransaction().replace(R.id.bid_container, mBidFragment, ENTERPRISE_BID).commit();
            mCurrentFragment = mBidFragment;
        } else {
            //先通过id或者tag找到“复活”的所有UI-Fragment
            mBidFragment = getSupportFragmentManager().findFragmentByTag(ENTERPRISE_BID);
            mRewardRankingFragment = getSupportFragmentManager().findFragmentByTag(REWARD_RANKING);

            //show()一个即可
            getSupportFragmentManager().beginTransaction()
                    .show(mBidFragment)
                    .hide(mRewardRankingFragment)
                    .commit();
        }
    }

    private void initView() {
        TabLayout.Tab tabLeft = tabLayout.newTab().setText(getString(R.string.enterprise_bid)).setTag(ENTERPRISE_BID);
        tabLayout.addTab(tabLeft, true);
        TabLayout.Tab tabRight = tabLayout.newTab().setText(getString(R.string.reward_ranking)).setTag(REWARD_RANKING);
        tabLayout.addTab(tabRight);
        int leftRight = (int) getResources().getDimension(R.dimen.dp_18);
        setIndicator(mContext, tabLayout, leftRight, leftRight);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getTag() == ENTERPRISE_BID) {
                    tvPublish.setText(getString(R.string.publish_bid));
                    switchContent(mCurrentFragment, mBidFragment);
                } else if (tab.getTag() == REWARD_RANKING) {
                    tvPublish.setText(getString(R.string.publish_reword));
                    switchContent(mCurrentFragment, mRewardRankingFragment);
                } else {
                    ToastUtils.show(mContext, "no no no ");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void switchContent(Fragment from, Fragment to) {
        if (from == null || to == null || from == to) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);

        if (!to.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.hide(from).add(R.id.bid_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }

        mCurrentFragment = to;
    }

    /**
     * 展开发布视图
     */
    private void showPublishView() {
        if (isMenuOpen) {
            viewRoot.setVisibility(View.GONE);
            animatorClose();
        } else {
            viewRoot.setVisibility(View.VISIBLE);
            tvPublish.setVisibility(View.VISIBLE);
            animatorOpen();
        }
        isMenuOpen = !isMenuOpen;
    }

    /**
     * 收起发布视图
     */
    private void closePublishView() {
        if (isMenuOpen) {
            viewRoot.setVisibility(View.GONE);
            animatorClose();
        }
        isMenuOpen = !isMenuOpen;
    }

    private void animatorClose() {
        ObjectAnimator animatorClose = ObjectAnimator.ofFloat(fab, "rotation", -135, 20, 0);
        animatorClose.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorClose.setDuration(150).start();

        Animation anim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, (float) 0.0
                , Animation.RELATIVE_TO_SELF, (float) 2.0
                , Animation.RELATIVE_TO_SELF, (float) 0.0
                , Animation.RELATIVE_TO_SELF, (float) 0.0);

//        Animation anim = new ScaleAnimation((float) 1.0, (float) 0.0, (float) 1.0, (float) 0.0
//                , Animation.RELATIVE_TO_SELF
//                , (float) 0.5
//                , Animation.RELATIVE_TO_SELF
//                , (float) 0.5);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvPublish.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim.setDuration(300);
        anim.setFillAfter(true);
        lltPublish.setAnimation(anim);
    }

    private void animatorOpen() {
        lltPublish.setVisibility(View.VISIBLE);

        ObjectAnimator animatorOpen = ObjectAnimator.ofFloat(fab, "rotation", -155, -135);
        animatorOpen.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorOpen.setDuration(80).start();

        Animation anim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, (float) 1.0
                , Animation.RELATIVE_TO_SELF, (float) 0.0
                , Animation.RELATIVE_TO_SELF, (float) 0.0
                , Animation.RELATIVE_TO_SELF, (float) 0.0);

//        Animation anim = new ScaleAnimation((float) 0.0, (float) 1.0, (float) 0.0, (float) 1.0
//                , Animation.RELATIVE_TO_SELF
//                , (float) 0.5
//                , Animation.RELATIVE_TO_SELF
//                , (float) 0.5);
        anim.setDuration(300);
        anim.setFillAfter(true);
        lltPublish.setAnimation(anim);
    }

    /**
     * 设置顶部 TabLayout 宽度
     *
     * @param context  .
     * @param tabs     父布局
     * @param leftDip  左边距
     * @param rightDip 右边距
     */
    public void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = SizeUtils.dp2px(context, leftDip);
        int right = SizeUtils.dp2px(context, rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    public void onFragmentInteraction(boolean show) {
        if (show) {
            AnimUtils.animatorShow(mContext,fab,mStartY);
        } else {
            int move = SizeUtils.dp2px(mContext, 40) + fab.getHeight();
            AnimUtils.animatorHide(mContext,fab,move,mStartY);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mStartY = AnimUtils.getCurrentFabY(fab);
        super.onWindowFocusChanged(hasFocus);
    }
}
