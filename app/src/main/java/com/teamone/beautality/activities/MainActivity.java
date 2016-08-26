package com.teamone.beautality.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.teamone.beautality.R;
import com.teamone.beautality.activities.companies.CompaniesFragment;
import com.teamone.beautality.activities.companies.adapter.CompaniesAdapter;
import com.teamone.beautality.models.response.ListItemResponse;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String BUNDLE_COMPANIES_ITEM = "companies_item";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        startFragment(((CompaniesFragment) newInstance(FRAGMENT_LIST)).setOnItemClickListener(mCompaniesItemClickListener), false);

    }

    @Override
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.navigation_layer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawer.addDrawerListener(toggle);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_list:
                mToolbar.setTitle(R.string.nav_list);
                startFragment(
                        ((CompaniesFragment) newInstance(FRAGMENT_LIST))
                                .setOnItemClickListener(mCompaniesItemClickListener), false);
                break;
            case R.id.nav_help:
                mToolbar.setTitle(R.string.nav_list);
                startFragment(newInstance(FRAGMENT_HELP), false);
                break;
            case R.id.nav_about:
                mToolbar.setTitle(R.string.nav_about);
                startFragment(newInstance(FRAGMENT_ABOUT), false);
                break;
            case R.id.nav_logout:
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.key_settings), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(getString(R.string.key_settings_access_token)).commit();
                newActivity(ACTIVITY_LOGIN);
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.navigation_layer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.navigation_layer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    CompaniesAdapter.OnItemClickListener mCompaniesItemClickListener = new CompaniesAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(ListItemResponse item) {

            Intent intent = new Intent(MainActivity.this, OwnerActivity.class);
            Gson gson = new Gson();
            String json = gson.toJson(item);
            intent.putExtra(BUNDLE_COMPANIES_ITEM, json);
            startActivity(intent);
        }
    };


}
