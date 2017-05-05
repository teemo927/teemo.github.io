package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.CommonAdapter;
import com.lanjiang.figersland.adapter.ViewHolder;
import com.lanjiang.figersland.bean.MyCollectionBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lin on 2017/2/20.
 */

public class MyCollectionActivity extends BaseToolbarActivity {
    @BindView(R.id.lv_my_collection)
    ListView lvMyCollection;

    List<MyCollectionBean> myCollectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        super.unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        setListViewData();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.my_collection));
    }

    /**
     * 模拟数据
     */
    private void getData() {
        myCollectionList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MyCollectionBean myCollectionBean = new MyCollectionBean(R.drawable.logo,
                    "兰江山第三期桩基检测工程", "勘探设计", "兰江集团", "8000", "￥");
            myCollectionList.add(myCollectionBean);
        }
        for (int i = 0; i < 5; i++) {
            MyCollectionBean myCollectionBean = new MyCollectionBean(R.drawable.logo,
                    "兰江山第二期桩基检测工程", "兰江集团");
            myCollectionList.add(myCollectionBean);
        }
    }

    /**
     * 设置listview
     */
    private void setListViewData() {
        // 模拟数据
        getData();
        CommonAdapter<MyCollectionBean> commonAdapter = new CommonAdapter<MyCollectionBean>(mContext,
                myCollectionList, R.layout.my_collection_item2) {
            @Override
            public void convert(ViewHolder helper, MyCollectionBean item) {
                helper.setImageResource(R.id.iv_logo, item.getLogo());
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_other1, item.getOther1());
                if (TextUtils.isEmpty(item.getOther2())) {
                    helper.setText(R.id.tv_other2, "");
                } else {
                    helper.setText(R.id.tv_other2, item.getOther2());
                }
                if (TextUtils.isEmpty(item.getOther3())) {
                    helper.setText(R.id.tv_other3, "");
                } else {
                    helper.setText(R.id.tv_other3, item.getOther3());
                }
                if (TextUtils.isEmpty(item.getCurrency())) {
                    helper.setText(R.id.tv_currency, "");
                } else {
                    helper.setText(R.id.tv_currency, item.getCurrency());
                }
            }
        };
        lvMyCollection.setAdapter(commonAdapter);
        commonAdapter.notifyDataSetChanged();
    }
}
