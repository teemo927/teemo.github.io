package com.lanjiang.figersland.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.Constant;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.ui.SignUpDetailActivity;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BidDetailFragment extends BFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.llt_money)
    LinearLayout lltMoney;

    private int mParam1;
    private String mParam2;

    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.llt_share)
    LinearLayout lltShare;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.llt_call)
    LinearLayout lltCall;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.llt_bottom)
    LinearLayout lltBottom;
    @BindView(R.id.iv_icon)
    CircleImageView ivIcon;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_project_location)
    TextView tvProjectLocation;
    @BindView(R.id.tv_project_time)
    TextView tvProjectTime;
    @BindView(R.id.tv_bid_range_introduce)
    TextView tvBidRangeIntroduce;
    @BindView(R.id.tv_contact_information)
    TextView tvContactInformation;
    @BindView(R.id.tv_entry_condition)
    TextView tvEntryCondition;
    @BindView(R.id.tv_related_document)
    TextView tvRelatedDocument;
    @BindView(R.id.tv_bid_detail_info)
    TextView tvBidDetailInfo;
    @BindView(R.id.tv_reward_money)
    TextView tvRewardMoney;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    @OnClick({R.id.tv_share, R.id.llt_share, R.id.tv_call, R.id.llt_call, R.id.tv_sign_up, R.id.tv_related_document})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_share:
            case R.id.llt_share:
                ((BaseToolbarActivity) getActivity()).share("", false);
//                ShareBean shareBean1 = new ShareBean("", "分享文本", "分享标题", "http://www.baidu.com", "", "http://www.github.com", "我是评论评论", "百度一下 ", "http://www.baidu.com");
//                shareBean1.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
//                ShareUtils.showShare(mContext, shareBean1);
                break;
            case R.id.tv_call:
            case R.id.llt_call:
                break;
            case R.id.tv_sign_up:
                startActivity(new Intent(mContext, SignUpDetailActivity.class));
                break;
            case R.id.tv_related_document:
                ToastUtils.show(mContext, "downloading ...");
                break;
            default:
                break;
        }
    }

    public BidDetailFragment() {
    }

    public static BidDetailFragment newInstance(int param1, String param2) {
        BidDetailFragment fragment = new BidDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        tvProjectLocation.setText(getString(R.string.project_location, "北京"));
        tvProjectTime.setText(getString(R.string.project_time, "2016-12-12"));
        tvRelatedDocument.setText(getString(R.string.name_doc, "报名详情"));
        lltMoney.setVisibility(mParam1 == Constant.TYPE_BID ? View.GONE : View.VISIBLE);
    }


}
