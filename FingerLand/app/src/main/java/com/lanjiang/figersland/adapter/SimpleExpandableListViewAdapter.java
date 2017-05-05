package com.lanjiang.figersland.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.bean.AllTopic;
import com.lanjiang.figersland.bean.TopicGroup2;
import com.lanjiang.figersland.bean.Topics;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.widget.InnerExpandableListView;

import java.util.List;

/**
 * 三级菜单 最上层
 * Created by Asus on 2016/12/5.
 */

public class SimpleExpandableListViewAdapter extends BaseExpandableListAdapter implements InnerExpandableListViewAdapter.AddTopicListener {


    private Context context;
    private List<Topics> data;

    public SimpleExpandableListViewAdapter(Context context, List<Topics> data) {
        this.context = context;
        this.data = data;
    }

    public List<Topics> getData() {
        return data;
    }

    public void setData(List<Topics> data) {
        this.data = data;
    }

    public void refreshData(List<Topics> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        // 很关键，，一定要返回  1
        return 1;
//        return data.get(i).classList.size();
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return data.get(i).group2s.get(i1);
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
    public View getGroupView(int i, final boolean b, View view, ViewGroup viewGroup) {
        final ViewHolderGroup holder;
        if (view == null) {
            holder = new ViewHolderGroup();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_topic_group_first, null);
            holder.icon = (ImageView) view.findViewById(R.id.iv_group);
            holder.title = (TextView) view.findViewById(R.id.tv_service);
            holder.indicator = (ImageView) view.findViewById(R.id.iv_indicator);
            view.setTag(holder);

        } else {
            holder = (ViewHolderGroup) view.getTag();
        }

        Topics topics = data.get(i);
        holder.icon.setImageResource(topics.firstGroupIcon);
        holder.title.setText(topics.firstGroupName);

        if (b) {
            holder.icon.setImageResource(topics.firstGroupIconPress);
            holder.title.setTextColor(context.getResources().getColor(R.color.white));
            holder.indicator.setImageDrawable(context.getResources().getDrawable(R.drawable.jixiala_select));
            view.setBackgroundColor(context.getResources().getColor(R.color.group_select));
            view.setSelected(true);
        } else {
            holder.icon.setImageResource(topics.firstGroupIcon);
            holder.title.setTextColor(context.getResources().getColor(R.color.black));
            holder.indicator.setImageDrawable(context.getResources().getDrawable(R.drawable.next_right_icon));
            view.setBackgroundColor(context.getResources().getColor(R.color.white));
            view.setSelected(false);
        }

        return view;
//        return getGenericView(data.get(i).name);
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        // 返回子ExpandableListView 的对象  此时传入是该父条目，即大学的对象（有歧义。。）
        return getGenericExpandableListView(i, data.get(i).group2s, data.get(i).showTopicList);
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 根据字符串生成布局，，因为我没有写layout.xml 所以用java 代码生成
     * <p>
     * 实际中可以通过Inflate加载自己的自定义布局文件，设置数据之后并返回
     *
     * @param string
     * @return
     */
    private TextView getGenericView(String string) {

        ExpandableListView.LayoutParams layoutParams = new ExpandableListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);

        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

        textView.setPadding(40, 20, 0, 20);
        textView.setText(string);
        textView.setTextColor(Color.BLUE);
        return textView;
    }

    /**
     * 三级菜单 第二层
     * 返回子ExpandableListView 的对象
     *
     * @param i .
     * @param group2List    .
     * @param showTopicList .
     * @return .
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public ExpandableListView getGenericExpandableListView(int i, List<TopicGroup2> group2List, List<Integer> showTopicList) {

        InnerExpandableListView view = new InnerExpandableListView(context);
        view.setBackgroundColor(context.getResources().getColor(R.color.white));
        view.setGroupIndicator(null);
        view.setDividerHeight(1);

        // 加载第二层的适配器
        final InnerExpandableListViewAdapter adapter = new InnerExpandableListViewAdapter(context, i, group2List, showTopicList);
        adapter.setAddListener(this);

        view.setAdapter(adapter);

        view.setPadding(20, 0, 0, 0);
        return view;
    }

    class ViewHolderGroup {
        ImageView icon;
        TextView title;
        ImageView indicator;
    }

    @Override
    public void add(int parent, int group, int child, boolean select) {
        Topics topics = data.get(parent);
        AllTopic allTopic = topics.group2s.get(group).allTopics.get(child);
        if (select) {
            topics.showTopicList.add(allTopic.topicId);
        } else {
            if (topics.showTopicList.contains(allTopic.topicId)) {
                topics.showTopicList.remove(allTopic.topicId);
            }
        }
        allTopic.setSelect(select);

//        ToastUtils.show(context, "----: " + allTopic.isSelect());
        LogUtils.e("----", "" + allTopic.isSelect());
        setData(data);
    }

}
