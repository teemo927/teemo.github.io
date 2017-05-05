package com.lanjiang.figersland.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.ui.BidActivity;
import com.lanjiang.figersland.ui.MineActivity;
import com.lanjiang.figersland.ui.PreDevelopmentActivity;
import com.lanjiang.figersland.ui.SiteControlActivity;
import com.lanjiang.figersland.widget.LineDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * PopWindow 管理工具类
 * Created by Asus on 2017/1/10.
 */

public class PopUtils {

    /**
     * PopWindow 点击接口
     * 点击返回城市
     */
    public interface PopClickListener {

        void click(String city);

    }

    private static PopClickListener mListener;

    public static void setListener(PopClickListener mListener) {
        PopUtils.mListener = mListener;
    }

    /**
     * 城市
     *
     * @param mInflater .
     * @param mContext  .
     */
    public static PopupWindow showCityWindow(LayoutInflater mInflater, Context mContext, PopClickListener listener) {
        setListener(listener);

        View root = mInflater.inflate(R.layout.poplayout_city, null);
        RecyclerView recycler = (RecyclerView) root.findViewById(R.id.recycler_view);
        ImageView back = (ImageView) root.findViewById(R.id.iv_back);

        final PopupWindow popupWindow = new PopupWindow(root, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.window_anim_style);
        popupWindow.setFocusable(true);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);

        List<String> list = new ArrayList<>();
        list.add("深圳");
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("石家庄");
        list.add("呼和浩特");
        list.add("哈萨克斯坦市");
        list.add("哈萨克斯坦市区");
        list.add("哈萨克斯坦大市区");
        CommonAdapter adapter = new CommonAdapter<String>(mContext, R.layout.adapter_city, list) {
            @Override
            protected void convert(ViewHolder holder, final String city, int position) {
                holder.setText(R.id.tv_name, city);
                holder.setOnClickListener(R.id.adapter_city, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.click(city);
                        popupWindow.dismiss();
                    }
                });
            }
        };
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        recycler.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(root, Gravity.NO_GRAVITY, 0, 0);
        return popupWindow;
    }

    public static void showMainMenuWindow(Context mContext, View view, int height) {
        showMainMenuWindow(mContext, R.drawable.qianse_bg, view, height);
    }

    /**
     * 展示右上角菜单
     *
     * @param context     .
     * @param drawableRes .
     * @param view        .
     * @param height
     */
    public static void showMainMenuWindow(final Context context, final int drawableRes, View view, int height) {
        PopupWindow popWindow = initPopWindow(context, drawableRes);
        int rightMargin = (int) context.getResources().getDimension(R.dimen.dp_2);

        int[] location = new int[2];
        view.getLocationOnScreen(location);

        popWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] - popWindow.getWidth() + view.getWidth() - rightMargin, location[1] + height);
    }

    /**
     * 菜单
     */
    public static PopupWindow initPopWindow(final Context context, final int drawableRes) {
        int current = -1;
        if (context instanceof PreDevelopmentActivity) {
            current = 0;
            LogUtils.i("TAG", "-- PreDevelopmentActivity --");
        } else if (context instanceof BidActivity) {
            current = 1;
            LogUtils.i("TAG", "-- BidActivity --");
//        } else if (context instanceof SiteControlActivity) {
//            current = 2;
//            LogUtils.i("TAG", "-- SiteControlActivity --");
        } else if (context instanceof MineActivity) {
            current = 2;
            LogUtils.i("TAG", "-- MineActivity --");
        }

        //内容显示
        int[] drawable = new int[]{R.drawable.zhaotoubiao, R.drawable.qianqi,  R.drawable.user};
        //对应启动的activity
        Class<?>[] activityClasses = new Class[]{PreDevelopmentActivity.class, BidActivity.class, MineActivity.class};
        //名字
        String[] activityNames = new String[]{context.getString(R.string.pre_develop), context.getString(R.string.bid),  context.getString(R.string.me)};

        List nameList = getList(current, activityNames);
        final List activityList = getList(current, activityClasses);
        final List drawableList = getList(current, drawable);

        View root = LayoutInflater.from(context).inflate(R.layout.popwindow_base_menu, null);
        if (drawableRes != 0) {
            root.setBackgroundResource(drawableRes);
        }
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);

        int width = (int) context.getResources().getDimension(R.dimen.dp_120);
        final PopupWindow mMenuWindow = new PopupWindow(root, width, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置popWindow的显示和消失动画
        mMenuWindow.setAnimationStyle(R.style.menu_window_anim_style);
        mMenuWindow.setFocusable(true);
        mMenuWindow.setBackgroundDrawable(new BitmapDrawable());
        mMenuWindow.setOutsideTouchable(true);
        mMenuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        CommonAdapter adapter = new CommonAdapter<String>(context, R.layout.adapter_main_menu, nameList) {
            @Override
            protected void convert(ViewHolder holder, String s, final int position) {

                holder.setText(R.id.tv_name, s);
                holder.setImageResource(R.id.iv_icon, (Integer) drawableList.get(position));
                holder.setOnClickListener(R.id.adapter_main_menu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, (Class<?>) activityList.get(position)));
                        mMenuWindow.dismiss();
                    }
                });
            }
        };
        recyclerView.addItemDecoration(new LineDecoration(context, LinearLayoutManager.VERTICAL, R.drawable.shape_divider_main_menu, LineDecoration.LEFT_RIGHT, drawableRes));
        recyclerView.setAdapter(adapter);
        return mMenuWindow;
    }

    private static List getList(int current, int[] drawable) {
        List list = new ArrayList();
        for (int i = 0; i < drawable.length; i++) {
            if (i != current) {
                list.add(drawable[i]);
            }
        }
        return list;
    }

    private static List getList(int current, Object[] drawable) {
        List list = new ArrayList();
        for (int i = 0; i < drawable.length; i++) {
            if (i != current) {
                list.add(drawable[i]);
            }
        }
        return list;
    }

}
