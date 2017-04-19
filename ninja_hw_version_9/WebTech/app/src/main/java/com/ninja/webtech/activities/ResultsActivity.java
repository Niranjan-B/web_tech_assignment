package com.ninja.webtech.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.ninja.webtech.R;
import com.ninja.webtech.adapters.FragmentPageAdapterResults;

public class ResultsActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    FragmentPageAdapterResults mFragmentPageAdapterResults;
    private String mQueryFromUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mQueryFromUser = getIntent().getStringExtra("query");

        mToolbar = (Toolbar) findViewById(R.id.toolbar_activity_results);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout_activity_results);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_activity_results);
        mFragmentPageAdapterResults = new FragmentPageAdapterResults(getSupportFragmentManager(), ResultsActivity.this, mQueryFromUser);

        mViewPager.setAdapter(mFragmentPageAdapterResults);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mFragmentPageAdapterResults.getTabView(i));
        }

        initUi();
    }

    private void initUi() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ninja", "On resume called");
    }
}
