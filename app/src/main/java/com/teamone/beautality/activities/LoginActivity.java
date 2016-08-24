package com.teamone.beautality.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.teamone.beautality.R;
import com.teamone.beautality.models.response.LoginResponse;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String APP = "bc2d3bb69ff9e36b25fc78d94db1e94d";
    private static final String CLI = "2733ccbef86c7ebbdab2c7593a68bf88";


    private Button mBtnSignIn;
    private EditText mETEmail;
    private EditText mETPassword;
    private TextInputLayout mTilEmail;
    private TextInputLayout mTilPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mBtnSignIn = (Button) findViewById(R.id.btn_login_signIn);
        mETEmail = (EditText) findViewById(R.id.et_login_email);
        mETPassword = (EditText) findViewById(R.id.et_login_password);
        mTilEmail = (TextInputLayout) findViewById(R.id.ti_login_email_input);
        mTilPassword = (TextInputLayout) findViewById(R.id.ti_login_password_input);

        mETEmail.setOnFocusChangeListener(focusChangeListener);
        mETPassword.setOnFocusChangeListener(focusChangeListener);
        mBtnSignIn.setOnClickListener(mClickBtnLoginListener);


        mETEmail.setText("a32sdf@domain.zone");
        mETPassword.setText("123456");
    }

    private boolean formIsValid() {
        String emailFromET = mETEmail.getText().toString().trim();
        String passwordFromET = mETPassword.getText().toString().trim();

        boolean emailIsValid = true;
        boolean passwordIsValid = true;

        if(!isValidEmail(emailFromET)) {
            emailIsValid = false;
            mTilEmail.setError(getString(R.string.activity_login_email_error));
            if (mETEmail.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        } else {
            mTilEmail.setErrorEnabled(false);
        }

        if(passwordFromET.isEmpty() || passwordFromET.length() < 4) {
            passwordIsValid = false;
            mTilPassword.setError(getString(R.string.activity_login_password_error));
            if (mETPassword.requestFocus()) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        } else {
            mTilPassword.setErrorEnabled(false);
        }

        if (emailIsValid == false || passwordIsValid == false) {
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void saveAccessToken(String access_token) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.key_settings), MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.key_settings_access_token), access_token);
        editor.commit();
    }

    /**
     * Функция для шифрования пароля
     * @param s
     * @return
     */
    public String MD5_Hash(String s) {
        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.update(s.getBytes(),0,s.length());
        String hash = new BigInteger(1, m.digest()).toString(16);
        return hash;
    }

    private View.OnClickListener mClickBtnLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String emailFromET = mETEmail.getText().toString().trim();
            String passwordFromET = mETPassword.getText().toString().trim();

            // передать это вместо пароля
            String encryptedPassword = MD5_Hash(passwordFromET);

            if (formIsValid()) {
                Call<LoginResponse> call = getApi().getLogin(APP,CLI,emailFromET, passwordFromET);
                call.enqueue(mCallBack);
            }
        }
    };

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (!hasFocus) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    };

    private Callback<LoginResponse> mCallBack = new Callback<LoginResponse>() {
        @Override
        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
            try {
                if(response.code() == 200) {
                   // saveAccessToken(response.body().getAccessToken().toString());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("APP", "Server response ("+response.code()+"): ");
                    showErrorMessage(R.string.alert_error_title,R.string.alert_error_server);
                }
            } catch (Exception exception) {
                Log.e(TAG, exception.getMessage(), exception);
            }
        }

        @Override
        public void onFailure(Call<LoginResponse> call, Throwable t) {
            showErrorMessage(R.string.alert_error_title,R.string.alert_error_unableToConnect);
            Log.e("APP",t.getLocalizedMessage());
            t.printStackTrace();
        }
    };
}