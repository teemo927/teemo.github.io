package com.lanjiang.figersland.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.KeyboardUtils;
import com.lanjiang.figersland.utils.ToastUtils;
import com.lanjiang.figersland.widget.LineDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends BFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private List<String> list = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @OnClick({R.id.iv_cancel,R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_cancel:
                etSearch.setText("");
                etSearch.requestFocus();
                break;
            case R.id.iv_back:
                mActivity.finish();
                break;
        }
    }

    public SearchFragment() {
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ToastUtils.show(mContext,"搜索");
                }
                return false;
            }

        });
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new LineDecoration(mContext, LinearLayoutManager.VERTICAL,LineDecoration.LEFT));

//        MultiItemTypeAdapter adapter = new MultiItemTypeAdapter(mContext,list);
//        adapter.addItemViewDelegate(new ItemViewDelegate() {
//            @Override
//            public int getItemViewLayoutId() {
//                return 0;
//            }
//
//            @Override
//            public boolean isForViewType(Object item, int position) {
//                return false;
//            }
//
//            @Override
//            public void convert(ViewHolder holder, Object o, int position) {
//
//            }
//        });
//        recyclerView.setAdapter(adapter);


        recyclerView.setAdapter(new CommonAdapter<String>(mContext,R.layout.adapter_search,list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_name,s);
                holder.setText(R.id.tv_start_time,getString(R.string.start_date_str,"2016-12-28"));
                holder.setText(R.id.tv_end_time,getString(R.string.end_date_str,"2017-01-02"));
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    getData(etSearch.getText().toString());
                    KeyboardUtils.hideSoftInput(getActivity());
                }
                return false;
            }
        });

    }

    private void getData(String text) {
        list.clear();
        recyclerView.getAdapter().notifyDataSetChanged();
        list.add(text);
        list.add(text);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
