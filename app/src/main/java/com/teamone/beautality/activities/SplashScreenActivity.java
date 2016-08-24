package com.teamone.beautality.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.teamone.beautality.R;

public class SplashScreenActivity extends BaseActivity {
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();

        handler.postDelayed(mTaskNextActivity, 2000 );
    }

    private Runnable mTaskNextActivity = new Runnable() {
        @Override
        public void run() {
            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.key_settings), MODE_PRIVATE);
            String accessToken = sharedPreferences.getString(getString(R.string.key_settings_access_token), "");

            Intent activity;

            if (accessToken.isEmpty()) {
                activity = new Intent(SplashScreenActivity.this, LoginActivity.class);
            } else {
                activity = new Intent(SplashScreenActivity.this, MainActivity.class);
            }

            startActivity(activity);
            finish();
        }
    };
}
