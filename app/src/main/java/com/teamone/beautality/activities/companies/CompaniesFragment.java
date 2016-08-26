package com.teamone.beautality.activities.companies;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamone.beautality.R;
import com.teamone.beautality.activities.BaseFragment;
import com.teamone.beautality.activities.companies.adapter.CompaniesAdapter;
import com.teamone.beautality.models.request.ListRequest;
import com.teamone.beautality.models.response.ListItemResponse;
import com.teamone.beautality.models.response.ListResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

import de.undercouch.bson4jackson.BsonFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oshhepkov on 20.08.16.
 */

public class CompaniesFragment extends BaseFragment {

    private CompaniesAdapter mCompaniesAdapter;
    private RecyclerView mRVCompaniesList;
    private String mAccessToken;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_companies, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        mCompaniesAdapter = new CompaniesAdapter(getActivity(),mProjectItemClickListener);

        mRVCompaniesList = (RecyclerView) root.findViewById(R.id.rv_companies);
        mRVCompaniesList.setAdapter(mCompaniesAdapter);
        mRVCompaniesList.setLayoutManager(linearLayoutManager);

        SharedPreferences sharedPreferences = mBaseActivity.getSharedPreferences(getString(R.string.key_settings), mBaseActivity.MODE_PRIVATE);
        mAccessToken = sharedPreferences.getString(getString(R.string.key_settings_access_token), "");

        getContent(mAccessToken);

        return root;

    }

    private void getContent(String token) {
         try {
            Call<ListResponse> call = mBaseActivity.getApi().postList(new ListRequest(mBaseActivity.APP, mBaseActivity.CLI, "private_person", token));
            call.enqueue(mListCallBack);
        } catch ( Exception e) {
            e.printStackTrace();
        }
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
        public void onItemClick(ListItemResponse item) {
            Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
    };


    private Callback<ListResponse> mListCallBack = new Callback<ListResponse>() {
        @Override
        public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
             if (response.code() == 200) {
                 byte[] decodedString = Base64.decode(response.body().getResult(), Base64.NO_WRAP);

                ByteArrayInputStream bais = new ByteArrayInputStream(decodedString);
                ObjectMapper mapper = new ObjectMapper(new BsonFactory());
                try {
                    List<ListItemResponse> result = mapper.readValue(bais, List.class);

                    mCompaniesAdapter.set((List<ListItemResponse>)mapper.convertValue(result, new TypeReference<List<ListItemResponse>>() { }));

                } catch (Exception e) {
                    mBaseActivity.showErrorMessage(R.string.alert_error_title, R.string.alert_error_client);
                    //mBaseActivity.replaceView(R.id.rv_companies,R.layout.fragment_nodata_placeholder);
                    e.printStackTrace();
                }

                // mCompaniesAdapter.set(response.body());
            } else if (response.code() >= 400 && response.code() < 500) {
                 mBaseActivity.showErrorMessage(R.string.alert_error_title, R.string.alert_error_client);
            } else {
                mBaseActivity.showErrorMessage(R.string.alert_error_title, R.string.alert_error_server);
            }
        }

        @Override
        public void onFailure(Call<ListResponse> call, Throwable t) {
            mBaseActivity.showErrorMessage(R.string.alert_error_title, R.string.alert_error_server);
        }
    };

}