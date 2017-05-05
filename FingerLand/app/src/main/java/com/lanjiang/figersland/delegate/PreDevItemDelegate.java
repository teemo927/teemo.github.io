package com.lanjiang.figersland.delegate;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.ui.MasterDetailActivity;
import com.lanjiang.figersland.ui.PapersDetailActivity;
import com.lanjiang.figersland.ui.YipaiDetailActivity;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * 前期开发内容布局
 * Created by Asus on 2017/2/20.
 */
public class PreDevItemDelegate implements ItemViewDelegate<String> {
    private Context mContext;

    public PreDevItemDelegate(Context mContext) {

        this.mContext = mContext;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.adapter_pre_devel;
    }

    @Override
    public boolean isForViewType(String item, int position) {
        return position !=0;
    }

    @Override
    public void convert(ViewHolder holder, final String info, int position) {
        switch (info) {
            case "red":
                holder.setBackgroundRes(R.id.tv_type, R.drawable.shape_mark_red);
                holder.setTextColorRes(R.id.tv_type, R.color.money);
                holder.setText(R.id.tv_type, mContext.getString(R.string.zhengjian));
                holder.setText(R.id.tv_name, "土地使用权证求办");
                holder.setText(R.id.tv_need, "￥8000.00");
                break;
            case "yellow":
                holder.setBackgroundRes(R.id.tv_type, R.drawable.shape_mark_yellow);
                holder.setTextColorRes(R.id.tv_type, R.color.yellow);
                holder.setText(R.id.tv_type, mContext.getString(R.string.gaoren));
                holder.setText(R.id.tv_name, "可以帮忙办理土地使用权证-5天");
                holder.setText(R.id.tv_need, "价格待议");
                break;
            case "yellow_light":
                holder.setBackgroundRes(R.id.tv_type, R.drawable.shape_mark_yellow_light);
                holder.setTextColorRes(R.id.tv_type, R.color.yellow_light);
                holder.setText(R.id.tv_type, mContext.getString(R.string.yipai));
                holder.setText(R.id.tv_name, "工程项目招募");
                holder.setText(R.id.tv_need, "招募5人");
                break;
        }
        holder.setOnClickListener(R.id.adapter_pre_devel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (info) {
                    case "red":
                        mContext.startActivity(new Intent(mContext, PapersDetailActivity.class));
                        break;
                    case "yellow":
                        mContext.startActivity(new Intent(mContext, MasterDetailActivity.class));
                        break;
                    case "yellow_light":
                        mContext.startActivity(new Intent(mContext, YipaiDetailActivity.class));
                        break;
                }
            }
        });
    }
}
