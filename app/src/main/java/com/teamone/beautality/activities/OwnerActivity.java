package com.teamone.beautality.activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.teamone.beautality.R;
import com.teamone.beautality.activities.owner.OwnerFragment;
import com.teamone.beautality.models.response.ListItemResponse;

/**
 * Created by oshhepkov on 21.08.16.
 */

public class OwnerActivity extends BaseActivity{

    private ListItemResponse mItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        Gson gson = new Gson();
        mItem = gson.fromJson(getIntent().getStringExtra(MainActivity.BUNDLE_COMPANIES_ITEM), ListItemResponse.class);
        startFragment(new OwnerFragment().setItem(mItem), false);


    }

    @Override
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        mToolbar.setTitle(mItem.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
