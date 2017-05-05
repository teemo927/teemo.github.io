package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 审核成员
 */
public class MemberVerifyActivity extends BaseToolbarActivity {

    @BindView(R.id.tv_name)
    TextView tvName;

    @OnClick({R.id.btn_refuse, R.id.btn_agree})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_refuse:
                ToastUtils.show(mContext, getString(R.string.refuse) + tvName.getText() + "加入");
                finish();
                break;
            case R.id.btn_agree:
                ToastUtils.show(mContext, getString(R.string.agree) + tvName.getText() + "加入");
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_verify);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.member_verify);
    }

    private void initView() {
        tvName.setText("宋小亮 ");
    }
}
