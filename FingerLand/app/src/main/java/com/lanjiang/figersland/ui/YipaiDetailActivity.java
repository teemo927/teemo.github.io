package com.lanjiang.figersland.ui;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.DialogUtils;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.FlowLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YipaiDetailActivity extends BaseToolbarActivity {

    public static int DEFAULT_NUM = 6;

    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.llt_share)
    LinearLayout lltShare;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.llt_call)
    LinearLayout lltCall;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.llt_bottom)
    LinearLayout lltBottom;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.recycler_view_has)
    RecyclerView recyclerViewHas;
    @BindView(R.id.tv_more_has)
    TextView tvMoreHas;
    @BindView(R.id.recycler_view_off)
    RecyclerView recyclerViewOff;
    @BindView(R.id.tv_more_off)
    TextView tvMoreOff;
    @BindView(R.id.activity_yipai_detail)
    RelativeLayout activityYipaiDetail;

    private List<String> mListHas;
    private List<String> listHasShow = new ArrayList<>();
    private List<String> mListOff;
    private List<String> listOffShow = new ArrayList<>();
    private boolean memberHasOpen;
    private boolean memberOffOpen;
    //
    private int mSelectPos = -1;

    @OnClick({R.id.llt_share, R.id.llt_call, R.id.tv_sign_up, R.id.tv_more_has, R.id.tv_more_off})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.llt_share:
                share(null, false);
                break;
            case R.id.llt_call:
                talk();
                break;
            case R.id.tv_sign_up:
                handle();
                break;
            case R.id.tv_more_has:
                memberHasOpen = !memberHasOpen;
                layoutMember(memberHasOpen, mListHas, listHasShow, tvMoreHas, recyclerViewHas);
                break;
            case R.id.tv_more_off:
                memberOffOpen = !memberOffOpen;
                layoutMember(memberOffOpen, mListOff, listOffShow, tvMoreOff, recyclerViewOff);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yipai_detail);
        unbinder = ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        tvEndTime.setText(getString(R.string.project_time, "2017-06-18"));

        mListHas = new ArrayList<>();
        int count = 24 / 3;
        for (int i = 0; i < count; i++) {
            mListHas.add("项目经理项目");
            mListHas.add("项目开发设计");
            mListHas.add("施工方");
        }

        mListOff = new ArrayList<>();
        int countOff = 12 / 3;
        for (int i = 0; i < countOff; i++) {
            mListOff.add("项目开发设计");
            mListOff.add("文案策划");
            mListOff.add("工程施工部门");
        }
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.recruit_detail);
    }

    private void initView() {
        recyclerViewHas.setLayoutManager(new GridLayoutManager(mContext, 1));
        recyclerViewHas.setAdapter(new CommonAdapter<String>(mContext, R.layout.adapter_member, listHasShow) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_name, s);
            }
        });

        layoutMember(memberHasOpen, mListHas, listHasShow, tvMoreHas, recyclerViewHas);


        recyclerViewOff.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerViewOff.setAdapter(new CommonAdapter<String>(mContext, R.layout.adapter_member_off, listOffShow) {
            @Override
            protected void convert(final ViewHolder holder, String s, final int position) {
                if (mSelectPos == position) {
                    holder.setChecked(R.id.cb_icon, true);
                    holder.getView(R.id.tv_name).setSelected(true);
                } else {
                    holder.setChecked(R.id.cb_icon, false);
                    holder.getView(R.id.tv_name).setSelected(false);
                }
                holder.setText(R.id.tv_name, s);

                final CheckBox checkBox = holder.getView(R.id.cb_icon);
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSelectPos = checkBox.isChecked() ? position : -1;
                        notifyDataSetChanged();
                    }
                });
                holder.setOnClickListener(R.id.adapter_member_off, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSelectPos = mSelectPos != position ? position : -1;
                        notifyDataSetChanged();
                    }
                });
            }
        });

        layoutMember(memberOffOpen, mListOff, listOffShow, tvMoreOff, recyclerViewOff);
    }

    /**
     * recyclerView 展开更多和折叠
     *
     * @param open       是否展开全部，是true,否false
     * @param originList 原始数据
     * @param showList   展示的数据
     * @param moreView   更多
     */
    private void layoutMember(boolean open, List originList, List showList, TextView moreView, RecyclerView recyclerView) {
        showList.clear();

        if (originList.size() <= DEFAULT_NUM) {
            moreView.setVisibility(View.GONE);
            showList.addAll(originList);
        } else {
            moreView.setVisibility(View.VISIBLE);
            if (open) {
                showList.addAll(originList);
                moreView.setText(getString(R.string.unfold));
                recyclerView.getAdapter().notifyItemRangeInserted(DEFAULT_NUM, mListHas.size());
            } else {
                showList.addAll(originList.subList(0, DEFAULT_NUM));
                moreView.setText(getString(R.string.more));
                recyclerView.getAdapter().notifyItemRangeRemoved(DEFAULT_NUM, mListHas.size());
            }
        }

    }

    /**
     * 我要参与
     */
    private void handle() {
        if (mSelectPos != -1) {
            showYesNo();
            return;
        }
        mSelectPos = -1;

        View root = mInflater.inflate(R.layout.popwindow_join, null);
        final PopupWindow joinWindow = new PopupWindow(root, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final FlowLayout flowLayout = (FlowLayout) root.findViewById(R.id.flowlayout);
        Button sure = (Button) root.findViewById(R.id.btn_sure);
        View close = root.findViewById(R.id.iv_close);

        final List<String> mVals = mListOff;

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int gap = (int) getResources().getDimension(R.dimen.dp_8);
        lp.setMargins(0, 0, gap, gap);

        for (int i = 0; i < mVals.size(); i++) {

            TextView textView = (TextView) mInflater.inflate(R.layout.adapter_tag, null);
            textView.setLayoutParams(lp);

            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //还原上一个状态
                    if (mSelectPos != -1) {
                        TextView child = (TextView) flowLayout.getChildAt(mSelectPos);
                        child.setBackgroundResource(R.drawable.shape_adapter_background_root);
                        child.setTextColor(getResources().getColor(R.color.word_normal));
                        child.setSelected(false);
                    }
                    if (mSelectPos == finalI) {
                        //设置当前状态
                        v.setBackgroundResource(R.drawable.shape_adapter_background_root);
                        ((TextView) v).setTextColor(getResources().getColor(R.color.word_normal));
                        v.setSelected(false);
                        mSelectPos = -1;
                    } else {
                        //设置当前状态
                        v.setBackgroundResource(R.drawable.shape_adapter_background_blue);
                        ((TextView) v).setTextColor(getResources().getColor(R.color.white));
                        v.setSelected(true);
                        mSelectPos = finalI;
                    }
                }
            });
            textView.setText(mVals.get(i));
            flowLayout.addView(textView);
        }

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectPos == -1) {
                    ToastUtils.show(mContext, "请至少选一个职位");
                    LogUtils.e(TAG, "请至少选一个席位");
                } else {
                    showYesNo();
//                    ToastUtils.show(mContext, "当前选择的是：" + mSelectPos + mVals.get(mSelectPos));
                    joinWindow.dismiss();
                }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinWindow.dismiss();
            }
        });

        // 设置popWindow的显示和消失动画
        joinWindow.setAnimationStyle(R.style.window_anim_bottom_to_top);
        joinWindow.setFocusable(true);
        joinWindow.setBackgroundDrawable(new BitmapDrawable());
        joinWindow.setOutsideTouchable(true);
        joinWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = YipaiDetailActivity.this.getWindow().getAttributes();
                params.alpha = 1f;
                YipaiDetailActivity.this.getWindow().setAttributes(params);
            }
        });
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 0.5f;
        this.getWindow().setAttributes(params);
        joinWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        joinWindow.showAtLocation(activityYipaiDetail, Gravity.BOTTOM, 0, 0);
    }

    private void showYesNo() {
        String msg = getString(R.string.ensure_enter)+ tvName.getText().toString() + "-" + mListOff.get(mSelectPos);
        DialogUtils.showYesNo(mContext, msg);
        LogUtils.e(TAG, msg);
    }


    @Override
    protected void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 1.0f;
        this.getWindow().setAttributes(params);
    }

}
