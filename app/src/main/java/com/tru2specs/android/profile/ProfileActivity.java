package com.tru2specs.android.profile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tru2specs.android.R;
import com.tru2specs.android.address.AddressActivity;
import com.tru2specs.android.basicinfo.BasicInfoActivity;
import com.tru2specs.android.customview.CustomEditText;
import com.tru2specs.android.login.LoginActivity;
import com.tru2specs.android.manager.SessionManager;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.objects.responses.loginSignup.LoginSignUpResponse;
import com.tru2specs.android.objects.responses.loginSignup.User;
import com.tru2specs.android.profile.presenter.ProfilePresenter;
import com.tru2specs.android.profile.view.IProfileView;
import com.tru2specs.android.signup.presenter.SignUpPresenter;
import com.tru2specs.android.signup.view.ISignUpView;
import com.tru2specs.android.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity implements IProfileView {


    @BindView(R.id.edt_full_name)
    TextView mFullName;
    @BindView(R.id.edt_last_name)
    EditText mLastName;
    @BindView(R.id.edt_signup_email)
    TextView mEmail;
    @BindView(R.id.edt_signup_password)
    EditText mPassword;
    @BindView(R.id.edt_signup_referral)
    EditText mReferralCode;
    @BindView(R.id.edt_signup_contact)
    EditText mContact;
    @BindView(R.id.chk_sign_up_terms)
    CheckBox mCheckTermsAndCondition;
    @BindView(R.id.rb_signup_male)
    RadioButton mMale;
    @BindView(R.id.rb_signup_female)
    RadioButton mFemale;
    @BindView(R.id.rg_signup_gender)
    RadioGroup mGenderGroup;
    @BindView(R.id.img_back)
    ImageView mBack;
    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;
    @BindView(R.id.btn_sign_up)
    Button mSignUp;
    @BindView(R.id.btn_add_a_new_address)
    Button mNewAddress;
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;

    private Calendar mCalendar;
    private ProfilePresenter mPresenter;
    @BindView(R.id.edt_signup_dob)
    CustomEditText mDob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setScreenTitle();
        hideProgressView();
        mPresenter = new ProfilePresenter(this);
        mCalendar = Calendar.getInstance();

        setBasicInfo();
    }

    @Override
    public void setBasicInfo() {
        com.tru2specs.android.objects.responses.loginSignup.Data data =
                new Gson().fromJson(new SessionManager(getApplicationContext()).getUserInfo(), com.tru2specs.android.objects.responses.loginSignup.Data.class);
        if (data != null && data.getUser() != null) {
            User user = data.getUser();
            if (!TextUtils.isEmpty(user.getFullName()))
                mFullName.setText(user.getFullName());

            if (!TextUtils.isEmpty(user.getUserId()))
                mEmail.setText(user.getUserId());

            if (!TextUtils.isEmpty(user.getPhoneNo()))
                mContact.setText(user.getPhoneNo());

            if (!TextUtils.isEmpty(user.getGender())) {
                String gender = user.getGender();
                if (gender.toLowerCase().equals(String.valueOf(mMale.getText()).toLowerCase())) {
                    mMale.setChecked(true);
                    return;
                }

                if (gender.toLowerCase().equals(String.valueOf(mFemale.getText()).toLowerCase())) {
                    mFemale.setChecked(true);
                    return;
                }
            }

            return;
        }

        navigateToLoginActivity();
    }

    @OnClick({R.id.img_back, R.id.btn_sign_up, R.id.btn_add_a_new_address, R.id.edt_signup_dob})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_add_a_new_address:
                navigateToAddAddressActivity();
                break;
            case R.id.btn_sign_up:
                mPresenter.attempUpdateUser();
                break;
            case R.id.edt_signup_dob:
                showDatePicker();
                break;
        }
    }

    private void navigateToAddAddressActivity() {
        Intent addressIntent = new Intent(ProfileActivity.this, AddressActivity.class);
        startActivity(addressIntent);
    }


    //region Calendar related stuff here
    private void showDatePicker() {
        new DatePickerDialog(ProfileActivity.this, dobSetListener, mCalendar
                .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        //TODO:Uncomment below line to display date on textview
        mDob.setText(sdf.format(mCalendar.getTime()));
    }

    private DatePickerDialog.OnDateSetListener dobSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, month);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };


    //endregion


    //region implementation of ISignUpView
    @Override
    public void updateUserFailed(String message) {
        Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUserSuccess(String message, Data data) {
        Toast.makeText(ProfileActivity.this,message,Toast.LENGTH_SHORT).show();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.KEY_LOGIN_SIGNUP_RESPONSE, data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public String getFullName() {
        return String.valueOf(mFullName.getText());
    }

    @Override
    public String getEmail() {
        return String.valueOf(mEmail.getText());
    }

    @Override
    public String getPassword() {
        return String.valueOf(mPassword.getText());
    }

    @Override
    public String getContactNo() {
        return String.valueOf(mContact.getText());
    }

    @Override
    public String getGender() {
        int selectedId = mGenderGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            return "";
        }
        RadioButton btn = (RadioButton) findViewById(selectedId);
        return String.valueOf(btn.getText());
    }

    @Override
    public String getReferralCode() {
        return String.valueOf(mReferralCode.getText());
    }

    @Override
    public String getDob() {
        return String.valueOf(mDob.getText());
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
    public void navigateToLoginActivity() {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mScreenTitle.setText(getString(R.string.title_my_accout));
    }
    //endregion
}
