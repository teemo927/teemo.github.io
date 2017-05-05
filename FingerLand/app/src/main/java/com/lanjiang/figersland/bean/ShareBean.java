package com.lanjiang.figersland.bean;

/**
 * 分享实体
 * Created by Asus on 2016/12/15.
 */


public class ShareBean {
    /**
     * 比如分享到QQ，其他平台则只需要更换平台类名，例如Wechat.NAME则是微信
     * 平台类型：如(Wechat.NAME , WechatMoments.NAME , WechatFavorite.NAME , QQ.NAME)
     */
    private String platform;

    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
    private String title;
    // titleUrl是标题的网络链接，QQ和QQ空间等使用
    private String titleUrl;
    // text是分享文本，所有平台都需要这个字段
    private String text;
    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
    private String imagePath;
    // url仅在微信（包括好友和朋友圈）中使用
    private String url;
    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
    private String comment;
    // site是分享此内容的网站名称，仅在QQ空间使用
    private String site;
    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
    private String siteUrl;
    private String imageUrl;

    public ShareBean(String platform, String title, String titleUrl, String text, String imagePath, String url, String comment, String site, String siteUrl) {
        this.platform = platform;
        this.title = title;
        this.titleUrl = titleUrl;
        this.text = text;
        this.imagePath = imagePath;
        this.url = url;
        this.comment = comment;
        this.site = site;
        this.siteUrl = siteUrl;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
