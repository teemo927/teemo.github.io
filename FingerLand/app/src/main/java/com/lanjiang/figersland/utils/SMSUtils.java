package com.lanjiang.figersland.utils;

import android.content.Context;

import cn.smssdk.SMSSDK;

/**
 * Created by Lin on 2016/12/22.
 */

public class SMSUtils {

    // 短信验证的APPKEY
    private static String APPKEY = "19da3ab1fee88";
    // 短信验证的APPSECRET
    private static String APPSECRET = "942e7d7f7a1ae91ab99f79e33de4533d";
    // 短信验证的国家代码
    private static String COUNTRY_CODE = "86";

    /**
     * 初始化短信功能
     *
     * @param mcontext
     */
    public static void initSMS(Context mcontext) {
        SMSSDK.initSDK(mcontext, APPKEY, APPSECRET);
    }

    /**
     * 获取验证码
     *
     * @param phoneNumber
     */
    public static void getVerificationCode(String phoneNumber) {
        SMSSDK.getVerificationCode(COUNTRY_CODE, phoneNumber);
    }


    /**
     * 校验验证码
     *
     * @param phoneNumber
     * @param verCode
     */
    public static void submitVerificationCode(String phoneNumber, String verCode) {
        SMSSDK.submitVerificationCode(COUNTRY_CODE, phoneNumber, verCode);
    }

}


