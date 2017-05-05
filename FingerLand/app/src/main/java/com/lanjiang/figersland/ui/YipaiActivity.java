package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.widget.FlowLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YipaiActivity extends BaseToolbarActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yipai);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.yipai);
        setRightTv(getString(R.string.start_in), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, PublishRecruitActivity.class));
            }
        });

    }

    private void initView() {
        final List<String> list = new ArrayList<>();
        list.add("深圳旧改项目招募成员");
        list.add("连云港兰江项目招募成员");
        list.add("深圳旧改项目招募成员");
        list.add("深圳旧改项目招募成员");
        CommonAdapter adapter = new CommonAdapter<String>(mContext, R.layout.adapter_yipai, list) {
            @Override
            protected void convert(final ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_name, s);
                holder.setText(R.id.tv_member_has, getString(R.string.member_has_num, 24 + ""));
                holder.setText(R.id.tv_member_off, getString(R.string.member_off_num, 12 + ""));
                holder.setOnClickListener(R.id.adapter_yipai, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, YipaiDetailActivity.class));
                    }
                });

                final FlowLayout lltMemberHas = holder.getView(R.id.llt_member_has);
                lltMemberHas.addView(createText("文案策划", true));
                lltMemberHas.addView(createText("项目经理", true));
                lltMemberHas.addView(createText("项目开发设计", true));
                lltMemberHas.addView(createText("项目经理", true));
                lltMemberHas.addView(createText("后勤保障 ", true));
                lltMemberHas.addView(createText("项目经理", true));
                lltMemberHas.addView(createText("后勤保障 ", true));
                lltMemberHas.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LogUtils.e(TAG, "lltMemberHas::" + lltMemberHas.getLineNum());
                        if (lltMemberHas.getLineNum() > 1) {
                            holder.setVisible(R.id.tv_more_has, true);
                        } else {
                            holder.setVisible(R.id.tv_more_has, false);
                        }
                    }
                });

                final FlowLayout lltMemberOff = holder.getView(R.id.llt_member_off);
                lltMemberOff.addView(createText("文案策划", false));
                lltMemberOff.addView(createText("我", false));
                lltMemberOff.addView(createText("项目经理", false));
                lltMemberOff.addView(createText("文案策划", false));
                lltMemberOff.addView(createText("我", false));
                lltMemberOff.addView(createText("项目经理", false));
                lltMemberOff.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        LogUtils.e(TAG, "lltMemberOff::" + lltMemberOff.getLineNum());
                        if (lltMemberOff.getLineNum() > 1) {
                            holder.setVisible(R.id.tv_more_off, true);
                        } else {
                            holder.setVisible(R.id.tv_more_off, false);
                        }
                    }
                });
            }
        };

//        LoadMoreWrapper wrapper = new LoadMoreWrapper(adapter);
//        wrapper.setLoadMoreView(R.layout.adapter_city);
//        wrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                ToastUtils.show(mContext, "load more");
//            }
//        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
    }

    private TextView createText(String s, boolean has) {
        int right = (int) getResources().getDimension(R.dimen.dp_8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, right, right);//4个参数按顺序分别是左上右下

        TextView childHas = new TextView(mContext);
        childHas.setLayoutParams(layoutParams);
        childHas.setPadding(right, 0, right, 0);
        if (has) {
            childHas.setBackgroundResource(R.drawable.shape_adapter_background_blue_light);
        } else {
            childHas.setBackgroundResource(R.drawable.shape_adapter_background_blue);
        }
        childHas.setText(s);
        childHas.setTextColor(getResources().getColor(R.color.white));
        return childHas;
    }

}
