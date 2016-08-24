package com.teamone.beautality.activities;

/**
 * Created by oshhepkov on 20.08.16.
 */

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.teamone.beautality.R;
import com.teamone.beautality.api.ApiService;

/**
 * Created by Eduard on 14.08.2016.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    public Toolbar mToolbar;
    private ApiService mApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApi = new ApiService();
    }

    @Override
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        this.setSupportActionBar(mToolbar);

    }

    public ApiService.Api getApi() {
        return mApi.getApi();
    }

    public void startFragment(Fragment fragment, boolean backStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        if (backStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
    /*
     * show message with button ok
     * @param resourceId String message
     */
    public void showErrorMessage(int title, int resourceId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(resourceId)
                .setPositiveButton(R.string.alert_button_ok, null);
        AlertDialog alert = builder.create();
        alert.show();
    }

}