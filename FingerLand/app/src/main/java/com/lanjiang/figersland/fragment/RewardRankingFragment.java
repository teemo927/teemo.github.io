package com.lanjiang.figersland.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lanjiang.figersland.Constant;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.BannerAdapter;
import com.lanjiang.figersland.adapter.BannerLoopAdapter;
import com.lanjiang.figersland.adapter.MainPagerAdapter;
import com.lanjiang.figersland.adapter.RewardMenuAdapter;
import com.lanjiang.figersland.bean.BannerItem;
import com.lanjiang.figersland.bean.RewardType;
import com.lanjiang.figersland.ui.BidDetailActivity;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.CirclePageIndicator;
import com.lanjiang.figersland.widget.LineDecoration;
import com.lanjiang.figersland.widget.MyScrollView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handleMessage interaction events.
 * Use the {@link RewardRankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RewardRankingFragment extends BFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.scrollView)
    MyScrollView scrollView;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.dots_layout)
    LinearLayout mDotsLayout;
    @BindView(R.id.tv_publish_time)
    TextView tvPublishTime;
    @BindView(R.id.llt_publish_time)
    LinearLayout lltPublishTime;
    @BindView(R.id.tv_deadline)
    TextView tvDeadline;
    @BindView(R.id.llt_deadline)
    LinearLayout lltDeadline;
    @BindView(R.id.tv_reward_money)
    TextView tvRewardMoney;
    @BindView(R.id.llt_reward_money)
    LinearLayout lltRewardMoney;
    @BindView(R.id.tv_reward_type)
    TextView tvRewardType;
    @BindView(R.id.llt_reward_type)
    LinearLayout lltRewardType;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private int actionNow;//当前状态

    @OnClick({R.id.tv_publish_time, R.id.tv_deadline, R.id.tv_reward_money, R.id.tv_reward_type,
            R.id.llt_publish_time, R.id.llt_deadline, R.id.llt_reward_money, R.id.llt_reward_type})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.llt_publish_time:
            case R.id.tv_publish_time:
                switchState(tvPublishTime);
                break;
            case R.id.tv_deadline:
            case R.id.llt_deadline:
                switchState(tvDeadline);
                break;
            case R.id.tv_reward_money:
            case R.id.llt_reward_money:
                switchState(tvRewardMoney);
                break;
            case R.id.tv_reward_type:
            case R.id.llt_reward_type:
                switchState(tvRewardType);
                showTypeMenu(tvRewardType);
                break;
            default:
                break;
        }
    }

    private String mParam1;
    private String mParam2;

    private List<String> mAdapterDatas = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public RewardRankingFragment() {
    }

    public static RewardRankingFragment newInstance(String param1, String param2) {
        RewardRankingFragment fragment = new RewardRankingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reward_ranking, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBannerAdapter = new BannerLoopAdapter(mContext, initViewPagerDataSource(),viewpager,mDotsLayout);
        viewpager.setAdapter(mBannerAdapter);
        viewpager.setCurrentItem(1);

        //默认选中
        switchState(tvPublishTime);

        initView();
        initAdapter();
        initAdapterData();
    }

    private void initView() {
        scrollView.setOnScrollActionListener(new MyScrollView.OnScrollActionListener() {
            @Override
            public void onScroll(int action, int scrollY) {
//                LogUtils.e(TAG, "action:" + action + " ,scrollY" + scrollY);

                if (actionNow == action) {
                    return;
                }
                if (action == MyScrollView.ACTION_SCROLL_DOWN) {
                    mListener.onFragmentInteraction(false);
                } else if (action == MyScrollView.ACTION_SCROLL_UP) {
                    mListener.onFragmentInteraction(true);
                }
                actionNow = action;
            }
        });
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        CommonAdapter commonAdapter = new CommonAdapter<String>(mContext, R.layout.adapter_reward_ranking, mAdapterDatas) {
            @Override
            protected void convert(ViewHolder holder, String info, final int position) {
//                holder.setImageResource(R.id.iv_icon, info);
                holder.setText(R.id.tv_project_name, info);
                holder.setOnClickListener(R.id.iv_star, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setSelected(!v.isSelected());
                        ToastUtils.show(mContext, v.isSelected() ? "收藏" + position : "取消收藏" + position);
                    }
                });
                holder.setOnClickListener(R.id.adapter_reward_ranking, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, BidDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(Constant.ACTIVITY_TYPE, Constant.TYPE_REWARD);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.addItemDecoration(new LineDecoration(mContext, LinearLayoutManager.VERTICAL, LineDecoration.LEFT));
        recyclerView.setAdapter(commonAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        /**
         * 发布按钮状态
         *
         * @param show .
         */
        void onFragmentInteraction(boolean show);
    }

    /**
     * 初始化viewpager数据: 视图，图片资源，文字资源, 并设置点击事件
     */
    private List<BannerItem> initViewPagerDataSource() {
        List<BannerItem> mViewList = new ArrayList<>();

        int[] resIds = new int[]{R.drawable.banner1, R.drawable.banner2};
        for (int i = 0; i < resIds.length; i++) {

            mViewList.add(new BannerItem(resIds[i]));
        }

        return mViewList;

    }

    /**
     * 初始化recyclerview数据
     */
    private void initAdapterData() {
        int count = 10;
        String s = getString(R.string.newest_bid_text);
        for (int i = 0; i < count; i++) {
            mAdapterDatas.add(s+"6");
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    /**
     * 条件筛选
     */
    private void resetStatus() {
        tvRewardType.setSelected(false);
        tvRewardMoney.setSelected(false);
        tvDeadline.setSelected(false);
        tvPublishTime.setSelected(false);
    }

    /**
     * 设置当前按钮颜色 并 更换按钮状态
     *
     * @param view 被点击按钮
     */
    private void switchState(TextView view) {
        //重置筛选按钮
        Drawable unfocused = getResources().getDrawable(R.drawable.fabu_unselect_icon);
        unfocused.setBounds(0, 0, unfocused.getMinimumWidth(), unfocused.getMinimumHeight());
        tvPublishTime.setCompoundDrawables(null, null, unfocused, null);
        tvPublishTime.setTextColor(getResources().getColor(R.color.text_gray));
        tvDeadline.setCompoundDrawables(null, null, unfocused, null);
        tvDeadline.setTextColor(getResources().getColor(R.color.text_gray));
        tvRewardMoney.setCompoundDrawables(null, null, unfocused, null);
        tvRewardMoney.setTextColor(getResources().getColor(R.color.text_gray));

        //设置当前点击控件
        Drawable selector = getResources().getDrawable(R.drawable.selector_bid_text);
        selector.setBounds(0, 0, selector.getMinimumWidth(), selector.getMinimumHeight());
        if (!view.equals(tvRewardType)) {//类型不更改
            view.setCompoundDrawables(null, null, selector, null);
            view.setTextColor(getResources().getColor(R.color.color_main));
        }

        //改变所有状态
        tvPublishTime.setSelected(view.equals(tvPublishTime) ? !tvPublishTime.isSelected() : false);
        tvDeadline.setSelected(view.equals(tvDeadline) ? !tvDeadline.isSelected() : false);
        tvRewardMoney.setSelected(view.equals(tvRewardMoney) ? !tvRewardMoney.isSelected() : false);
        tvRewardType.setSelected(view.equals(tvRewardType));
//        LogUtils.e(TAG, " ,tvPublishTime.isSelected(): " + tvPublishTime.isSelected() + " ,tvDeadline.isSelected(): " + tvDeadline.isSelected());

        //根据状态展示功能
        switch (view.getId()) {
            case R.id.tv_publish_time://发布时间
                if (tvPublishTime.isSelected()) {
                    LogUtils.i(TAG, "tvPublishTime , 正排序" + view.getId());
                } else {
                    LogUtils.i(TAG, "tvPublishTime , 反排序" + view.getId());
                }
                break;
            case R.id.tv_deadline://截止时间
                if (tvDeadline.isSelected()) {
                    LogUtils.i(TAG, "tvDeadline , 正排序" + view.getId());
                } else {
                    LogUtils.i(TAG, "tvDeadline , 反排序" + view.getId());
                }
                break;
            case R.id.tv_reward_money://悬赏金额
                if (tvRewardMoney.isSelected()) {
                    LogUtils.i(TAG, "tvRewardMoney , 正排序" + view.getId());
                } else {
                    LogUtils.i(TAG, "tvRewardMoney , 反排序" + view.getId());
                }
                break;
            case R.id.tv_reward_type://悬赏类型
                if (tvRewardType.isSelected()) {
                    LogUtils.i(TAG, "tvRewardType , 正排序" + view.getId());
                } else {
                    LogUtils.i(TAG, "tvRewardType , 反排序" + view.getId());
                }
                break;
            default:
                break;
        }

    }

    @NonNull
    private List<RewardType> getMenuData() {
        List<RewardType> data = new ArrayList<>();
        List<String> childList = new ArrayList<>();
        childList.add("精装修设计");
        childList.add("施工图设计");
        childList.add("规划设计");
        childList.add("供电专业设计");
        childList.add("照明专业设计");
        childList.add("方案设计");
        for (int i = 0; i < 15; i++) {
            data.add(new RewardType("勘探设计、监理及咨询服务", childList));
        }
        return data;
    }

    /**
     * 显示悬赏类型菜单
     *
     * @param tv
     */
    private void showTypeMenu(final TextView tv) {
        List<RewardType> data = getMenuData();

        View root = mInflater.inflate(R.layout.poplayout_menu_reward, null);
        int width = getResources().getDimensionPixelSize(R.dimen.dp_310);
        final PopupWindow window = new PopupWindow(root, width, WindowManager.LayoutParams.MATCH_PARENT);

        ExpandableListView elv = (ExpandableListView) root.findViewById(R.id.elv);
        ExpandableListAdapter adapter = new RewardMenuAdapter(mContext, data, new RewardMenuAdapter.ItemClickListener() {
            @Override
            public void click(String type) {
                tv.setText(type);
                window.dismiss();
            }
        });
        elv.setAdapter(adapter);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.window_anim_style);
        window.setFocusable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setOutsideTouchable(true);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                closePopupWindow(window);
            }
        });

        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.7f;
        getActivity().getWindow().setAttributes(params);

        window.showAtLocation(root, Gravity.RIGHT, 0, 0);
    }

    /**
     * 显示悬赏类型菜单
     *
     * @param popupWindow
     */
    private void closePopupWindow(PopupWindow popupWindow) {
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 1f;
        getActivity().getWindow().setAttributes(params);
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }


}
