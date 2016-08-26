package com.teamone.beautality.activities.owner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamone.beautality.R;
import com.teamone.beautality.activities.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshhepkov on 21.08.16.
 */
public class OwnerServicesFragment extends BaseFragment {
    private RecyclerView mRVServiceList;
    private RecyclerViewAdapter mServiceAdapter;
    private List<String> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_owner_services, container, false);

        mServiceAdapter = new RecyclerViewAdapter();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mRVServiceList = (RecyclerView) root.findViewById(R.id.rv_services);
        mRVServiceList.setAdapter(mServiceAdapter);
        mRVServiceList.setLayoutManager(mLayoutManager);

        mServiceAdapter.set(mList);

        return root;
    }
    public OwnerServicesFragment setList(List<String> list) {
        this.mList = new ArrayList<>(list);
        return this;
    }
    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        List<String> mList = new ArrayList<>();
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_owner_service_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public void add(String service) {
            mList.add(service);
            notifyDataSetChanged();
        }
        public void set(List<String> data) {
            mList = new ArrayList<>(data);
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTVTitle, mTVPrice;
            public ViewHolder(View itemView) {
                super(itemView);
                mTVTitle = (TextView) itemView.findViewById(R.id.tv_title);
                mTVPrice = (TextView) itemView.findViewById(R.id.tv_price);
            }
            public void bind(String service) {
                mTVTitle.setText(service);
            }
        }
    }
}
