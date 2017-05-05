package com.lanjiang.figersland.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.DialogUtils;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.SpaceItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySettingActivity extends BaseToolbarActivity {

    private List<String> mList = new ArrayList<>();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @OnClick(R.id.rlt_add_card)
    public void click(View v){
        ToastUtils.show(mContext,"add card");
    }

    /**
     * @param savedInstanceState .
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_setting);
        unbinder = ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.pay_setting);
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new CommonAdapter<String>(mContext, R.layout.adapter_pay_setting, mList) {
            @Override
            protected void convert(final ViewHolder holder, String s, final int position) {
                holder.setText(R.id.tv_bank, s);
                holder.setOnClickListener(R.id.tv_default, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtils.showYesNo(mContext, getString(R.string.sure_set_default), new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                mList.set(holder.getAdapterPosition(),"默认卡");
                                recyclerView.getAdapter().notifyItemChanged(holder.getAdapterPosition());
                            }
                        });
                    }
                });
                holder.setOnLongClickListener(R.id.adapter_pay_setting, new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        DialogUtils.showYesNo(mContext, getString(R.string.sure_delete), new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        mList.remove(holder.getAdapterPosition());
                                        recyclerView.getAdapter().notifyItemRemoved(holder.getAdapterPosition());
                                    }
                                });
                        return true;
                    }
                });
                FloatingActionButton fab = holder.getView(R.id.iv_icon);
//                View view = holder.getView(R.id.adapter_pay_setting);
//                int radius = (int) getResources().getDimension(R.dimen.dp_4);
                switch (position) {
                    case 0:
                        holder.setText(R.id.tv_default, getString(R.string.is_default));
//                        StateListDrawable drawable1 = setSelector(radius, "#239F85", "#47499E");
//                        view.setBackgroundDrawable(drawable1);
//                        fab.setImageResource(R.drawable.nongyebanklogo);
                        break;
                    case 1:
//                        fab.setImageResource(R.drawable.chinabank);
                        break;
                    case 2:
                        fab.setImageResource(R.drawable.jiaotongbank);
                        break;
                }
            }
        });
        int space = (int) getResources().getDimension(R.dimen.dp_12);
        recyclerView.addItemDecoration(new SpaceItemDecoration(space, 1));
    }

    private void initData() {
        mList.add("农业银行");
        mList.add("中国银行");
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
        }

    }

    /**
     * 设置圆角背景选择器
     *
     * @param radius     背景圆角
     * @param normalArgb 一般状态颜色
     * @param pressArgb  选择状态颜色
     * @return 颜色
     */
    private StateListDrawable setSelector(int radius, String normalArgb, String pressArgb) {
        //shape
        GradientDrawable normal = new GradientDrawable();
        normal.setCornerRadius(radius);
        normal.setColor(Color.parseColor(normalArgb));

        GradientDrawable press = new GradientDrawable();
        press.setCornerRadius(radius);
        press.setColor(Color.parseColor(pressArgb));

        StateListDrawable drawable = new StateListDrawable();
        //Non focused states
        drawable.addState(new int[]{-android.R.attr.state_focused, -android.R.attr.state_selected, -android.R.attr.state_pressed},
                normal);
        drawable.addState(new int[]{-android.R.attr.state_focused, android.R.attr.state_selected, -android.R.attr.state_pressed},
                press);
        //Focused states
        drawable.addState(new int[]{android.R.attr.state_focused, -android.R.attr.state_selected, -android.R.attr.state_pressed},
                press);
        drawable.addState(new int[]{android.R.attr.state_focused, android.R.attr.state_selected, -android.R.attr.state_pressed},
                press);
        //Pressed
        drawable.addState(new int[]{android.R.attr.state_selected, android.R.attr.state_pressed},
                press);
        drawable.addState(new int[]{android.R.attr.state_pressed},
                press);
        return drawable;
    }

}
