package com.lanjiang.figersland.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lanjiang.figersland.R;

import java.util.List;


/**
 * 成员添加
 * Created by Asus on 2016/12/27.
 */

public class MemberAdapter extends BaseAdapter {

    private Context context;
    private List<String> listText;
    private ListView listView;

    public MemberAdapter(Context context, List<String> listText, ListView listView) {
        this.context = context;
        this.listText = listText;
        this.listView = listView;
//        setListViewHeightBasedOnChildren(listView);
    }

    public List<String> getListText() {
        return listText;
    }

    @Override
    public int getCount() {
        if (listText != null && listText.size() != 0) {
            return listText.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listText.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.adapter_add_member, null);
            holder.editText = (EditText) convertView.findViewById(R.id.et_name);
            holder.btnAdd = (Button) convertView.findViewById(R.id.btn_add);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.ref = position;

        holder.editText.setText(listText.get(position));
        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listText.set(holder.ref, s.toString());
            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listText.add("");
                notifyDataSetChanged();
                setListViewHeightBasedOnChildren(listView);
            }
        });
        holder.btnAdd.setVisibility(position == getCount() - 1 ? View.VISIBLE : View.GONE);

        return convertView;
    }

    private class ViewHolder {
        EditText editText;
        Button btnAdd;
        int ref;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
