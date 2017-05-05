package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.MemberAdapter;
import com.lanjiang.figersland.utils.LogUtils;
import com.lanjiang.figersland.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布招募
 */
public class PublishRecruitActivity extends BaseToolbarActivity {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_introduce)
    EditText etIntroduce;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_rules)
    TextView tvRules;
    @BindView(R.id.rlt_agree)
    RelativeLayout rltAgree;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.activity_publish_recruit)
    LinearLayout activityPublishRecruit;
    @BindView(R.id.lv_member_has)
    ListView lvMemberHas;
    @BindView(R.id.lv_member_off)
    ListView lvMemberOff;
    @BindView(R.id.et_declaration)
    EditText etDeclaration;
    private MemberAdapter memberAdapterHas;
    private MemberAdapter memberAdapterOff;

    private List<String> mListMemberHas = new ArrayList<>();
    private List<String> mListMemberOff = new ArrayList<>();

    @OnClick({R.id.btn_submit, R.id.rlt_agree, R.id.tv_rules})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                List<String> listHas = memberAdapterHas.getListText();
                for (String s : listHas) {
                    LogUtils.e(TAG, "已有成员：" + s);
                }
                List<String> listOff = memberAdapterOff.getListText();
                for (String s : listOff) {
                    LogUtils.e(TAG, "尚缺成员：" + s);
                }
                finish();
                break;
            case R.id.rlt_agree:
                cbAgree.setChecked(!cbAgree.isChecked());
//                ToastUtils.show(mContext, "同意？" + cbAgree.isChecked());
                break;
            case R.id.tv_rules:
                ToastUtils.show(mContext, "发布规则");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_recruit);
        ButterKnife.bind(this);

        initView();
        getData();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.publish_recruit);
    }

    private void initView() {
        memberAdapterHas = new MemberAdapter(mContext, mListMemberHas, lvMemberHas);
        lvMemberHas.setAdapter(memberAdapterHas);

        memberAdapterOff = new MemberAdapter(mContext, mListMemberOff, lvMemberOff);
        lvMemberOff.setAdapter(memberAdapterOff);

        activityPublishRecruit.requestFocus();
    }

    public void getData() {
        for (int i = 0; i < 1; i++) {
            mListMemberHas.add("");
        }
        memberAdapterHas.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(lvMemberHas);

        for (int i = 0; i < 1; i++) {
            mListMemberOff.add("");
        }
        memberAdapterOff.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(lvMemberOff);
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
