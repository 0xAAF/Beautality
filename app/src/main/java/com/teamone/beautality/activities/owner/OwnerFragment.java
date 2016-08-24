package com.teamone.beautality.activities.owner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teamone.beautality.R;
import com.teamone.beautality.activities.BaseActivity;
import com.teamone.beautality.activities.ImageGalleryActivity;
import com.teamone.beautality.utils.PicassoRoundedCornersTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshhepkov on 20.08.16.
 */

public class OwnerFragment extends Fragment {
    private Button mBTServices;
    private BaseActivity mBaseActivity;
    private TextView mTVPhoneNumber, mTVAddress, mTVTitle;
    private ImageView mIVMap, mIVLogo;
    private String mPhoneNumber;
    private LinearLayout LLGallery;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseActivity = (BaseActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_owner, container, false);

        mTVTitle = (TextView) root.findViewById(R.id.tv_title);

        mTVPhoneNumber = (TextView) root.findViewById(R.id.tv_tel);
        mTVPhoneNumber.setOnClickListener(mOnPhoneClickListener);
        mTVPhoneNumber.setPaintFlags(mTVPhoneNumber.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        mTVAddress = (TextView) root.findViewById(R.id.tv_address);

        mIVLogo = (ImageView) root.findViewById(R.id.iv_logo) ;
        mIVMap = (ImageView) root.findViewById(R.id.iv_map) ;

        LLGallery = (LinearLayout) root.findViewById(R.id.ll_gallery);
        mBTServices = (Button) root.findViewById(R.id.bt_services);
        mBTServices.setOnClickListener(mOnServicesClickListener);

        //DEBUG
         Handler handler = new Handler();
         handler.postDelayed(content, 500);

        return root;

    }

    public Fragment setServicesClickListener(View.OnClickListener clickListener) {
        mOnServicesClickListener = clickListener;
        return this;
    }

    public void getContent() {
        //TODO: Retrofit callback here
        final List<String> photos = new ArrayList<>();
        photos.add("http://www.intrawallpaper.com/static/images/2a5y2aru.jpg");
        photos.add("http://www.planwallpaper.com/static/images/HD-Wallpapers1.jpeg");
        photos.add("http://www.planwallpaper.com/static/images/HD-Wallpapers1.jpeg");
        photos.add("http://www.planwallpaper.com/static/images/HD-Wallpapers1.jpeg");
        photos.add("http://www.planwallpaper.com/static/images/HD-Wallpapers1.jpeg");
        photos.add("http://www.wallisme.com/wp-content/uploads/2016/01/Space-4K-wallppers.jpg");

        String mTitle = "Company Name",
                mPhoneNumber = "+8(905) 555 5555",
                mAddress = "Volgograd, mira 55";

        String mapUrl = "https://maps.googleapis.com/maps/api/staticmap?" +
                "center=" + mAddress + "&" +
                "size=" + mIVMap.getWidth() + "x" + mIVMap.getHeight();
        mapUrl = mapUrl.replace(" ","%20");
        Log.e("APP","Map url: "+ mapUrl);

        Picasso.with(mBaseActivity).load(mapUrl).into(mIVMap);

        this.mPhoneNumber = mPhoneNumber;

        mBaseActivity.mToolbar.setTitle(mTitle);

        mTVTitle.setText(mTitle);
        mTVPhoneNumber.setText(mPhoneNumber);
        mTVAddress.setText(mAddress);

        for (int i=0;i<photos.size();i++) {
            ImageView image = new ImageView(mBaseActivity);
            image.setLayoutParams(new LinearLayout.LayoutParams(0,LLGallery.getWidth()/3,1));
            image.setPadding(1,1,1,1);
            Picasso.with(mBaseActivity)
                    .load(photos.get(i))
                    .transform(new PicassoRoundedCornersTransform(12, PicassoRoundedCornersTransform.Corners.ALL))
                    .fit().centerCrop()
                    .into(image);
            final int page = i;
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startGalleryActivity(mBaseActivity,photos,page);
                }
            });
            LLGallery.addView(image);
            if (i>=2) break;
        }

    }
    Runnable content = new Runnable() {
        @Override
        public void run() {
            getContent();
        }
    };
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