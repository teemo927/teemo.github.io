package com.lanjiang.figersland.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.bean.RewardType;
import com.lanjiang.figersland.widget.SpaceItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 悬赏类型菜单适配器
 * Created by Asus on 2016/12/14.
 */

public class RewardMenuAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<RewardType> data;

    public RewardMenuAdapter(Context context, List<RewardType> data,ItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    public List<RewardType> getData() {
        return data;
    }

    public void setData(List<RewardType> data) {
        this.data = data;
    }

    public void refreshData(List<RewardType> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        return data.get(groupPosition) != null ? data.get(groupPosition).getTypeList().size() : 0;
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition).getRewardGroup();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getTypeList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup holder;
        if (convertView == null) {
            holder = new ViewHolderGroup();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_menu_group, null);
            holder.title = (TextView) convertView.findViewById(R.id.tv_service);
            holder.indicator = (ImageView) convertView.findViewById(R.id.iv_indicator);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderGroup) convertView.getTag();
        }

        holder.title.setText((CharSequence) getGroup(groupPosition));

        if (isExpanded) {
            holder.title.setTextColor(context.getResources().getColor(R.color.white));
//            RotateAnimation rotateAnimation = new RotateAnimation(-180, 0, RELATIVE_TO_SELF, (float) 0.5, RELATIVE_TO_SELF, (float) 0.5);
//            rotateAnimation.setDuration(300);
//            rotateAnimation.setFillAfter(true);
//            rotateAnimation.setFillBefore(true);
//            holder.indicator.startAnimation(rotateAnimation);
            convertView.setBackgroundColor(context.getResources().getColor(R.color.group_select));
            convertView.setSelected(true);
        } else {
            holder.title.setTextColor(context.getResources().getColor(R.color.black));
//            RotateAnimation rotateAnimation = new RotateAnimation(0, -180, RELATIVE_TO_SELF, (float) 0.5, RELATIVE_TO_SELF, (float) 0.5);
//            rotateAnimation.setDuration(300);
//            rotateAnimation.setFillAfter(true);
//            rotateAnimation.setFillBefore(true);
//            holder.indicator.startAnimation(rotateAnimation);
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
            convertView.setSelected(false);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final RecyclerView recyclerView;
        int spaceCount = 3;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recycler_view_reward_menu, null);
            recyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_view);
            int space = (int) context.getResources().getDimension(R.dimen.dp_8);
            recyclerView.addItemDecoration(new SpaceItemDecoration(space, spaceCount));
            convertView.setTag(recyclerView);
        } else {
            recyclerView = (RecyclerView) convertView.getTag();
        }

        List list = data.get(groupPosition).getTypeList();
        recyclerView.setLayoutManager(new GridLayoutManager(context, spaceCount));
        CommonAdapter adapter = new CommonAdapter<String>(context, R.layout.adapter_menu, list) {
            @Override
            protected void convert(final ViewHolder holder, final String s, int position) {
                holder.setText(R.id.tv_name, s);
                holder.setOnClickListener(R.id.tv_name, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setSelected(!v.isSelected());
                        listener.click(s);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class ViewHolderGroup {
        TextView title;
        ImageView indicator;
    }

    public interface ItemClickListener{
        void click(String type);
    }

    ItemClickListener listener;

}
