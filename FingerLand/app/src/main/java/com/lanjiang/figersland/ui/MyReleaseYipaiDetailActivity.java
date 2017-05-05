package com.lanjiang.figersland.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.HeaderAndContentAdapter;
import com.lanjiang.figersland.adapter.PositionAdapter;
import com.lanjiang.figersland.bean.PositionBean;
import com.lanjiang.figersland.bean.PositionInfoBean;
import com.lanjiang.figersland.utils.WidgetUtils;
import com.lanjiang.figersland.widget.RecyclerViewDivider;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lin on 2016/12/13.
 */

public class MyReleaseYipaiDetailActivity extends BaseToolbarActivity implements PositionAdapter.OnItemClickListener {

    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_release_date_content)
    TextView tvReleaseDateContent;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_end_date_content)
    TextView tvEndDateContent;
    @BindView(R.id.iv_click_recruit_introduce)
    ImageView ivClickRecruitIntroduce;
    @BindView(R.id.tv_recruit_introduce_content)
    TextView tvRecruitIntroduceContent;
    @BindView(R.id.iv_click_member_has)
    ImageView ivClickMemberHas;
    @BindView(R.id.tv_member_has_content)
    TextView tvMemberHasContent;
    @BindView(R.id.iv_click_recruit_declaration)
    ImageView ivClickRecruitDeclaration;
    @BindView(R.id.tv_recruit_declaration_content)
    TextView tvRecruitDeclarationContent;
    @BindView(R.id.recyclerview_position)
    RecyclerView recyclerViewPosition;
    @BindView(R.id.recyclerview_position_info)
    RecyclerView recyclerViewPositionInfo;

    private List<PositionBean> positionList;
    private PositionAdapter positionAdapter;
    private HeaderAndContentAdapter headerAndContentAdapter;
    private int oldSelectedPosition = 0;
    private LinearLayoutManager mPositionInfoLayoutManager;
    private LinearLayoutManager mPositionLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release_yipai_detail);
        super.unbinder = ButterKnife.bind(this);
        initData();
        initViews();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.yipai_details));
    }

    @OnClick({R.id.tv_edit, R.id.iv_click_recruit_introduce, R.id.iv_click_member_has, R.id.iv_click_recruit_declaration})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                startActivity(new Intent(mContext, YipaiActivity.class));
                break;
            case R.id.iv_click_recruit_introduce:
                WidgetUtils.changeVisibility(tvRecruitIntroduceContent);
                break;
            case R.id.iv_click_member_has:
                WidgetUtils.changeVisibility(tvMemberHasContent);
                break;
            case R.id.iv_click_recruit_declaration:
                WidgetUtils.changeVisibility(tvRecruitDeclarationContent);
                break;
        }
    }

    /**
     * 模拟数据
     */
    private void initData() {
        positionList = new ArrayList<>();
        List<PositionInfoBean> PositionInfoBeanList1 = new ArrayList<>();
        PositionInfoBeanList1.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList1.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList1.add(new PositionInfoBean("杨顺"));
        PositionInfoBeanList1.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList1.add(new PositionInfoBean("宋小亮"));
        PositionBean c1 = new PositionBean("项目经理", PositionInfoBeanList1);
        c1.setSeleted(true);

        List<PositionInfoBean> PositionInfoBeanList2 = new ArrayList<>();
        PositionInfoBeanList2.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList2.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList2.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList2.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList2.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList2.add(new PositionInfoBean("汤迪"));
        PositionBean c2 = new PositionBean("产品经理", PositionInfoBeanList2);

        List<PositionInfoBean> PositionInfoBeanList3 = new ArrayList<>();
        PositionInfoBeanList3.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList3.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList3.add(new PositionInfoBean("杨顺"));
        PositionInfoBeanList3.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList3.add(new PositionInfoBean("陈丽宝"));
        PositionInfoBeanList3.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList3.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList3.add(new PositionInfoBean("杨顺"));
        PositionInfoBeanList3.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList3.add(new PositionInfoBean("陈丽宝"));
        PositionBean c3 = new PositionBean("采购经理", PositionInfoBeanList3);

        List<PositionInfoBean> PositionInfoBeanList4 = new ArrayList<>();
        PositionInfoBeanList4.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList4.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList4.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList4.add(new PositionInfoBean("陈丽宝"));
        PositionInfoBeanList4.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList4.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList4.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList4.add(new PositionInfoBean("陈丽宝"));
        PositionBean c4 = new PositionBean("文案策划", PositionInfoBeanList4);
        positionList = new ArrayList<>();
        List<PositionInfoBean> PositionInfoBeanList5 = new ArrayList<>();
        PositionInfoBeanList5.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList5.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList5.add(new PositionInfoBean("杨顺"));
        PositionInfoBeanList5.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList5.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList5.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList5.add(new PositionInfoBean("杨顺"));
        PositionInfoBeanList5.add(new PositionInfoBean("汤迪"));
        PositionBean c5 = new PositionBean("项目经理", PositionInfoBeanList5);


        List<PositionInfoBean> PositionInfoBeanList6 = new ArrayList<>();
        PositionInfoBeanList6.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList6.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList6.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList6.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList6.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList6.add(new PositionInfoBean("汤迪"));
        PositionBean c6 = new PositionBean("产品经理", PositionInfoBeanList6);

        List<PositionInfoBean> PositionInfoBeanList7 = new ArrayList<>();
        PositionInfoBeanList7.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList7.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList7.add(new PositionInfoBean("杨顺"));
        PositionInfoBeanList7.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList7.add(new PositionInfoBean("陈丽宝"));
        PositionInfoBeanList7.add(new PositionInfoBean("宋小亮"));
        PositionInfoBeanList7.add(new PositionInfoBean("张文琴"));
        PositionInfoBeanList7.add(new PositionInfoBean("杨顺"));
        PositionInfoBeanList7.add(new PositionInfoBean("汤迪"));
        PositionInfoBeanList7.add(new PositionInfoBean("陈丽宝"));
        PositionBean c7 = new PositionBean("采购经理", PositionInfoBeanList7);

        positionList.add(c1);
        positionList.add(c2);
        positionList.add(c3);
        positionList.add(c4);
        positionList.add(c5);
        positionList.add(c6);
        positionList.add(c7);

    }

    private void initViews() {
        mPositionInfoLayoutManager = new LinearLayoutManager(this);
        mPositionLayoutManager = new LinearLayoutManager(this);
        recyclerViewPosition.setLayoutManager(mPositionLayoutManager);
        recyclerViewPositionInfo.setLayoutManager(mPositionInfoLayoutManager);
        recyclerViewPosition.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.VERTICAL));
        recyclerViewPositionInfo.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.VERTICAL));
        positionAdapter = new PositionAdapter(this, positionList);
        positionAdapter.setOnItemClickListener(this);
        recyclerViewPosition.setAdapter(positionAdapter);
        headerAndContentAdapter = new HeaderAndContentAdapter(this, positionList);
        recyclerViewPositionInfo.setAdapter(headerAndContentAdapter);

        // Add the sticky headers decoration,给职位添加标题
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(headerAndContentAdapter);
        recyclerViewPositionInfo.addItemDecoration(headersDecor);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerViewPositionInfo.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    //第一个完全显示的item和最后一个item。
                    int firstVisibleItem = mPositionInfoLayoutManager.findFirstCompletelyVisibleItemPosition();
                    int lastVisibleItem = mPositionInfoLayoutManager.findLastVisibleItemPosition();
                    //此判断，避免左侧点击最后一个item无响应
                    if (lastVisibleItem != mPositionInfoLayoutManager.getItemCount() - 1) {
                        int sort = headerAndContentAdapter.getSortType(firstVisibleItem);
                        changeSelected(sort);
                    } else {
                        changeSelected(positionAdapter.getItemCount() - 1);
                    }
                    if (needMove) {
                        needMove = false;
                        //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                        int n = movePosition - mPositionInfoLayoutManager.findFirstVisibleItemPosition();
                        if (0 <= n && n < recyclerViewPositionInfo.getChildCount()) {
                            //获取要置顶的项顶部离RecyclerView顶部的距离
                            int top = recyclerViewPositionInfo.getChildAt(n).getTop() - dip2px(MyReleaseYipaiDetailActivity.this, 24);
                            //最后的移动
                            recyclerViewPositionInfo.scrollBy(0, top);
                        }
                    }
                }
            });
        } else {
            recyclerViewPositionInfo.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    //第一个完全显示的item和最后一个item。
                    int firstVisibleItem = mPositionInfoLayoutManager.findFirstCompletelyVisibleItemPosition();
                    int lastVisibleItem = mPositionInfoLayoutManager.findLastVisibleItemPosition();
                    //此判断，避免左侧点击最后一个item无响应
                    if (lastVisibleItem != mPositionInfoLayoutManager.getItemCount() - 1) {
                        int sort = headerAndContentAdapter.getSortType(firstVisibleItem);
                        changeSelected(sort);
                    } else {
                        changeSelected(positionAdapter.getItemCount() - 1);
                    }
                    if (needMove) {
                        needMove = false;
                        //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                        int n = movePosition - mPositionInfoLayoutManager.findFirstVisibleItemPosition();
                        if (0 <= n && n < recyclerViewPositionInfo.getChildCount()) {
                            //获取要置顶的项顶部离RecyclerView顶部的距离
                            int top = recyclerViewPositionInfo.getChildAt(n).getTop() - dip2px(MyReleaseYipaiDetailActivity.this, 24);
                            //最后的移动
                            recyclerViewPositionInfo.scrollBy(0, top);
                        }
                    }
                }
            });
        }
    }


    private boolean needMove = false;
    private int movePosition;

    private void moveToPosition(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = mPositionInfoLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mPositionInfoLayoutManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            recyclerViewPositionInfo.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = recyclerViewPositionInfo.getChildAt(n - firstItem).getTop();
            recyclerViewPositionInfo.scrollBy(0, top - dip2px(this, 28));
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            recyclerViewPositionInfo.scrollToPosition(n);
            movePosition = n;
            needMove = true;
        }
    }

    @Override
    public void onItemClick(int position) {
        changeSelected(position);
        moveToThisSortFirstItem(position);

    }

    private void moveToThisSortFirstItem(int position) {
        movePosition = 0;
        for (int i = 0; i < position; i++) {
            movePosition += headerAndContentAdapter.getPositionList().get(i).getPositionInfoBeanList().size();
        }
        moveToPosition(movePosition);
    }

    private void changeSelected(int position) {
        if (position < 0) {
            return;
        }
        positionList.get(oldSelectedPosition).setSeleted(false);
        positionList.get(position).setSeleted(true);
        //增加左侧联动
        recyclerViewPosition.scrollToPosition(position);
        oldSelectedPosition = position;
        positionAdapter.notifyDataSetChanged();
    }

    /**
     * 根据手机分辨率从dp转成px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
