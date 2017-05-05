package com.lanjiang.figersland.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.bean.PositionBean;
import com.lanjiang.figersland.bean.PositionInfoBean;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lin on 2017/2/22.
 */
public class HeaderAndContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    private List<PositionBean> PositionList;
    private List<PositionInfoBean> teamList = new ArrayList<>();
    private Context mContext;

    public HeaderAndContentAdapter(Context context, List<PositionBean> PositionList) {
        mContext = context;
        setPositionList(PositionList);
    }

    public void setPositionList(List<PositionBean> PositionList) {
        this.PositionList = PositionList;
        for (int i = 0; i < PositionList.size(); i++) {
            if (teamList != null) {
                teamList.addAll(PositionList.get(i).getPositionInfoBeanList());
            }
        }
        notifyDataSetChanged();
    }

    public List<PositionBean> getPositionList() {
        return PositionList;
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    /**
     * 返回值相同会被默认为同一项
     *
     * @param position
     * @return
     */
    @Override
    public long getHeaderId(int position) {
        return getSortType(position);
    }

    //获取当前职位的类型
    public int getSortType(int position) {
        int sort = -1;
        int sum = 0;
        for (int i = 0; i < PositionList.size(); i++) {
            if (position >= sum) {
                sort++;
            } else {
                return sort;
            }
            sum += PositionList.get(i).getPositionInfoBeanList().size();
        }
        return sort;
    }

    /**
     * ===================================================================================================
     * header的ViewHolder
     * ===================================================================================================
     */
    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.position_info_header_item, viewGroup, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        LinearLayout linearLayout = (LinearLayout) viewHolder.itemView;
        TextView textView = (TextView) linearLayout.getChildAt(1);
        textView.setText(PositionList.get(getSortType(position)).getSortName());
//        textView.setBackgroundColor(getRandomColor());
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 设置header的随机背景颜色
     */
    /*private int getRandomColor() {
        SecureRandom rgen = new SecureRandom();
        return Color.HSVToColor(150, new float[]{
                rgen.nextInt(359), 1, 1
        });
    }*/

    /**
     * ==================================================================================================
     * 以下为contentViewHolder
     * ==================================================================================================
     */
    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.position_info_content_item, parent, false);
        ContentViewHolder contentViewHolder = new ContentViewHolder(view, new IPositionClicks() {
            @Override
            public void onChangeBtn(int position) {
                notifyItemChanged(position);
            }
        });

      /*  // 设置item和按钮的点击事件
        contentViewHolder.llPositionInfo.setOnClickListener(this);
        contentViewHolder.btnAgree.setOnClickListener(this);
        contentViewHolder.btnRefuse.setOnClickListener(this);
        contentViewHolder.btnLeaveWord.setOnClickListener(this);*/

//        return new ContentViewHolder(view);
        return contentViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContentViewHolder viewHolder = (ContentViewHolder) holder;
        viewHolder.tvPersonName.setText(teamList.get(position).getName());
    }

    private class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout llPositionInfo;
        TextView tvPersonName;
        //      ImageView imageViewTeam;
        TextView btnAgree;
        TextView btnRefuse;
        TextView btnLeaveWord;
        IPositionClicks mlistener;

        public ContentViewHolder(View itemView, IPositionClicks listener) {
            super(itemView);
            llPositionInfo = (LinearLayout) itemView.findViewById(R.id.ll_position_info);
            tvPersonName = (TextView) itemView.findViewById(R.id.tv_person_name);
//          imageViewTeam = (ImageView) itemView.findViewById(R.id.iv_logo);
            btnAgree = (TextView) itemView.findViewById(R.id.btn_agree);
            btnRefuse = (TextView) itemView.findViewById(R.id.btn_refuse);
            btnLeaveWord = (TextView) itemView.findViewById(R.id.btn_leave_word);

            this.mlistener = listener;
            btnAgree.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_agree:
//                    WidgetUtils.changeCheckedButton(mContext, (TextView) v, R.drawable.shape_btn_blue, R.drawable.shape_btn_gray);
                    break;
                case R.id.btn_refuse:
//                    WidgetUtils.changeCheckedButton(mContext, (TextView) v, R.drawable.shape_btn_blue, R.drawable.shape_btn_gray);
                    break;
                case R.id.btn_leave_word:
//                    WidgetUtils.changeCheckedButton(mContext, (TextView) v, R.drawable.shape_btn_blue, R.drawable.shape_btn_gray);
                    break;
                default:
                    break;
            }


        }
    }

    private interface IPositionClicks {
        //关注按钮,需要更新按钮的状态
        public void onChangeBtn(int position);
    }

    /**
     * item和按钮的点击事件
     *
     * @param v
     */
    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_position_info:
                mContext.startActivity(new Intent(mContext,MyReleaseYipaiDetailActivity.class));
                break;
            case R.id.btn_agree:
                WidgetUtils.changeCheckedButton(mContext, (TextView) v, R.drawable.shape_btn_blue, R.drawable.shape_btn_gray);
                break;
            case R.id.btn_refuse:
                WidgetUtils.changeCheckedButton(mContext, (TextView) v, R.drawable.shape_btn_blue, R.drawable.shape_btn_gray);
                break;
            case R.id.btn_leave_word:
                WidgetUtils.changeCheckedButton(mContext, (TextView) v, R.drawable.shape_btn_blue, R.drawable.shape_btn_gray);
                break;
            default:
                break;
        }
    }*/

}
