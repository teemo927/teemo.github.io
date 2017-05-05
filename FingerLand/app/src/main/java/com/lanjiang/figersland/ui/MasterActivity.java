package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 高人榜
 */
public class MasterActivity extends BaseToolbarActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    @BindView(R.id.rlt_publish)
    RelativeLayout rltPublish;

    @OnClick(R.id.rlt_publish)
    public void click(View view){
        startActivity(new Intent(mContext,AddMasterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaoren);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        List<String> list = new ArrayList<>();
        list.add("李云龙");
        list.add("和尚");
        list.add("赵刚");
        CommonAdapter adapter = new CommonAdapter<String>(mContext, R.layout.adapter_gaoren, list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_name, s);
                holder.setOnClickListener(R.id.adpapter_gaoren, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, MasterDetailActivity.class));
                    }
                });
                holder.setVisible(R.id.iv_mark,position%2 == 0);
            }
        };

        EmptyWrapper mEmptyWrapper = new EmptyWrapper(adapter);
        mEmptyWrapper.setEmptyView(R.layout.empty_view);

        recyclerView.setAdapter(mEmptyWrapper);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.gaoren);
    }
}
