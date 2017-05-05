package com.lanjiang.figersland.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.DialogUtils;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.LineDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseToolbarActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<String> mList = new ArrayList<>();

    @OnClick({R.id.tv_login_out})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_login_out:
                DialogUtils.showYesNo(mContext, getString(R.string.sure_set_default), new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        finish();
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initView();
        getData();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.settings);
    }

    private void initView() {

        recyclerView.setAdapter(new CommonAdapter<String>(mContext, R.layout.adapter_setting, mList) {
            @Override
            protected void convert(ViewHolder holder, String s, final int position) {
                holder.setText(R.id.tv_name, s);
                holder.setOnClickListener(R.id.adapter_setting, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                startActivity(new Intent(mContext, PaySettingActivity.class));
                                break;
                        }
                    }
                });

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.addItemDecoration(new LineDecoration(mContext, LinearLayoutManager.VERTICAL, LineDecoration.LEFT));
    }


    public void getData() {

        mList.add(getString(R.string.msg_notice));
        mList.add(getString(R.string.password_setting));
        mList.add(getString(R.string.pay_setting));
    }
}
