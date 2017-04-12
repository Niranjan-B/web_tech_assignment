package com.ninja.webtech.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ninja.webtech.R;
import com.ninja.webtech.fragments.FavoritesFragment;
import com.ninja.webtech.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view_main_activity);

        initUi();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Search on FB");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        initNavigationView();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_fragment_container, new HomeFragment()).commit();
    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                try {
                    selectItem(item);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
    }

    private void selectItem(MenuItem item) throws IllegalAccessException, InstantiationException {
        Fragment fragment = null;
        Class fragmentClass = null;

        switch (item.getItemId()) {
            case R.id.menu_home:
                fragmentClass = HomeFragment.class;
                getSupportActionBar().setTitle("Search on FB");
                break;
            case R.id.menu_fav:
                fragmentClass = FavoritesFragment.class;
                getSupportActionBar().setTitle(item.getTitle());
                break;
            case R.id.menu_about_me:
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(MainActivity.this, AboutMeActivity.class));
                break;
        }

        if (fragmentClass != null) {
            fragment = (Fragment) fragmentClass.newInstance();

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_layout_fragment_container, fragment).commit();

            item.setChecked(true);

            mDrawerLayout.closeDrawers();
        }
    }
}
