package com.lanjiang.figersland.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.fragment.TopicDetailFragment;

/**
 * 感兴趣页面（如勘探设计）
 */
public class TopicDetailActivity extends BaseToolbarActivity implements TopicDetailFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_topic_detail, TopicDetailFragment.newInstance("","")).commit();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.kantan_design);
        setSearchVisible(View.VISIBLE);
        setRightMenu();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
