package com.lanjiang.figersland.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.bean.AllTopic;
import com.lanjiang.figersland.bean.TopicGroup2;

import java.util.List;

/**
 * 三级菜单 第二层
 * Created by Asus on 2016/12/5.
 */

public class InnerExpandableListViewAdapter extends BaseExpandableListAdapter {


    private Context context;
    private int parent;
    private List<TopicGroup2> data;
    private List<Integer> showTopicList;

    public InnerExpandableListViewAdapter(Context context, int parent, List<TopicGroup2> data, List<Integer> showTopicList) {
        this.context = context;
        this.parent = parent;
        this.data = data;
        this.showTopicList = showTopicList;
    }

    public void setData(List<TopicGroup2> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<TopicGroup2> getData() {
        return data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return data.get(i).allTopics.size();
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(i).allTopics.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_topic_group_second, null);
            holder = new ViewHolderGroup(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderGroup) convertView.getTag();
        }
        holder.title.setText(data.get(groupPosition).groupName);
        holder.title.setTextColor(context.getResources().getColor(R.color.word_normal));
        if (isExpanded) {
            holder.indicator.setImageDrawable(context.getResources().getDrawable(R.drawable.jixiala_icon));
        } else {
            holder.indicator.setImageDrawable(context.getResources().getDrawable(R.drawable.next_right_icon));
        }
        return convertView;
//        return getGenericView(data.get(i).name);
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        final ViewHolderChild holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_topic_child, null);
            holder = new ViewHolderChild(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderChild) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                convertView.setBackgroundColor(context.getResources().getColor(R.color.color_main));
//                holder.title.setTextColor(context.getResources().getColor(R.color.white));
                handleClick(groupPosition, childPosition);
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                convertView.setBackgroundColor(context.getResources().getColor(R.color.color_main));
//                holder.title.setSelected();
                handleClick(groupPosition, childPosition);
            }
        });


        AllTopic allTopic = data.get(groupPosition).allTopics.get(childPosition);
        boolean contain = showTopicList.contains(allTopic.topicId);
        holder.checkBox.setChecked(contain);
//        holder.checkBox.setChecked(allTopic.isSelect());
        holder.title.setText(allTopic.topicName);
        return convertView;
    }

    private void handleClick(int groupPosition, int childPosition) {
        AllTopic allTopic = data.get(groupPosition).allTopics.get(childPosition);
        allTopic.toggle();
        addListener.add(parent, groupPosition, childPosition, allTopic.isSelect());

        // 注意，一定要通知 ExpandableListView 資料已經改變，ExpandableListView 會重新產生畫面
        notifyDataSetChanged();
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 第二层（目录二）
     */
    class ViewHolderGroup {
        TextView title;
        ImageView indicator;

        public ViewHolderGroup(View view) {
            this.title = (TextView) view.findViewById(R.id.tv_service);
            this.indicator = (ImageView) view.findViewById(R.id.iv_indicator);
        }
    }

    /**
     * 第三层（child层）
     */
    class ViewHolderChild {
        CheckBox checkBox;
        TextView title;

        public ViewHolderChild(View view) {
            this.checkBox = (CheckBox) view.findViewById(R.id.cb_select);
            this.title = (TextView) view.findViewById(R.id.tv_service);
        }
    }

    AddTopicListener addListener;

    public void setAddListener(AddTopicListener addListener) {
        this.addListener = addListener;
    }

    //事件回调
    interface AddTopicListener {
        void add(int parent, int group, int child, boolean select);
    }
}
