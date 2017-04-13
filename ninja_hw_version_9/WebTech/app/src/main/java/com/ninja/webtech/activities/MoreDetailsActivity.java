package com.ninja.webtech.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ninja.webtech.R;
import com.ninja.webtech.adapters.FragmentPageAdapterMoreDetails;

public class MoreDetailsActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_activity_details);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout_activity_more_details);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_activity_more_details);
        FragmentPageAdapterMoreDetails adapter = new FragmentPageAdapterMoreDetails(getSupportFragmentManager(), MoreDetailsActivity.this);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }

        initUi();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("More Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
