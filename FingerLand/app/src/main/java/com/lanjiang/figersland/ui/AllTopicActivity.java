package com.lanjiang.figersland.ui;

import android.os.Bundle;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.fragment.InterestedTopicFragment;

public class AllTopicActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_topic);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_interested_topic, InterestedTopicFragment.newInstance()).commit();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(getString(R.string.interested_topic));
    }
}
