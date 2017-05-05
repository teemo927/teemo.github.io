package com.lanjiang.figersland.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.bean.BannerItem;
import com.lanjiang.figersland.delegate.TopicHeadItemDelegate;
import com.lanjiang.figersland.delegate.TopicItemDelegate;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.LineDecoration;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handleMessage interaction events.
 * Use the {@link TopicDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicDetailFragment extends BFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //第一个展示的项
    public static int FIRST_APPEARED_ITEM = 1;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<String> mListDatas = new ArrayList<>();

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TopicDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopicDetailFragment.
     */
    public static TopicDetailFragment newInstance(String param1, String param2) {
        TopicDetailFragment fragment = new TopicDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_detail_copy, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MultiItemTypeAdapter multiItemTypeAdapter = new MultiItemTypeAdapter(mContext, mListDatas);
        final TopicHeadItemDelegate headItemDelegate = new TopicHeadItemDelegate(mContext, initViewPagerDataSource());
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mBannerAdapter = headItemDelegate.getAdapter();
            }
        });
        multiItemTypeAdapter.addItemViewDelegate(headItemDelegate);
        multiItemTypeAdapter.addItemViewDelegate(new TopicItemDelegate(mContext));

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new LineDecoration(mContext, LinearLayoutManager.VERTICAL, LineDecoration.LEFT, false));
        recyclerView.setAdapter(multiItemTypeAdapter);

        initListData();
    }

    /**
     * 初始化viewpager数据: 视图，图片资源，文字资源, 并设置点击事件
     */
    private List<BannerItem> initViewPagerDataSource() {
        List<BannerItem> mViewList = new ArrayList<>();

        int[] resIds = new int[]{R.drawable.banner1, R.drawable.banner2};
        for (int i = 0; i < resIds.length; i++) {

            mViewList.add(new BannerItem(resIds[i]));
        }

        return mViewList;

    }

    private void initListData() {
        for (int i = 0; i < 8; i++) {
            mListDatas.add("兰江山地三期工程" + i);
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
