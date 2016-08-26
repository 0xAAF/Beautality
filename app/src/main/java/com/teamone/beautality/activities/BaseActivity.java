package com.teamone.beautality.activities;

/**
 * Created by oshhepkov on 20.08.16.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.teamone.beautality.R;
import com.teamone.beautality.activities.about.AboutFragment;
import com.teamone.beautality.activities.companies.CompaniesFragment;
import com.teamone.beautality.activities.help.HelpFragment;
import com.teamone.beautality.api.ApiService;

/**
 * Created by Eduard on 14.08.2016.
 */
public class BaseActivity extends AppCompatActivity {

    public final String TAG = BaseActivity.class.getSimpleName();
    public final String APP = "bc2d3bb69ff9e36b25fc78d94db1e94d";
    public final String CLI = "8b9b9c439d326279913d314d650b7a4a";

    protected final int
            FRAGMENT_LIST = 0,
            FRAGMENT_HELP = 1,
            FRAGMENT_ABOUT = 2;
    protected final int
            ACTIVITY_LOGIN = 0;

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
        getSupportFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content,fragment);
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
    public Fragment newInstance(int id) {
        switch ( id ) {
            case FRAGMENT_LIST:return new CompaniesFragment();
            case FRAGMENT_HELP:return new HelpFragment();
            case FRAGMENT_ABOUT:return new AboutFragment();
            default: return null;
        }
    }
    public void newActivity(int id) {
        Class newActivity = null;
        boolean finish = false;
        switch ( id ) {
            case ACTIVITY_LOGIN:
                newActivity = LoginActivity.class;
                finish = true;
                break;
        }
        if (newActivity != null) {
            Intent intent = new Intent(this, newActivity);
            startActivity(intent);
            if (finish) {
                finish();
            }
        }
    }
    public void replaceView(int targetId, int resoureId) {
        View C = findViewById(targetId);
        ViewGroup parent = (ViewGroup) C.getParent();
        int index = parent.indexOfChild(C);
        parent.removeView(C);
        C = getLayoutInflater().inflate(resoureId, parent, false);
        parent.addView(C, index);
    }

}