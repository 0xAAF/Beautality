package com.teamone.beautality.activities.companies.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teamone.beautality.R;
import com.teamone.beautality.activities.BaseActivity;
import com.teamone.beautality.models.response.ListItemResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshhepkov on 20.08.16.
 */

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.ProjectItemViewHolder>{

    private BaseActivity mActivity;
    private List<ListItemResponse> mProjectList;
    private OnItemClickListener mListener;

    public CompaniesAdapter(Activity activity,OnItemClickListener listener){
        this.mActivity = (BaseActivity) activity;
        this.mListener = listener;
        this.mProjectList = new ArrayList<>();
    }

    @Override
    public ProjectItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View cardViewProjectItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_companies_item,parent,false);
        ProjectItemViewHolder projectItemViewHolder = new ProjectItemViewHolder(cardViewProjectItem);
        return projectItemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ProjectItemViewHolder projectItemViewHolderHolder, int position) {
        projectItemViewHolderHolder.bind(mProjectList.get(position), mListener);
    }

    @Override
    public int getItemCount(){
        return mProjectList.size();
    }

    /**
     * add ProjectListItemResponse and update view
     * @param data
     */
    public void add(ListItemResponse data){
        mProjectList.add(data);
        notifyDataSetChanged();
    }

    /**
     * sets ArrayList to adapter
     * @param data
     */
    public void set(List<ListItemResponse>data){
        if(data != null) {
            mProjectList = new ArrayList<>(data);
        }
        notifyDataSetChanged();
    }

    /**
     * View holder for RecyclerView
     */
    protected class ProjectItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTVProjectTitle;
        private ImageView mIVProjectImage;

        private ProjectItemViewHolder(View itemView) {
            super(itemView);
            mTVProjectTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mIVProjectImage = (ImageView) itemView.findViewById(R.id.iv_logo);
        }

        private void bind(final ListItemResponse item, final OnItemClickListener listener) {
            mTVProjectTitle.setText(item.getTitle());

                Picasso.with(mActivity)
                        .load(item.getLogo())
                        .noPlaceholder()
                        .into(mIVProjectImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }
    /**
     * interface on click listener for RecyclerView
     */
    public interface OnItemClickListener {
        void onItemClick(ListItemResponse item);
    }
}