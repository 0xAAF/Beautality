package com.teamone.beautality.activities.companies;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.teamone.beautality.R;
import com.teamone.beautality.activities.BaseActivity;
import com.teamone.beautality.activities.companies.adapter.CompaniesAdapter;
import com.teamone.beautality.models.response.CompaniesItemResponse;

/**
 * Created by oshhepkov on 20.08.16.
 */

public class CompaniesFragment extends Fragment {

    private CompaniesAdapter mCompaniesAdapter;
    private TextView mTVProjectListEmpty;
    private RecyclerView mRVCompaniesList;
    private BaseActivity mBaseActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseActivity = (BaseActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_companies, container, false);

        mTVProjectListEmpty = (TextView) root.findViewById(R.id.tv_title);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        mCompaniesAdapter = new CompaniesAdapter(getActivity(),mProjectItemClickListener);

        mRVCompaniesList = (RecyclerView) root.findViewById(R.id.rv_companies);
        mRVCompaniesList.setAdapter(mCompaniesAdapter);
        mRVCompaniesList.setLayoutManager(linearLayoutManager);
       // getContent();

        for (int i=0;i<10;i++)
        mCompaniesAdapter.add(new CompaniesItemResponse().generate());

        return root;

    }
    public Fragment setOnItemClickListener(CompaniesAdapter.OnItemClickListener clickListener) {
        mProjectItemClickListener = clickListener;
        return this;
    }
    /**
     * Click listener for items in project list
     */
    private CompaniesAdapter.OnItemClickListener mProjectItemClickListener = new CompaniesAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(CompaniesItemResponse item) {
            Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * get content from server, use retrofit2
     * /
    private void getContent() {
        Call<List<CompaniesItemResponse>> call = mBaseActivity.getApi().getProjectList();
        call.enqueue(new Callback<List<CompaniesItemResponse>>() {
            @Override
            public void onResponse(Call<List<CompaniesItemResponse>> call, Response<List<CompaniesItemResponse>> response) {
                if (response.code() == 200) {
                    if (response.body().size() == 0) {
                        mTVProjectListEmpty.setText(R.string.activity_companies_empty);
                        mTVProjectListEmpty.setVisibility(View.VISIBLE);
                        mRVCompaniesList.setVisibility(View.GONE);
                    } else {
                        mCompaniesAdapter.set(response.body());
                        mRVCompaniesList.setVisibility(View.VISIBLE);
                        mTVProjectListEmpty.setVisibility(View.GONE);
                    }
                } else if (response.code() >= 400 && response.code() < 500) {
                  //  mBaseActivity.showErrorMessage(R.string.alert_error_client);
                } else if (response.code() >= 500) {
                  //  mBaseActivity.showErrorMessage(R.string.alert_error_server);
                }
            }

            @Override
            public void onFailure(Call<List<CompaniesItemResponse>> call, Throwable t) {
                if (t instanceof UnknownHostException) {
                  //  mBaseActivity.showErrorMessage(R.string.alert_error_unableToConnect);
                } else  {
                   // mBaseActivity.showErrorMessage(R.string.alert_error_client);
                }
                Log.e("APP",t.getLocalizedMessage(),t);
            }
        });
    }
     /**/
}