package com.tru2specs.android.dashboard.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tru2specs.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by GP00471911 on 24-03-2017.
 */

public class DashboardOfferPagerFragment extends Fragment {
    public final static String SLIDER_IMAGE_ID = "image_id";
    @BindView(R.id.img_service_details_header)
    ImageView mSliderImage;
    Unbinder unbinder;
    private String mId;

    public static DashboardOfferPagerFragment getInstance(String imageUrl) {
        DashboardOfferPagerFragment fragment = new DashboardOfferPagerFragment();
        Bundle args = new Bundle();
        args.putString(SLIDER_IMAGE_ID, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(SLIDER_IMAGE_ID)) {
            mId = getArguments().getString(SLIDER_IMAGE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard_offers_pager_header, container, false);
        unbinder = ButterKnife.bind(this, v);
        setImage();
        return v;
    }

    private void setImage() {
        Glide.with(getActivity()).load(mId)
                .crossFade()
                .error(R.drawable.no_image)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mSliderImage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
