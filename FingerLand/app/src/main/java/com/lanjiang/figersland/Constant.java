package com.lanjiang.figersland;

import android.os.Environment;

/**
 * 常量
 * Created by Asus on 2017/1/9.
 */

public interface Constant {

    /**
     * 拍照相关参数
     * 路径/storage/emulated/0/fingers/
     */
    String PIC_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/fingers/";
    String FILE_NAME = System.currentTimeMillis() + ".png";
    int CMD = 0x1;


    /**
     * 界面间传递intent 参数
     */
    String ACTIVITY_EXTRA = "activity_extra";


    /**
     * 界面类型
     * TYPE_BID    招标信息
     * TYPE_REWARD 悬赏信息
     */
    String ACTIVITY_TYPE = "activity_type";
    int TYPE_BID = 0x2;
    int TYPE_REWARD = 0x3;
}
