package com.lanjiang.figersland.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.bean.MineMenuBean;

import java.util.List;

/**
 * Created by Lin on 2016/12/27.
 */

public class MineMenuAdapter extends BaseAdapter {
    private Context context;
    private List<MineMenuBean> data;

    public MineMenuAdapter(Context context, List<MineMenuBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 2 || position == 3) {
            return R.layout.mine_menu_item2;
        } else {
            return R.layout.mine_menu_item;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(getItemViewType(position), parent, false);

            holder = new ViewHolder();
            holder.ivMenuIcon = (ImageView) convertView.findViewById(R.id.iv_menu_icon);
            holder.tvMenu = (TextView) convertView.findViewById(R.id.tv_menu);
            holder.tvPrompt = (TextView) convertView.findViewById(R.id.tv_prompt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MineMenuBean mineMenuBean = data.get(position);
        holder.ivMenuIcon.setImageResource(mineMenuBean.getIcon());
        holder.tvMenu.setText(mineMenuBean.getName());
        holder.tvPrompt.setText(mineMenuBean.getPrompt());
        return convertView;
    }

    private static class ViewHolder {
        ImageView ivMenuIcon;
        TextView tvMenu;
        TextView tvPrompt;
    }

}
