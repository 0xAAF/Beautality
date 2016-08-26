package com.teamone.beautality.activities.owner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.teamone.beautality.R;
import com.teamone.beautality.activities.BaseFragment;
import com.teamone.beautality.activities.ImageGalleryActivity;
import com.teamone.beautality.models.response.ListItemResponse;
import com.teamone.beautality.utils.PicassoRoundedCornersTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshhepkov on 20.08.16.
 */

public class OwnerFragment extends BaseFragment {
    private Button mBTServices;
    private TextView mTVPhoneNumber, mTVAddress, mTVTitle, mDescription, mTVTime;
    private ImageView mIVMap, mIVLogo;
    private String mPhoneNumber;
    private LinearLayout LLGallery,LLGalleryBlock;
    private ListItemResponse mItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_owner, container, false);

        mTVTitle = (TextView) root.findViewById(R.id.tv_title);
        mDescription = (TextView) root.findViewById(R.id.tv_subtitle) ;

        mTVPhoneNumber = (TextView) root.findViewById(R.id.tv_tel);
        mTVPhoneNumber.setOnClickListener(mOnPhoneClickListener);
        mTVPhoneNumber.setPaintFlags(mTVPhoneNumber.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        mTVAddress = (TextView) root.findViewById(R.id.tv_address);
        mTVTime =  (TextView) root.findViewById(R.id.tv_time);
        mIVLogo = (ImageView) root.findViewById(R.id.iv_logo) ;
        mIVMap = (ImageView) root.findViewById(R.id.iv_map) ;
        LLGallery = (LinearLayout) root.findViewById(R.id.ll_gallery);
        LLGalleryBlock = (LinearLayout) root.findViewById(R.id.ll_gallery_block);

        mBTServices = (Button) root.findViewById(R.id.bt_services);
        mBTServices.setOnClickListener(mOnServicesClickListener);


        getContent();
        return root;

    }

    public OwnerFragment setItem(ListItemResponse item) {
        mItem = item;
        return this;
    }
    public void getContent() {
        final List<String> photos = mItem.getPhotos();

        String  mPhoneNumber = mItem.getPhone(),
                mAddress = mItem.getCity()+", "+mItem.getAddress();

        String mapUrl = "https://maps.googleapis.com/maps/api/staticmap?" +
                "center=" + mAddress + "&" +
                "size=" + getResources().getDisplayMetrics().widthPixels + "x"+ (getResources().getDisplayMetrics().widthPixels/2);
        mapUrl = mapUrl.replace(" ","%20");

        if (mItem.getServices().size() > 0) {
            mBTServices.setVisibility(View.VISIBLE);
        } else {
            mBTServices.setVisibility(View.GONE);
        }
        this.mPhoneNumber = mPhoneNumber;
        mTVTitle.setText(mItem.getTitle());
        mDescription.setText(mItem.getDescription());
        mTVPhoneNumber.setText(mPhoneNumber);
        mTVAddress.setText(mAddress);
        mTVTime.setText(mItem.getTime());

        Picasso.with(mBaseActivity).load(mapUrl).into(mIVMap, new Callback() {
            @Override
            public void onSuccess() {
                mIVMap.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                mIVMap.setVisibility(View.GONE);
            }
        });
        Picasso.with(mBaseActivity).load(mItem.getLogo()).into(mIVLogo);
        int width = (getResources().getDisplayMetrics().widthPixels - 1) / 3;
    if (photos.size() > 0) {
        LLGalleryBlock.setVisibility(View.VISIBLE);
        for (int i = 0; i < photos.size(); i++) {
            final int page = i;

            ImageView image = new ImageView(mBaseActivity);

            image.setLayoutParams(new LinearLayout.LayoutParams(width, width));
            image.setPadding(1, 1, 1, 1);
            Picasso.with(mBaseActivity)
                    .load(photos.get(i))
                    .transform(new PicassoRoundedCornersTransform(12, PicassoRoundedCornersTransform.Corners.ALL))
                    .fit().centerCrop()
                    .into(image);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startGalleryActivity(mBaseActivity, photos, page);
                }
            });
            LLGallery.addView(image);
            if (i >= 2) break;
        }
    } else {
        LLGalleryBlock.setVisibility(View.GONE);
    }

    }
    View.OnClickListener mOnPhoneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPhoneNumber));
            startActivity(intent);
        }
    };

    View.OnClickListener mOnServicesClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mBaseActivity.startFragment(new OwnerServicesFragment().setList(mItem.getServices()), true);
        }
    };

    private void startGalleryActivity(Context context, List<String> images, int startPosition) {
        Intent intent = new Intent(context, ImageGalleryActivity.class);
        intent.putStringArrayListExtra(ImageGalleryActivity.BUNDLE_KEY_GALLERY_IMAGES, (ArrayList<String>) images);
        intent.putExtra(ImageGalleryActivity.BUNDLE_KEY_GALLERY_PROJECTNAME, mTVTitle.getText());
        intent.putExtra(ImageGalleryActivity.BUNDLE_KEY_GALLERY_POSITION, startPosition);
        startActivity(intent);
    }

}