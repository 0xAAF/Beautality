package com.teamone.beautality.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.teamone.beautality.R;
import com.teamone.beautality.activities.owner.OwnerFragment;
import com.teamone.beautality.activities.owner.OwnerServicesFragment;

/**
 * Created by oshhepkov on 21.08.16.
 */

public class OwnerActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        startFragment(new OwnerFragment().setServicesClickListener(mServicesClickListener), false);
    }

    @Override
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
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

    View.OnClickListener mServicesClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startFragment(new OwnerServicesFragment(), true);
        }
    };
}
