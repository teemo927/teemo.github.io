package com.lanjiang.figersland.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.CommonAdapter;
import com.lanjiang.figersland.adapter.ViewHolder;
import com.lanjiang.figersland.bean.MyReleaseBean;
import com.lanjiang.figersland.ui.MyOrderBaPingDetailActivity;
import com.lanjiang.figersland.ui.MyOrderMasterRequisitionActivity;
import com.lanjiang.figersland.ui.MyOrderRewardDetailActivity;
import com.lanjiang.figersland.ui.MyOrderYipaiDetailActivity;
import com.lanjiang.figersland.ui.MyReleaseBaPingDetailActivity;
import com.lanjiang.figersland.ui.MyReleaseMasterRequisitionActivity;
import com.lanjiang.figersland.ui.MyReleaseRewardDetailActivity;
import com.lanjiang.figersland.ui.MyReleaseTenderDetailActivity;
import com.lanjiang.figersland.ui.MyReleaseYipaiDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的发布Fragment
 * Created by Lin on 2016/12/28.
 */

public class MyReleaseFragment extends Fragment {

    @BindView(R.id.lv_mine_content)
    ListView lvMineContent;
    private ArrayList<MyReleaseBean> data;
    private int type;
    private int layout;
    private int index;
    private String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_release, container, false);
        ButterKnife.bind(this, view);
        init();
        setListViewData();
        return view;
    }

    /**
     * 初始化并取值
     */
    private void init() {
//        index = getArguments().getInt("index");
        Bundle bundle = getArguments();
        index = bundle.getInt("index");
        title = bundle.getString("title");
        switch (index) {
            case 0:
                type = 1;
                layout = R.layout.mine_fragment_listview_item;
                data = getFragmentData2(getMyReleaseBean(), getMyReleaseBean1());
                break;
            case 1:
                type = 1;
                layout = R.layout.mine_fragment_listview_item;
                data = getFragmentData(getMyReleaseBean2());
                break;
            case 2:
                type = 1;
                layout = R.layout.mine_fragment_listview_item;
                data = getFragmentData(getMyReleaseBean3());
                break;
            case 3:
                type = 2;
                layout = R.layout.mine_fragment_listview_item2;
                data = getFragmentData(getMyReleaseBean4());
                break;
            case 4:
                type = 3;
                layout = R.layout.mine_fragment_listview_item3;
                data = getFragmentData(getMyReleaseBean5());
                break;
            default:
                break;
        }

        // ListView的点击事件
        lvMineContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                if (TextUtils.isEmpty(title)) {
                    return;
                }
                if (getString(R.string.my_release).equals(title)) {
                    skipToMyRelease(intent);
                } else {
                    skipToMyOrder(intent);
                }
            }
        });
    }

    /**
     * 我的发布的子页面
     *
     * @param intent
     */
    private void skipToMyRelease(Intent intent) {
        switch (index) {
            case 0:
                intent.setClass(getContext(), MyReleaseTenderDetailActivity.class);
                break;
            case 1:
                intent.setClass(getContext(), MyReleaseRewardDetailActivity.class);
                break;
            case 2:
                intent.setClass(getContext(), MyReleaseBaPingDetailActivity.class);
                break;
            case 3:
                intent.setClass(getContext(), MyReleaseMasterRequisitionActivity.class);
                break;
            case 4:
                intent.setClass(getContext(), MyReleaseYipaiDetailActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    /**
     * 我的订单的子页面
     *
     * @param intent
     */
    private void skipToMyOrder(Intent intent) {
        switch (index) {
            case 0:
                intent.putExtra("type","tender");
                intent.setClass(getContext(), MyOrderRewardDetailActivity.class);
                break;
            case 1:
                intent.putExtra("type","reward");
                intent.setClass(getContext(), MyOrderRewardDetailActivity.class);
                break;
            case 2:
                intent.setClass(getContext(), MyOrderBaPingDetailActivity.class);
                break;
            case 3:
                intent.setClass(getContext(), MyOrderMasterRequisitionActivity.class);
                break;
            case 4:
                intent.setClass(getContext(), MyOrderYipaiDetailActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    /**
     * 设置ListView数据
     */
    private void setListViewData() {
        CommonAdapter<MyReleaseBean> adapter = new CommonAdapter<MyReleaseBean>(getContext(),
                data, layout) {
            @Override
            public void convert(ViewHolder helper, MyReleaseBean item) {
                helper.setImageResource(R.id.iv_logo, item.getIcon());
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_end_date_content, item.getEndDate());
                helper.setText(R.id.tv_other1, item.getOther1());
                helper.setText(R.id.tv_other2, item.getOther2());
                helper.setText(R.id.tv_other3, item.getOther3());
                helper.setText(R.id.tv_draft, item.getDraft());
                if (type == 2) {
                    int drawableId = 0;
                    switch (item.getOther4()) {
                        case "0":
                            drawableId = R.drawable.star0;
                            break;
                        case "1":
                            drawableId = R.drawable.star1;
                            break;
                        case "2":
                            drawableId = R.drawable.star2;
                            break;
                        case "3":
                            drawableId = R.drawable.star3;
                            break;
                        case "4":
                            drawableId = R.drawable.star4;
                            break;
                        case "5":
                            drawableId = R.drawable.star5;
                            break;
                        default:
                            break;
                    }
                    helper.setImageResource(R.id.tv_other4, drawableId);
                } else {
                    helper.setText(R.id.tv_other4, item.getOther4());
                }
            }
        };
        lvMineContent.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    /**
     * 模拟数据集合
     */
    private ArrayList<MyReleaseBean> getFragmentData(MyReleaseBean myReleaseBean) {
        // 模拟listview数据
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(myReleaseBean);
        }
        return data;
    }

    /**
     * 模拟数据集合
     */
    private ArrayList<MyReleaseBean> getFragmentData2(MyReleaseBean myReleaseBean, MyReleaseBean myReleaseBean1) {
        // 模拟listview数据
        data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            data.add(myReleaseBean);
        }
        for (int i = 0; i < 17; i++) {
            data.add(myReleaseBean1);
        }
        return data;
    }

    // 以下模拟一大堆 单条 数据
    private MyReleaseBean getMyReleaseBean() {
        MyReleaseBean myReleaseBean = new MyReleaseBean();
        myReleaseBean.setIcon(R.drawable.lanjianglogo);
        myReleaseBean.setTitle("兰江山第三期桩基检测工程");
        myReleaseBean.setReleaseDate("2017-01-01");
        myReleaseBean.setEndDate("2017-02-01");
        myReleaseBean.setOther1("竞标公司：");
        myReleaseBean.setOther2("15");
        myReleaseBean.setOther3("分类：");
        myReleaseBean.setOther4("基础工程");
        myReleaseBean.setDraft("（草稿）");
        return myReleaseBean;
    }

    private MyReleaseBean getMyReleaseBean1() {
        MyReleaseBean myReleaseBean = new MyReleaseBean();
        myReleaseBean.setIcon(R.drawable.lanjianglogo);
        myReleaseBean.setTitle("兰江山第三期桩基检测工程");
        myReleaseBean.setReleaseDate("2017-01-01");
        myReleaseBean.setEndDate("2017-02-01");
        myReleaseBean.setOther1("竞标公司：");
        myReleaseBean.setOther2("15");
        myReleaseBean.setOther3("分类：");
        myReleaseBean.setOther4("基础工程");
        myReleaseBean.setDraft("");
        return myReleaseBean;
    }

    private MyReleaseBean getMyReleaseBean2() {
        MyReleaseBean myReleaseBean2 = new MyReleaseBean();
        myReleaseBean2.setIcon(R.drawable.lanjianglogo);
        myReleaseBean2.setTitle("兰江山第三期桩基检测工程");
        myReleaseBean2.setReleaseDate("2017-01-01");
        myReleaseBean2.setEndDate("2017-02-01");
        myReleaseBean2.setOther1("应征公司：");
        myReleaseBean2.setOther2("15");
        myReleaseBean2.setOther3("悬赏金额：");
        myReleaseBean2.setOther4("8000");
        myReleaseBean2.setDraft("");
        return myReleaseBean2;
    }

    private MyReleaseBean getMyReleaseBean3() {
        MyReleaseBean myReleaseBean3 = new MyReleaseBean();
        myReleaseBean3.setIcon(R.drawable.lanjianglogo);
        myReleaseBean3.setTitle("办理用地规划许可证");
        myReleaseBean3.setReleaseDate("2017-01-01");
        myReleaseBean3.setEndDate("2017-02-01");
        myReleaseBean3.setOther1("订单状态：");
        myReleaseBean3.setOther2("未发货");
        myReleaseBean3.setOther3("霸屏金额：");
        myReleaseBean3.setOther4("8000");
        myReleaseBean3.setDraft("");
        return myReleaseBean3;
    }

    private MyReleaseBean getMyReleaseBean4() {
        MyReleaseBean myReleaseBean4 = new MyReleaseBean();
        myReleaseBean4.setIcon(R.drawable.lanjianglogo);
        myReleaseBean4.setTitle("上来露个脸");
        myReleaseBean4.setReleaseDate("2017-01-01");
        myReleaseBean4.setEndDate("2017-02-01");
        myReleaseBean4.setOther1("被征用次数：");
        myReleaseBean4.setOther2("15");
        myReleaseBean4.setOther3("好评：");
        myReleaseBean4.setOther4("4");
        myReleaseBean4.setDraft("");
        return myReleaseBean4;
    }

    private MyReleaseBean getMyReleaseBean5() {
        MyReleaseBean myReleaseBean5 = new MyReleaseBean();
        myReleaseBean5.setIcon(R.drawable.lanjianglogo);
        myReleaseBean5.setTitle("兰江山第三期桩基检测工程");
        myReleaseBean5.setReleaseDate("2017-01-01");
        myReleaseBean5.setEndDate("2017-02-01");
        myReleaseBean5.setOther1("需招募人数：");
        myReleaseBean5.setOther2("5");
        myReleaseBean5.setOther3("已申请人数：");
        myReleaseBean5.setOther4("16");
        myReleaseBean5.setDraft("");
        return myReleaseBean5;
    }
}
