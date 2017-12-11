package com.tru2specs.android.faq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tru2specs.android.R;
import com.tru2specs.android.base.BaseActivity;
import com.tru2specs.android.faq.adapter.FaqAdapter;
import com.tru2specs.android.faq.presenter.FAQPresenter;
import com.tru2specs.android.faq.view.IFaqView;
import com.tru2specs.android.lens.presenter.LensPresenter;
import com.tru2specs.android.objects.responses.faq.Data;
import com.tru2specs.android.objects.responses.faq.Faq;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FAQActivity extends BaseActivity implements IFaqView {
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    @BindView(R.id.img_back)
    ImageView mBack;

    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;
    @BindView(R.id.rv_faqs)
    RecyclerView mAddresses;
    private FAQPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        hideProgressView();
        setScreenTitle();


        mPresenter = new FAQPresenter(getApplicationContext(), this);
        mPresenter.getFAQs();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAddresses.setLayoutManager(mLayoutManager);
        mAddresses.setItemAnimator(new DefaultItemAnimator());
    }

    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.faqs));
    }

    @Override
    public void onNetworkChange(boolean isConnected) {

    }

    @Override
    public void getFaq() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void hideProgressView() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgressView() {
        mProgress.setVisibility(View.VISIBLE);
    }


    @Override
    public void onGetFaqFailure(String msg) {

    }

    @Override
    public void onGetFaqSuccess(Data data) {
        List<Faq> faqList = data.getFaqs();
        FaqAdapter adapter = new FaqAdapter(getApplicationContext(), faqList);
        mAddresses.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        hideProgressView();
    }
    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
