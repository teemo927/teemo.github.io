package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordMasterActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_use_count)
    TextView tvUseCount;
    @BindView(R.id.tv_history_record)
    TextView tvHistoryRecord;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.activity_record_gaoren)
    LinearLayout activityRecordGaoren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_gaoren);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tvEndTime.setText(getString(R.string.project_time,"2017-01-17"));

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        List list = new ArrayList();
        list.add("2016-01-15 14:32:15");
        list.add("2016-01-15 14:46:20");
        list.add("2016-01-15 14:55:30");
        recyclerView.setAdapter(new CommonAdapter<String>(mContext, R.layout.adapter_use_record, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_time, s);
            }
        });
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.use_record);
    }
}
