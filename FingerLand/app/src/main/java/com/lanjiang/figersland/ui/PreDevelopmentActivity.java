package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.bean.BannerItem;
import com.lanjiang.figersland.delegate.PreDevHeadItemDelegate;
import com.lanjiang.figersland.delegate.PreDevItemDelegate;
import com.lanjiang.figersland.utils.PopUtils;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.LineDecoration;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreDevelopmentActivity extends BaseToolbarActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_development);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.pre_develop);
        setLeftTv(getString(R.string.default_city), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext,"left-----");
                showCityWindow();
            }
        });
        setRightMenu();
    }

    private void initView() {
        initAdapter();
        initAdapterData();
    }


    private void initAdapter() {
        //多类型布局
        MultiItemTypeAdapter multiItemTypeAdapter = new MultiItemTypeAdapter(mContext, mData);
        //头布局
        final PreDevHeadItemDelegate preDevHeadItemDelegate = new PreDevHeadItemDelegate(mContext, initViewPagerDataSource());
//        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mBannerAdapter = preDevHeadItemDelegate.getAdapter();
//            }
//        });
        multiItemTypeAdapter.addItemViewDelegate(preDevHeadItemDelegate);
        //内容布局
        multiItemTypeAdapter.addItemViewDelegate(new PreDevItemDelegate(mContext));

        //这句就是添加我们自定义的分隔线
        recyclerView.addItemDecoration(new LineDecoration(this, LineDecoration.VERTICAL_LIST, LineDecoration.LEFT, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(multiItemTypeAdapter);
    }

    /**
     * 初始化recyclerview数据
     */
    private void initAdapterData() {

        mData.add("0");//首为空
        mData.add("red");
        mData.add("yellow");
        mData.add("yellow_light");

        mData.add("red");
        mData.add("yellow");
        mData.add("yellow_light");

        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void showCityWindow() {
        PopUtils.showCityWindow(mInflater, mContext, new PopUtils.PopClickListener() {
            @Override
            public void click(String city) {
                setLeftTv(city);
            }
        });
    }

    /**
     * 初始化viewpager数据: 视图，图片资源，文字资源, 并设置点击事件
     */
    private List<BannerItem> initViewPagerDataSource() {
        List<BannerItem> mViewList = new ArrayList<>();

        int[] resIds = new int[]{R.drawable.banner1, R.drawable.banner2, R.drawable.sceen_image};
        for (int i = 0; i < resIds.length; i++) {

            mViewList.add(new BannerItem(resIds[i]));
        }

        return mViewList;

    }

}
