package com.ninja.webtech.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ninja.webtech.R;

public class MoreDetailsActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_activity_details);

        initUi();
    }

    private void initUi() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("More Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
