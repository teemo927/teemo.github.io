package com.lanjiang.figersland.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lanjiang.figersland.BaseActivity;
import com.lanjiang.figersland.MainActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

import static android.R.attr.action;

/**
 * Created by Lin on 2016/12/7.
 */

public class LoginActivity extends BaseActivity implements PlatformActionListener {
    @BindView(R.id.btn_skip)
    Button btnSkip;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_free_register)
    TextView tvFreeRegister;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_other_login)
    TextView tvOtherLogin;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.iv_QQ)
    ImageView ivQQ;
    @BindView(R.id.iv_weibo)
    ImageView ivWeibo;


    // 找到用户
    private static final int MSG_USERID_FOUND = 0;
    // 第三方登录成功
    private static final int MSG_AUTH_COMPLETE = 1;
    // 第三方登录失败
    private static final int MSG_AUTH_ERROR = 2;
    // 第三方登录取消
    private static final int MSG_AUTH_CANCEL = 3;
    // 跳转到注册页面
    public static final int REGISTER = 4;
    // 跳转到忘记密码页面
    public static final int FORGET_PASSWORD = 5;

    private Unbinder unbinder;
    private Platform qq;
    private Platform weixin;
    private Platform weibo;

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_AUTH_COMPLETE:
                    ToastUtils.show(LoginActivity.this, "登录成功！");
                    skipActivity(LoginActivity.this, MainActivity.class);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 初始化
     */
    @Override
    protected void initView() {
        unbinder = ButterKnife.bind(this);
        // 初始化第三方登录
        ShareSDK.initSDK(this);
        qq = ShareSDK.getPlatform(QQ.NAME);
        weixin = ShareSDK.getPlatform(Wechat.NAME);
        weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
    }

    @OnClick({R.id.btn_skip, R.id.btn_login, R.id.tv_free_register, R.id.tv_forget_password,
            R.id.iv_weixin, R.id.iv_QQ, R.id.iv_weibo})
    public void onClick(View view) {
        switch (view.getId()) {
            // 跳到主界面
            case R.id.btn_skip:
                skipActivity(LoginActivity.this, MainActivity.class);
                break;
            // 账号登录
            case R.id.btn_login:
                doLogin();
                break;
            // 注册
            case R.id.tv_free_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("where", REGISTER);
                startActivity(intent);
                break;
            // 忘记密码
            case R.id.tv_forget_password:
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                intent2.putExtra("where", FORGET_PASSWORD);
                startActivity(intent2);
                break;
            // 微信登录
            case R.id.iv_weixin:
                Toast.makeText(this, "点击了weixin登录", Toast.LENGTH_SHORT).show();
                authorize(weixin);
                break;
            // QQ登录
            case R.id.iv_QQ:
                Toast.makeText(this, "点击了qq登录", Toast.LENGTH_SHORT).show();
                authorize(qq);
                break;
            // 微博登录
            case R.id.iv_weibo:
                Toast.makeText(this, "点击了weibo登录", Toast.LENGTH_SHORT).show();
                authorize(weibo);
                break;
        }
    }

    /**
     * 登录
     */
    private void doLogin() {
        /*String userName = "13112341234";
        String password = "123456";
        String etPhoneText = etPhoneNumber.getText().toString().trim();
        String etPasswordText = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(etPhoneText) || TextUtils.isEmpty(etPasswordText)) {
            String prompt = "用户名或密码不能为空";
            ToastUtils.show(this, prompt);
            return;
        }
        if (!userName.equals(etPhoneText) || !password.equals(etPasswordText)) {
            String prompt = "用户名或密码错误";
            etPassword.setText("");
            ToastUtils.show(this, prompt);
            return;
        }*/
        String prompt = "登录成功";
        ToastUtils.show(this, prompt);
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("from", "login");
        startActivity(intent);
    }

    /**
     * 页面跳转
     *
     * @param packageContext
     * @param cls
     */
    private void skipActivity(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        startActivity(intent);
    }

    /**
     * 执行授权,获取用户信息
     *
     * @param platform
     */
    private void authorize(Platform platform) {
        if (platform == null) {
            return;
        }

        platform.setPlatformActionListener(this);
        // 关闭SSO-->登录(客户端)授权
        platform.SSOSetting(false);

        platform.showUser(null);
    }

    /**
     * 取消授权
     */
    private void loginOut() {
        if (qq != null && qq.isValid()) {
            qq.removeAccount(true);
            Log.e(TAG, "QQ Destroyed");
        }
        if (weixin != null && weixin.isValid()) {
            weixin.removeAccount(true);
            Log.e(TAG, "weixin Destroyed");
        }
        if (weibo != null && weibo.isValid()) {
            weibo.removeAccount(true);
            Log.e(TAG, "weibo Destroyed");
        }
    }

    // 第三方登录的回调
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        // 输出所有授权用户信息
        String str = platform.getDb().exportData();
        LogUtils.e("TAG", str);

        Message msg = Message.obtain();//直接差入队列
        // 判断走了哪个回调
        msg.what = MSG_AUTH_COMPLETE;
        // 用户的信息
        msg.obj = new Object[]{platform.getName(), hashMap};
        mhandler.sendMessage(msg);

        /*if (action == Platform.ACTION_USER_INFOR) {
            Log.e(TAG, "onComplete: 进来了" );
            Message msg = Message.obtain();// 直接差入队列
            // 判断走了哪个回调
            msg.what = MSG_AUTH_COMPLETE;
            // 用户的信息
            msg.obj = new Object[]{platform.getName(), hashMap};
            mhandler.sendMessage(msg);
        }*/
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        if (action == Platform.ACTION_USER_INFOR) {
            mhandler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
    }

    @Override
    public void onCancel(Platform platform, int i) {
        if (action == Platform.ACTION_USER_INFOR) {
            mhandler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "-----------onDestroy:----------- ");
        // 取消注册butterKnife
        if (unbinder != null) {
            unbinder.unbind();
        }
        // 取消授权
        loginOut();
    }

}
