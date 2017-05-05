package com.lanjiang.figersland.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.CommonAdapter;
import com.lanjiang.figersland.adapter.ViewHolder;
import com.lanjiang.figersland.bean.PayWayBean;
import com.lanjiang.figersland.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lin on 2016/12/14.
 */

public class ReleaseRewardPayActivity extends BaseToolbarActivity {

    @BindView(R.id.iv_zhongtie_icon)
    ImageView ivZhongtieIcon;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_release_date_content)
    TextView tvReleaseDateContent;
    @BindView(R.id.tv_closing_date)
    TextView tvClosingDate;
    @BindView(R.id.tv_closing_date_content)
    TextView tvClosingDateContent;
    @BindView(R.id.tv_reward_classification)
    TextView tvRewardClassification;
    @BindView(R.id.tv_reward_classification_content)
    TextView tvRewardClassificationContent;
    @BindView(R.id.tv_pay_amount)
    TextView tvPayAmount;
    @BindView(R.id.tv_currency_China)
    TextView tvCurrencyChina;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.lv_pay_way)
    ListView lvPayWay;
    @BindView(R.id.btn_confirm_pay)
    Button btnConfirmPay;

    private Unbinder unbinder;
    private List<PayWayBean> data;
    private String titleName;

    private CommonAdapter<PayWayBean> commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fromWhere();
        setContentView(R.layout.activity_release_reward_pay);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(titleName);
    }


    /**
     * 初始化
     */
    private void initView() {
        // 获取数据
        getData();

        commonAdapter.notifyDataSetChanged();

        lvPayWay.setAdapter(commonAdapter);

        lvPayWay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int count = data.size();
                for (int i = 0; i < count; i++) {
                    RelativeLayout item = (RelativeLayout) lvPayWay.getChildAt(i);
                    ((ImageView) (item.getChildAt(2))).setImageResource(R.drawable.ssdk_oks_classic_check_default);
                }
                RelativeLayout item = (RelativeLayout) lvPayWay.getChildAt(position);
                ((ImageView) (item.getChildAt(2))).setImageResource(R.drawable.checked);
            }
        });

    }

    /**
     * 获取数据
     */
    private void getData() {
        // 初始化数据集合和adapter
        data = new ArrayList<>();
        commonAdapter = new CommonAdapter<PayWayBean>(this, data, R.layout.pay_way_item) {
            @Override
            public void convert(ViewHolder helper, PayWayBean item) {
                helper.setText(R.id.tv_Pay_way, item.getPayWayName());
                helper.setImageResource(R.id.iv_pay_icon, item.getPayWayIcon());
            }
        };

        PayWayBean payWayBean1 = new PayWayBean("支付宝支付", R.drawable.zhifubao_icon);
        PayWayBean payWayBean2 = new PayWayBean("微信支付", R.drawable.weixin_icon1);
        PayWayBean payWayBean3 = new PayWayBean("银联支付", R.drawable.yinlian_icon);
        PayWayBean payWayBean4 = new PayWayBean("中国农业银行储蓄卡   尾号4510", R.drawable.nongye_icon);
        data.add(payWayBean1);
        data.add(payWayBean2);
        data.add(payWayBean3);
        data.add(payWayBean4);
    }

    /**
     * 通过传值判断从哪个页面过来
     */
    private void fromWhere() {
        String type = getIntent().getStringExtra("type");
        if (type == null) {
            titleName = getString(R.string.post_tender);
        }
        if ("rewards".equals(type)) {
            titleName = getString(R.string.payment_order);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @OnClick(R.id.btn_confirm_pay)
    public void onClick() {
        ToastUtils.show(this, "支付成功");
//        startActivity(new Intent(mContext, MainActivity.class));
    }

}
