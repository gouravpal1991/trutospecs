package com.tru2specs.android.threed;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.superunitystudio.glasses.UnityPlayerActivity;
import com.tru2specs.android.R;

/**
 * Created by palgour on 11/19/17.
 */

public class ThreeDTryOnActivity extends UnityPlayerActivity implements View.OnClickListener {
    LinearLayout mTryOnView;

    ImageView mBack;

    TextView mListingTitle;
    TextView mScreenTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_three_d_try_on);
        //mContext = this;
//        ButterKnife.bind(this);
        mBack = (ImageView) findViewById(R.id.img_back);
        mScreenTitle = (TextView) findViewById(R.id.txt_pre_login_toolbar_title);
        mListingTitle = (TextView) findViewById(R.id.txt_product_listing_title);
        mTryOnView = (LinearLayout) findViewById(R.id.rl_three_d_view);
        mTryOnView.setVisibility(View.GONE);
        setToolbar();
        showThree3dView();
        mBack.setOnClickListener(this);

    }

    private void showThree3dView() {
       /* mUnityPlayer = new UnityPlayer(mContext);
        int glesMode = mUnityPlayer.getSettings().getInt("gles_mode", 1);
        boolean trueColor8888 = false;
        mUnityPlayer.init(glesMode, trueColor8888);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mTryOnView = (LinearLayout) findViewById(R.id.rl_three_d_view);
        mTryOnView.setVisibility(View.VISIBLE);
        mTryOnView.addView(mUnityPlayer.getView(), 0, layoutParams);*/
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    public void setToolbar() {
//        mStore.setVisibility(View.GONE);
//        mAppTitle.setVisibility(View.GONE);
        mBack.setVisibility(View.VISIBLE);
        mScreenTitle.setVisibility(View.VISIBLE);
        // mListingTitle.setVisibility(View.VISIBLE);
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText("Tru2Specs");
        mBack.setOnClickListener(this);
        //mListingTitle.setText("men");
//        mScreenTitle.setText("men");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
        }
    }
}
