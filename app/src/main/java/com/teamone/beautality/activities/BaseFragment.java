package com.teamone.beautality.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by oshhepkov on 26.08.16.
 */
public class BaseFragment extends Fragment {
    protected BaseActivity mBaseActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseActivity = (BaseActivity)getActivity();
    }

}
