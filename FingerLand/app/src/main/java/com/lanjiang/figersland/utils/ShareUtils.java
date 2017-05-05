package com.lanjiang.figersland.utils;

import android.content.Context;
import android.os.Environment;

import com.lanjiang.figersland.R;

import java.io.File;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 分享工具类
 * Created by Asus on 2016/12/15.
 */

public class ShareUtils {


    /**
     * @param platform 指定分享平台，和slient一起使用可以直接分享到指定的平台
     * @param silent   是否直接分享（true则直接分享）
     */
    public static void showOnekeyshare(Context context, String platform, boolean silent) {
        OnekeyShare oks = new OnekeyShare();

        //        // 分享时Notification的图标和文字
        //        oks.setNotification(R.mipmap.ic_launcher,
        //                getResources().getString(R.string.app_name));
        // address是接收人地址，仅在信息和邮件使用
        oks.setAddress("12345678901");
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("兰江集团并购房策网，跨界合作迎互联网时代");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.szlandcom.com/newsDetail.aspx?id=257");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/tencent/MicroMsg/WeiXin/a.jpg");
        if (file.exists()) {
            oks.setImagePath(Environment.getExternalStorageDirectory().getPath() + "/tencent/MicroMsg/WeiXin/a.jpg");
        }
        // imageUrl是图片的网络路径，新浪微博、人人网、QQ空间、
        // 微信的两个平台、Linked-In支持此字段
        oks.setImageUrl("http://pan.baidu.com/s/1dFgidXn");
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.lanjianggroup.com/phone/newsDetail.aspx?id=257");
        //        // appPath是待分享应用程序的本地路劲，仅在微信中使用
        //        oks.setAppPath(MainActivity.TEST_IMAGE);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(context.getString(R.string.share));
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.szlandcom.com/");
        // venueName是分享社区名称，仅在Foursquare使用
        oks.setVenueName("Southeast in China");
        // venueDescription是分享社区描述，仅在Foursquare使用
        oks.setVenueDescription("This is a beautiful place!");
        // latitude是维度数据，仅在新浪微博、腾讯微博和Foursquare使用
        oks.setLatitude(23.122619f);
        // longitude是经度数据，仅在新浪微博、腾讯微博和Foursquare使用
        oks.setLongitude(113.372338f);
        // 是否直接分享（true则直接分享）
        oks.setSilent(silent);
        // 指定分享平台，和slient一起使用可以直接分享到指定的平台
        if (platform != null && !platform.isEmpty()) {
            oks.setPlatform(platform);
        }
//        // 去除注释可通过OneKeyShareCallback来捕获快捷分享的处理结果
//        oks.setCallback(new OneKeyShareCallback());
//        //通过OneKeyShareCallback来修改不同平台分享的内容
//        oks.setShareContentCustomizeCallback(
//                new ShareContentCustomizeDemo());

        oks.show(context);
    }

}
