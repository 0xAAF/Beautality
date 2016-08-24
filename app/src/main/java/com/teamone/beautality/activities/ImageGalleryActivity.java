package com.teamone.beautality.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.teamone.beautality.R;
import com.teamone.beautality.utils.custom_ui.ImageGalleryViewPager;
import com.teamone.beautality.utils.custom_ui.TouchImageView;

import java.util.List;

/**
 * Created by oshhepkov on 22.08.16.
 */
public class ImageGalleryActivity extends BaseActivity {
    public static final String
            BUNDLE_KEY_GALLERY_PROJECTNAME = "galleryName",
            BUNDLE_KEY_GALLERY_POSITION= "galleryPosition",
            BUNDLE_KEY_GALLERY_IMAGES = "galleryImages";

    private ImageGalleryViewPager mVPGallery;
    private GalleryPagerAdapter mGalleryAdapter;
    private String mProjectName="";
    private int mPosition;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        List<String> images = null;

        if (getIntent().hasExtra(BUNDLE_KEY_GALLERY_IMAGES)) {
            images = getIntent().getStringArrayListExtra(BUNDLE_KEY_GALLERY_IMAGES);
        } else {
            finish();
        }
        if (getIntent().hasExtra(BUNDLE_KEY_GALLERY_PROJECTNAME)) {
            mProjectName = getIntent().getStringExtra(BUNDLE_KEY_GALLERY_PROJECTNAME);
        }
        if (getIntent().hasExtra(BUNDLE_KEY_GALLERY_POSITION)) {
            mPosition = getIntent().getIntExtra(BUNDLE_KEY_GALLERY_POSITION, 0);
        }

        mGalleryAdapter = new GalleryPagerAdapter(this, images);
        mVPGallery = (ImageGalleryViewPager) findViewById(R.id.vp_gallery);
        mVPGallery.setAdapter(mGalleryAdapter);
        mVPGallery.setCurrentItem(mPosition);
        mVPGallery.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitlePositionSubstring(position+1, mGalleryAdapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitle(mProjectName);
        setTitlePositionSubstring(mPosition+1, mGalleryAdapter.getCount());
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setTitlePositionSubstring(int position, int max) {
        mToolbar.setSubtitle(position+" "+getString(R.string.activity_gallery_position_of)+" "+max);
    }
    private class GalleryPagerAdapter extends PagerAdapter {
        Context mContext;
        List<String> mImages;
        public TouchImageView mCurrentView;

        public GalleryPagerAdapter(Context context, List<String> images) {
            this.mContext = context;
            mImages = images;
        }

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public Object instantiateItem(ViewGroup collection, final int position) {
            mCurrentView = new TouchImageView(mContext);
            Picasso.with(mContext)
                    .load((mImages.get(position)))
                    .into(mCurrentView);
            collection.addView(mCurrentView, 0);
            return mCurrentView;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view){
            collection.removeView((View) view);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}