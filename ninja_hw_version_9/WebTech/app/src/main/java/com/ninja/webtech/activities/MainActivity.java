package com.ninja.webtech.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.ninja.webtech.GlobalSingleton;
import com.ninja.webtech.R;
import com.ninja.webtech.fragments.FavoritesFragment;
import com.ninja.webtech.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private SharedPreferences mPref;
    LocationManager mLocationManager;
    LocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view_main_activity);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("ninja", "loc changed = " + location.toString());
                GlobalSingleton.setmLat(location.getLatitude());
                GlobalSingleton.setmLon(location.getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d("ninja", "status changed = ");
            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("ninja", "provider enabled = ");
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("ninja", "status disabled = ");
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET }, 10);
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);

        initUi();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("ninja", "permission granted");
                    mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
                } else {
                    Log.d("ninja", "permission denied");
                }
        }
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
