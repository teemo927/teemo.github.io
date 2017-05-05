package com.lanjiang.figersland.bean;

import java.util.List;

/**
 * Created by Lin on 2017/2/21.
 */

public class PositionBean {
    private String sortName;
    private boolean seleted;
    private List<PositionInfoBean> positionInfoBeanList;

    public PositionBean(String sortName, List<PositionInfoBean> positionInfoBeanList) {
        this.sortName = sortName;
        this.positionInfoBeanList = positionInfoBeanList;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public boolean isSeleted() {
        return seleted;
    }

    public void setSeleted(boolean seleted) {
        this.seleted = seleted;
    }

    public List<PositionInfoBean> getPositionInfoBeanList() {
        return positionInfoBeanList;
    }

    public void setPositionInfoBeanList(List<PositionInfoBean> positionInfoBeanList) {
        this.positionInfoBeanList = positionInfoBeanList;
    }
}
