package com.teamone.beautality.activities.owner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamone.beautality.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshhepkov on 21.08.16.
 */
public class OwnerServicesFragment extends Fragment{
    private RecyclerView mRVServiceList;
    private RecyclerViewAdapter mServiceAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_owner_services, container, false);

        mServiceAdapter = new RecyclerViewAdapter();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mRVServiceList = (RecyclerView) root.findViewById(R.id.rv_services);
        mRVServiceList.setAdapter(mServiceAdapter);
        mRVServiceList.setLayoutManager(mLayoutManager);

        getContent();

        return root;
    }

    public void getContent() {
        //TODO: Retrofit callback here

        mServiceAdapter.add("nails", "150 rub");
        mServiceAdapter.add("hair", "500 rub");
    }



    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        List<String> mListNames = new ArrayList<>();
        List<String> mListPrice = new ArrayList<>();
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_owner_service_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(mListNames.get(position), mListPrice.get(position));
        }

        @Override
        public int getItemCount() {
            return mListNames.size();
        }
        public void add(String name, String price) {
            mListNames.add(name);
            mListPrice.add(price);
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTVTitle, mTVPrice;
            public ViewHolder(View itemView) {
                super(itemView);
                mTVTitle = (TextView) itemView.findViewById(R.id.tv_title);
                mTVPrice = (TextView) itemView.findViewById(R.id.tv_price);
            }
            public void bind(String name, String price) {
                mTVTitle.setText(name);
                mTVPrice.setText(price);
            }
        }
    }
}
