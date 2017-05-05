package com.lanjiang.figersland.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2016/12/12.
 */

public class PackageUtils {

    /**
     * 获取当前安装的应用所有包名
     *
     * @param context
     * @return
     */
    public static List<String> getAllPackageNames(Context context) {
        List<String> list = new ArrayList<>();
        PackageManager pageManage = context.getPackageManager();
        List<PackageInfo> packages = pageManage.getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            String appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            String pagName = packageInfo.packageName;
            LogUtils.e("TAG", appName + "--" + pagName);
            list.add(pagName);
        }
        return list;
    }
}
