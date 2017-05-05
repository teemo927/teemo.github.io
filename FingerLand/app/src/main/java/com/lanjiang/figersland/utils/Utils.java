package com.lanjiang.figersland.utils;

import android.app.Activity;
import android.content.Context;

import com.lanjiang.figersland.Constant;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.ui.BidActivity;
import com.lanjiang.figersland.ui.MineActivity;
import com.lanjiang.figersland.ui.PreDevelopmentActivity;
import com.lanjiang.figersland.ui.SiteControlActivity;

import java.io.File;

/**
 * Created by Asus on 2017/1/9.
 */

public class Utils {

    /**
     * 拍照，设置默认路径，文件名，返回码
     *
     * @param activity .
     */
    public static void takePhoto(Activity activity) {
        takePhoto(activity, Constant.PIC_DIR, Constant.FILE_NAME, Constant.CMD);
    }

    /**
     * 拍照，设置默认路径，文件名，返回码
     *
     * @param activity .
     */
    public static void takePhoto(Activity activity, String dir, String fileName, int cmd) {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        boolean b = CameraUtil.takePhoto(activity, dir, fileName, cmd);
        LogUtils.e("TAG", "--OK？--" + b);
    }

}
