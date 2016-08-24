package com.teamone.beautality.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.teamone.beautality.R;
import com.teamone.beautality.activities.companies.CompaniesFragment;
import com.teamone.beautality.activities.companies.adapter.CompaniesAdapter;
import com.teamone.beautality.models.response.CompaniesItemResponse;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final String BUNDLE_COMPANIES_ITEM = "companies_item";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        startFragment(new CompaniesFragment().setOnItemClickListener(mCompaniesItemClickListener), false);
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
            case R.id.nav_camara:
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
        }
        else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    CompaniesAdapter.OnItemClickListener mCompaniesItemClickListener = new CompaniesAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(CompaniesItemResponse item) {
            Intent intent = new Intent(MainActivity.this, OwnerActivity.class);
            intent.putExtra(BUNDLE_COMPANIES_ITEM, item.getId());
            startActivity(intent);
        }
    };
}
