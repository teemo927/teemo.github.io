package com.lanjiang.figersland.bean;

import com.lanjiang.figersland.R;

/**
 * Created by Lin on 2016/12/26.
 */

public class PayWayBean {
    private String payWayName;
    private int payWayIcon;
    private int payCheck;

    public PayWayBean(String payWayName, int payWayIcon) {
        this.payWayName = payWayName;
        this.payWayIcon = payWayIcon;
        this.payCheck = R.drawable.ssdk_oks_classic_check_default;
    }

    public String getPayWayName() {
        return payWayName;
    }

    public void setPayWayName(String payWayName) {
        this.payWayName = payWayName;
    }

    public int getPayWayIcon() {
        return payWayIcon;
    }

    public void setPayWayIcon(int payWayIcon) {
        this.payWayIcon = payWayIcon;
    }

    public int getPayCheck() {
        return payCheck;
    }

    public void setPayCheck(int payCheck) {
        this.payCheck = payCheck;
    }
}
