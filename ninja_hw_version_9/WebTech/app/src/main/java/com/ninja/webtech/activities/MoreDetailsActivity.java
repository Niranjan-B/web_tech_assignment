package com.ninja.webtech.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.ninja.webtech.R;
import com.ninja.webtech.adapters.FragmentPageAdapterMoreDetails;
import com.ninja.webtech.utilities.StorageClass;

public class MoreDetailsActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    String mId, mName, mUrl, mType;
    Gson gson;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        mId = getIntent().getStringExtra("id");
        mName = getIntent().getStringExtra("name");
        mUrl = getIntent().getStringExtra("picture");
        mType = getIntent().getStringExtra("type");

        gson = new Gson();
        mPref = PreferenceManager.getDefaultSharedPreferences(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_activity_details);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout_activity_more_details);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_activity_more_details);
        FragmentPageAdapterMoreDetails adapter = new
                FragmentPageAdapterMoreDetails(getSupportFragmentManager(), MoreDetailsActivity.this, mId);

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
            case R.id.add_to_fav:
                StorageClass storageClass = new StorageClass(mId, mName, mUrl, mType);
                mPref.edit().putString(mId, gson.toJson(storageClass)).apply();
                Log.d("added", "" + mPref.getString(mId, ""));
                return true;
            case R.id.post_to_fb:
                return true;
            default:
                super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    private void initUi() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("More Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
