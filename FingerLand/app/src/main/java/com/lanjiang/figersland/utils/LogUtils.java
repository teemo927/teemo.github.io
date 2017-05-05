package com.lanjiang.figersland.utils;

import android.util.Log;

import com.lanjiang.figersland.BuildConfig;


public class LogUtils {

    private static boolean debugOn = BuildConfig.DEBUG;


    public static void v(String tag,String s){
        if (!debugOn)
            return;
        Log.v(tag,s);
    }


    public static void i(String tag,String s){
        if (!debugOn)
            return;
        Log.i(tag,s);
    }

    public static void d(String tag,String s){
        if (!debugOn)
            return;
        Log.d(tag,s);
    }

    public static void w(String tag,String s){
        if (!debugOn)
            return;
        Log.w(tag,s);
    }

    public static void e(String tag,String s){
        if (!debugOn)
            return;
        Log.e(tag,s);
    }
}
