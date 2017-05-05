package com.lanjiang.figersland.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lin on 2016/12/8.
 */

public class MatchUtils {
    /**
     * 验证是否是手机号?
     *
     * @param phoneNum 手机号码
     * @return
     */
    public static boolean isPhoneNumber(String phoneNum) {
        if (!TextUtils.isEmpty(phoneNum)) {
            // 验证手机的正则表达式
            String str = "^[1][3,4,5,7,8][0-9]{9}$";
            Pattern pattern = Pattern.compile(str);
            Matcher matcher = pattern.matcher(phoneNum);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * 判断是否是6-16位的数字或字母
     *
     * @param passward
     * @return
     */
    public static boolean isPassword(String passward) {
        if (!TextUtils.isEmpty(passward)) {
            String str = "^[0-9a-zA-Z]{6,16}$";
            Pattern pattern = Pattern.compile(str);
            Matcher matcher = pattern.matcher(passward);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * 验证是否是合法用户名
     *
     * @param userName :字母+数字
     * @return
     */
    public static boolean isUserName(String userName) {
        if (!TextUtils.isEmpty(userName)) {
            String str = "(^[A-Za-z0-9]{2,16}$)|(^[\u4E00-\u9FA5]{2,8}$)";
            Pattern pattern = Pattern.compile(str);
            Matcher matcher = pattern.matcher(userName);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * 判断是否是邮件地址
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (!TextUtils.isEmpty(email)) {
            String check = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            Pattern pattern = Pattern.compile(check);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        } else {
            return false;
        }
    }
}
