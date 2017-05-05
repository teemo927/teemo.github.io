package com.lanjiang.figersland.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.bean.PositionBean;

import java.util.List;

/**
 * Created by Lin on 2017/2/22.
 */
public class PositionAdapter extends RecyclerView.Adapter<PositionAdapter.ViewHolder> {
    private Context context;
    private List<PositionBean> PositionList;

    public PositionAdapter(Context context, List<PositionBean> PositionList) {
        this.context = context;
        this.PositionList = PositionList;
    }

    public void setPositionList(List<PositionBean> PositionList) {
        this.PositionList = PositionList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.position_item, null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(PositionList.get(position).getSortName());
        holder.textView.setSelected(PositionList.get(position).isSeleted());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return PositionList == null ? 0 : PositionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_position);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
