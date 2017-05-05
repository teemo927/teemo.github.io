package com.lanjiang.figersland.ui;

import android.os.Bundle;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.R;

public class HelpActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.help);
    }
}
