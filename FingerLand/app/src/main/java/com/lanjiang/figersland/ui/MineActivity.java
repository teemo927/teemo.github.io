package com.lanjiang.figersland.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.lanjiang.figersland.BaseActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.adapter.MineMenuAdapter;
import com.lanjiang.figersland.bean.MineMenuBean;
import com.lanjiang.figersland.utils.PopUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lin on 2016/12/26.
 */

public class MineActivity extends BaseActivity {
    @BindView(R.id.iv_user_login)
    ImageView ivUserLogin;
    @BindView(R.id.fl_top)
    FrameLayout flTop;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.gv_mine_menu)
    GridView gvMineMenu;

    private List<MineMenuBean> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.unbinder = ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void initView() {
        data = getData();
        MineMenuAdapter mineMenuAdapter = new MineMenuAdapter(this, data);
        mineMenuAdapter.notifyDataSetChanged();
        gvMineMenu.setAdapter(mineMenuAdapter);

        gvMineMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.putExtra("title", getResources().getString(R.string.my_release));
                        String[] tabs1 = {"招标发布", "悬赏发布", "霸屏发布", "高人征用", "一拍即合"};
                        intent.putExtra("tabs", tabs1);
                        intent.setClass(mContext, MyReleaseActivity.class);
                        break;
                    case 1:
                        intent.putExtra("title", getResources().getString(R.string.my_orders));
                        String[] tabs2 = {"招标订单", "悬赏订单", "霸屏订单", "高人征用", "一拍即合"};
                        intent.putExtra("tabs", tabs2);
                        intent.setClass(mContext, MyReleaseActivity.class);
                        break;
                    case 2:
                        intent.setClass(mContext, VerifyActivity.class);
                        break;
                    case 3:
                        intent.setClass(mContext, MyNewsActivity.class);
                        break;
                    case 4:
                        intent.setClass(mContext, MineActivity.class);
                        break;
                    case 5:
                        intent.setClass(mContext, MyCollectionActivity.class);
                        break;
                    case 6:
                        intent.setClass(mContext, SettingActivity.class);
                        break;
                    case 7:
                        intent.setClass(mContext, HelpActivity.class);
                        break;
                    default:
                        break;
                }
                startActivity(intent);
            }
        });
    }

    private List<MineMenuBean> getData() {
        data = new ArrayList<>();
        MineMenuBean mineMenuBean1 = new MineMenuBean("我的发布", R.drawable.fabu, "25");
        MineMenuBean mineMenuBean2 = new MineMenuBean("我的订单", R.drawable.dingdan, "15");
        MineMenuBean mineMenuBean3 = new MineMenuBean("企业认证", R.drawable.qiye, "未通过");
        MineMenuBean mineMenuBean4 = new MineMenuBean("我的消息", R.drawable.news, "3条未读");
        MineMenuBean mineMenuBean5 = new MineMenuBean("我的好友", R.drawable.friend, "13");
        MineMenuBean mineMenuBean6 = new MineMenuBean("我的收藏", R.drawable.collection, "5");
        MineMenuBean mineMenuBean7 = new MineMenuBean("设置", R.drawable.shezhi, "完成度90%");
        MineMenuBean mineMenuBean8 = new MineMenuBean("帮助", R.drawable.help, "常见问题");
        data.add(mineMenuBean1);
        data.add(mineMenuBean2);
        data.add(mineMenuBean3);
        data.add(mineMenuBean4);
        data.add(mineMenuBean5);
        data.add(mineMenuBean6);
        data.add(mineMenuBean7);
        data.add(mineMenuBean8);

        return data;
    }

    @OnClick(R.id.iv_menu)
    public void onClick() {
        PopUtils.showMainMenuWindow(mContext, ivMenu, ivMenu.getHeight());
//        showPopWindow(initPopWindow(0, 3));
    }
}
