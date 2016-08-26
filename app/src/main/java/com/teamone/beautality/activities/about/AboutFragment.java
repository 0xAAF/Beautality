package com.teamone.beautality.activities.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamone.beautality.R;
import com.teamone.beautality.activities.BaseFragment;

/**
 * Created by oshhepkov on 25.08.16.
 */
public class AboutFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        mBaseActivity.mToolbar.setTitle(R.string.nav_about);
        return root;
    }
}
