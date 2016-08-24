package com.teamone.beautality.activities.companies.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamone.beautality.R;
import com.teamone.beautality.activities.BaseActivity;
import com.teamone.beautality.models.response.CompaniesItemResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshhepkov on 20.08.16.
 */

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.ProjectItemViewHolder>{

    private BaseActivity mActivity;
    private List<CompaniesItemResponse> mProjectList;
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
    public void add(CompaniesItemResponse data){
        mProjectList.add(data);
        notifyDataSetChanged();
    }

    /**
     * sets ArrayList to adapter
     * @param data
     */
    public void set(List<CompaniesItemResponse>data){
        if(data != null) {
            mProjectList = new ArrayList<>(data);
        }
        notifyDataSetChanged();
    }

    /**
     * View holder for RecyclerView
     */
    protected class ProjectItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTVProjectTitle,mTVOwnerName;
        private ImageView mIVProjectImage, mIVOwnerImage;

        private ProjectItemViewHolder(View itemView) {
            super(itemView);
            mTVProjectTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTVOwnerName = (TextView) itemView.findViewById(R.id.tv_owner);
            mIVProjectImage = (ImageView) itemView.findViewById(R.id.iv_project);
            mIVOwnerImage = (ImageView) itemView.findViewById(R.id.iv_owner);
        }

        private void bind(final CompaniesItemResponse item, final OnItemClickListener listener) {
            mTVProjectTitle.setText(item.getTitle());
            String name ="";

            if (!item.getOwner().getFirstName().equals(null)) {
                name+=item.getOwner().getFirstName();
            }
            if (!item.getOwner().getLastName().equals(null)) {
                name+=" "+item.getOwner().getLastName();
            }
            mTVOwnerName.setText(name);

            /*
            if (item.getOwner().getAvatar() != null) {
                Picasso.with(mActivity)
                        .load(mActivity.mApiService.getPictureUrl( item.getOwner().getAvatar()) )
                        .transform(new PicassoCircleTransform())
                        .into(mIVOwnerImage);
            }

            if (item.getPictures().size() > 0) {
                Picasso.with(mActivity)
                        .load(mActivity.mApiService.getPictureUrl( item.getPictures().get(0)) )
                        .noPlaceholder()
                        .into(mIVProjectImage);
            }
*/
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
        void onItemClick(CompaniesItemResponse item);
    }
}