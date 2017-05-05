package com.lanjiang.figersland.delegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lanjiang.figersland.Constant;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.ui.BidDetailActivity;
import com.lanjiang.figersland.ui.MasterDetailActivity;
import com.lanjiang.figersland.ui.PapersDetailActivity;
import com.lanjiang.figersland.ui.YipaiDetailActivity;
import com.lanjiang.figersland.utils.ToastUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * 话题内容布局
 * Created by Asus on 2017/2/20.
 */
public class TopicItemDelegate implements ItemViewDelegate<String> {
    private Context mContext;

    public TopicItemDelegate(Context mContext) {

        this.mContext = mContext;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.adapter_topic_detail;
    }

    @Override
    public boolean isForViewType(String item, int position) {
        return position !=0;
    }

    @Override
    public void convert(ViewHolder holder, final String s, final int position) {
        holder.setText(R.id.tv_project_name, s);
        holder.setOnClickListener(R.id.iv_star, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                ToastUtils.show(mContext, v.isSelected() ? "收藏" + position : "取消收藏" + position);
            }
        });
        holder.setOnClickListener(R.id.adapter_topic_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BidDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.ACTIVITY_TYPE, Constant.TYPE_BID);
                intent.putExtras(bundle);

                mContext.startActivity(intent);
            }
        });
    }
}
