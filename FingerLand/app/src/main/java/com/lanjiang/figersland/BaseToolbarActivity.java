package com.lanjiang.figersland;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.adapter.BannerAdapter;
import com.lanjiang.figersland.ui.BidActivity;
import com.lanjiang.figersland.ui.SearchActivity;
import com.lanjiang.figersland.utils.PopUtils;
import com.lanjiang.figersland.utils.ShareUtils;
import com.lanjiang.figersland.utils.SystemBarTintManager;

import butterknife.Unbinder;

/**
 * .
 * Created by Asus on 2016/12/7.
 */

public abstract class BaseToolbarActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected LayoutInflater mInflater;

    //内容布局
    private ViewGroup contentView;

    //导航栏
    private ViewGroup mTitleBar;
    private TextView mTitle;
    private ImageView mIvLeft;
    private TextView mTvLeft;
    private ImageView mIvRight;
    private TextView mTvRight;
    private ImageView mIvSearch;
    private View line;
    private View.OnClickListener mListenerLeft;

    protected Unbinder unbinder;
    protected BannerAdapter mBannerAdapter;//轮播

    /**
     * 将其他布局添加到标题栏下方
     *
     * @param savedInstanceState .
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mInflater = LayoutInflater.from(mContext);
        //先执行父类
        setContentView(R.layout.activity_base_toolbar);

        //使用第三方解决，弊端颜色变暗（需同时在style中设置透明状态栏）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int color = R.color.colorPrimary;
            mTitleBar.setBackgroundResource(color);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintResource(color);
            tintManager.setStatusBarTintEnabled(true);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBannerAdapter != null) {
            mBannerAdapter.stop();
            mBannerAdapter = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBannerAdapter != null) {
            mBannerAdapter.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mBannerAdapter != null) {
            mBannerAdapter.stop();
            mBannerAdapter = null;
        }
    }

    /**
     * 重写
     * 将内容布局放置标题栏下方
     *
     * @param layoutResID 内容布局中添加指定layout
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        if (contentView == null && R.layout.activity_base_toolbar == layoutResID) {
            super.setContentView(R.layout.activity_base_toolbar);

            //状态栏相关控件
            mTitleBar = (ViewGroup) findViewById(R.id.title_bar);
            mIvLeft = (ImageView) findViewById(R.id.iv_left);
            mTvLeft = (TextView) findViewById(R.id.tv_left);
            mTitle = (TextView) findViewById(R.id.toolbar_title);
            mIvSearch = (ImageView) findViewById(R.id.iv_search);
            mTvRight = (TextView) findViewById(R.id.tv_right);
            mIvRight = (ImageView) findViewById(R.id.iv_right);
            line = findViewById(R.id.under_line);
            if (Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){//4.4以上使用translateZ
                line.setVisibility(View.GONE);
            }

            contentView = (ViewGroup) findViewById(R.id.layout_center);
            contentView.removeAllViews();

        } else if (layoutResID != R.layout.activity_base_toolbar) {
            //内容布局中添加指定layout
            View addView = mInflater.inflate(layoutResID, null);
            contentView.addView(addView, new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            setActionBar();
            afterSettingActionBar();
        }
    }

    /**
     * 设置标题栏
     */
    public abstract void setActionBar();

    private void afterSettingActionBar() {
        if (null == mListenerLeft) {
            mIvLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /**
     * 设置左侧图标及监听器
     *
     * @param imgId    图标
     * @param listener 监听器
     */
    public void setLeftImg(@DrawableRes int imgId, View.OnClickListener listener) {
        setLeftImg(imgId);
        setOnLeftImgClickListener(listener);
    }

    private void setLeftImg(@DrawableRes int imgId) {
        mIvLeft.setVisibility(View.VISIBLE);
        mIvLeft.setImageResource(imgId);
    }

    public void setOnLeftImgClickListener(View.OnClickListener listenerLeft) {
        mListenerLeft = listenerLeft;
        mIvLeft.setOnClickListener(listenerLeft);
    }

    protected void setLeftTv(String text, View.OnClickListener listener) {
        mIvLeft.setVisibility(View.GONE);
        mTvLeft.setVisibility(View.VISIBLE);
        mTvLeft.setText(text);
        mTvLeft.setOnClickListener(listener);
    }

    protected void setLeftTv(String text) {
        mTvLeft.setText(text);
    }


    /**
     * 设置标题
     *
     * @param text 标题
     */
    public void setActivityTitle(String text) {
        mTitle.setText(text);
    }

    public void setActivityTitle(@StringRes int textId) {
        mTitle.setText(textId);
    }


    /**
     * 设置右边侧图标及监听器
     *
     * @param imgId    图标
     * @param listener 监听器
     */
    public void setRight(@DrawableRes int imgId, View.OnClickListener listener) {
        setRightImg(imgId);
        setOnRightClickListener(listener);
    }

    public void setRightImg(@DrawableRes int imgId) {
        mIvRight.setVisibility(View.VISIBLE);
        mIvRight.setImageResource(imgId);
    }

    public void setOnRightClickListener(View.OnClickListener listenerRight) {
        mIvRight.setVisibility(View.VISIBLE);
        mIvRight.setOnClickListener(listenerRight);
    }

    protected void setRightTv(String text, View.OnClickListener listener) {
        mIvRight.setVisibility(View.GONE);
        mTvRight.setVisibility(View.VISIBLE);
        mTvRight.setText(text);
        mTvRight.setOnClickListener(listener);
    }


    /**
     * 设置菜单 ,跳转主界面
     */
    public void setRightMenu() {
        int drawableRes = R.drawable.qianse_bg;
        if (mContext instanceof BidActivity) {
            drawableRes = R.drawable.shense_bg;
        }

        final int finalDrawableRes = drawableRes;
        setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUtils.showMainMenuWindow(mContext, finalDrawableRes, mIvRight, mTitle.getHeight());
            }
        });
    }

    /**
     * 设置搜索可见，同时菜单可见，点击跳转搜索界面
     *
     * @param visible .
     */
    public void setSearchVisible(int visible) {
        if (visible == View.VISIBLE) {
            mIvSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSearchAction();
                }
            });
        }
        mIvSearch.setVisibility(visible);
        //同时菜单可见
        if (mIvRight.getVisibility() == View.GONE) {
            mIvRight.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 搜索动作
     */
    public void setSearchAction() {
        startActivity(new Intent(mContext, SearchActivity.class));
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //增加过度动画
        //        overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
    }


    /**
     * @param platform 指定分享平台，和slient一起使用可以直接分享到指定的平台
     * @param silent   是否直接分享（true则直接分享）
     */
    public void share(String platform, boolean silent) {
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 0.7f;
        this.getWindow().setAttributes(params);
        ShareUtils.showOnekeyshare(mContext, platform, silent);
    }

    public void talk() {
//        Intent intent = new Intent();
//        //包名 包名+类名（全路径）
//        intent.setClassName("com.netease.nim.demo", "com.netease.nim.demo.main.activity.P2PMessageActivity");
//        intent.putExtra(Extras.EXTRA_ACCOUNT, "1");
//        intent.putExtra(Extras.EXTRA_FROM, "from_notification");
//        startActivity(intent);
    }

}
