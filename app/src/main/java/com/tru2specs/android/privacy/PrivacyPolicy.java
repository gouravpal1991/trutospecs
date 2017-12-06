package com.tru2specs.android.privacy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrivacyPolicy extends BaseActivity {

    private TextView mPolicy;
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.img_back)
    ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        ButterKnife.bind(this);
        setScreenTitle();
        mPolicy = (TextView) findViewById(R.id.txt_privacy_policy);
        mPolicy.setText(getResources().getString(R.string.privacy_policy));
        mPolicy.setMovementMethod(new ScrollingMovementMethod());
    }

    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.privacy_policy_title));
    }

    @Override
    public void onNetworkChange(boolean isConnected) {

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
