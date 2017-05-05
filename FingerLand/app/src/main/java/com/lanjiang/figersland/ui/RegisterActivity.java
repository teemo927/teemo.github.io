package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.lanjiang.figersland.utils.MatchUtils;
import com.lanjiang.figersland.utils.SMSUtils;
import com.lanjiang.figersland.utils.ToastUtils;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

import static butterknife.ButterKnife.bind;

/**
 * Created by Lin on 2016/12/7.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.btn_get_verification_code)
    Button btnGetVerificationCode;
    @BindView(R.id.et_verCode)
    EditText etVerCode;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private Unbinder unbinder;
    private String phoneNumber;
    private String password;
    private String verCode;
    private EventHandler eventHandler;
    // 校验失败详情
    private String des;
    // 密码框默认不可见
    boolean eyeOpen = false;

    // 校验成功
    private static final int CHECK_SUCCESS = 0;
    // 校验失败
    private static final int CHECK_FAIL = 1;

    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case CHECK_SUCCESS:
                    // 注册成功，跳转至主界面
                    String prompt = "注册成功";
                    ToastUtils.show(RegisterActivity.this, prompt);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case CHECK_FAIL:
                    Toast.makeText(RegisterActivity.this, des, Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SMSUtils.initSMS(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        // 先判断跳转至哪个功能页面
        whichPage();
        unbinder = ButterKnife.bind(this);
        // 初始化短信验证回调
        initEventHandler();
    }

    /**
     * 跳转至哪个功能页面
     */
    private void whichPage() {
        Intent intent = getIntent();
        int where = intent.getIntExtra("where", -1);
        if (where == -1) {
            return;
        }
        if (where == LoginActivity.REGISTER) {
            // 跳转至注册功能
        }
        if (where == LoginActivity.FORGET_PASSWORD) {
            // 跳转至忘记密码功能
            tvForgetPassword.setVisibility(View.VISIBLE);
            btnRegister.setText("提交");
            eyeOpen = true;
            ivEye.setImageDrawable(getResources().getDrawable(R.drawable.eye_open));
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etPassword.setHint("新密码");
        }
    }

    // 初始化短信验证回调
    private void initEventHandler() {
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) { // 回调完成
                    // 提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
                    SMSResultComplete(event, data);
                } else {
                    try {
                        ((Throwable) data).printStackTrace();
                        Throwable throwable = (Throwable) data;
                        JSONObject object = new JSONObject(throwable.getMessage());
                        des = object.optString("detail");
                        if (!TextUtils.isEmpty(des)) {
                            mhandler.sendEmptyMessage(1);
                            return;
                        }
                    } catch (Exception e) {
                        SMSLog.getInstance().w(e);
                    }
                }
            }
        };
        // 注册回调接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.iv_back, R.id.btn_get_verification_code, R.id.btn_register, R.id.iv_eye})
    public void onClick(View view) {
        switch (view.getId()) {
            // 返回
            case R.id.iv_back:
                onBackPressed();
                break;
            // 获取验证码
            case R.id.btn_get_verification_code:
                getVerCode(phoneNumber);
                break;
            // 注册
            case R.id.btn_register:
                doRegister();
                break;
            // 改变密码框可见状态
            case R.id.iv_eye:
                changePasswordVisibility();
                break;
        }
    }

    /**
     * 改变密码框可见性
     */
    private void changePasswordVisibility() {
        if (eyeOpen) {
            ivEye.setImageDrawable(getResources().getDrawable(R.drawable.eye_close));
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            eyeOpen = false;
        } else {
            ivEye.setImageDrawable(getResources().getDrawable(R.drawable.eye_open));
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            eyeOpen = true;
        }
    }

    /**
     * 注册
     */
    private void doRegister() {
        getEditTextContent();
        if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(verCode)) {
            String prompt = "每一项都不能为空";
            ToastUtils.show(this, prompt);
            return;
        }
        // 验证手机号、密码及验证码
        boolean flagPhone = MatchUtils.isPhoneNumber(phoneNumber) ? true : false;
        boolean flagPassword = MatchUtils.isPassword(password) ? true : false;
        if (!flagPhone) {
            String prompt = "手机号码不合法";
            ToastUtils.show(this, prompt);
            return;
        }
        if (!flagPassword) {
            String prompt = "密码应为6-16位字母或数字";
            ToastUtils.show(this, prompt);
            return;
        }
        // 校验验证码
        SMSUtils.submitVerificationCode(phoneNumber, verCode);
    }

    /**
     * 提交验证码成功
     *
     * @param event
     * @param data
     */
    private void SMSResultComplete(int event, Object data) {
        switch (event) {
            case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE://提交验证码
                HashMap<String, Object> mData = (HashMap<String, Object>) data;
                // String country = (String) mData.get("country");//返回的国家编号
                String phone = (String) mData.get("phone");// 返回用户注册的手机号
                LogUtils.e("TAG", phone);
                mhandler.sendEmptyMessage(0);
                break;
            case SMSSDK.EVENT_GET_VERIFICATION_CODE:// 获取验证码成功
                Log.e("TAG", "获取验证码成功");
                break;
            case SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES:// 返回支持发送验证码的国家列表
                break;
        }
    }

    /**
     * 获取EditText中的数据
     */
    private void getEditTextContent() {
        phoneNumber = etPhoneNumber.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        verCode = etVerCode.getText().toString().trim();
    }

    /**
     * 获取验证码
     *
     * @param phoneNum
     */
    private void getVerCode(String phoneNum) {
        getEditTextContent();
        if (TextUtils.isEmpty(phoneNum)) {
            String prompt = "手机号不能为空";
            ToastUtils.show(this, prompt);
            return;
        }
        ToastUtils.show(this, phoneNum);
        SMSUtils.getVerificationCode(phoneNum);
    }

    @Override
    protected void onDestroy() {
        // ButterKnife注销
        if (unbinder != null) {
            bind(this).unbind();
        }
        // 注销回调接口
        if (eventHandler != null) {
            SMSSDK.unregisterEventHandler(eventHandler);
        }
        super.onDestroy();
    }
}
