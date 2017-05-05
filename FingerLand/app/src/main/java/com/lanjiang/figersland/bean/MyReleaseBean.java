package com.lanjiang.figersland.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lin on 2016/12/28.
 */

public class MyReleaseBean implements Parcelable{
    private int icon;
    private String title;
    private String releaseDate;
    private String endDate;
    private String other1;
    private String other2;
    private String other3;
    private String other4;
    private String draft;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.icon);
        dest.writeString(this.title);
        dest.writeString(this.releaseDate);
        dest.writeString(this.endDate);
        dest.writeString(this.other1);
        dest.writeString(this.other2);
        dest.writeString(this.other3);
        dest.writeString(this.other4);
        dest.writeString(this.draft);
    }

    public MyReleaseBean() {
    }

    protected MyReleaseBean(Parcel in) {
        this.icon = in.readInt();
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.endDate = in.readString();
        this.other1 = in.readString();
        this.other2 = in.readString();
        this.other3 = in.readString();
        this.other4 = in.readString();
        this.draft = in.readString();
    }

    public static final Creator<MyReleaseBean> CREATOR = new Creator<MyReleaseBean>() {
        @Override
        public MyReleaseBean createFromParcel(Parcel source) {
            return new MyReleaseBean(source);
        }

        @Override
        public MyReleaseBean[] newArray(int size) {
            return new MyReleaseBean[size];
        }
    };

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }

    public String getOther4() {
        return other4;
    }

    public void setOther4(String other4) {
        this.other4 = other4;
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }
}
