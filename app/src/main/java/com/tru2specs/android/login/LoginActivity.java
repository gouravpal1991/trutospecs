package com.tru2specs.android.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.tru2specs.android.R;
import com.tru2specs.android.activity.ResetPasswordActivity;
import com.tru2specs.android.customview.CustomEditText;
import com.tru2specs.android.dashboard.DashboardActivity;
import com.tru2specs.android.login.presenter.LoginPresenter;
import com.tru2specs.android.login.view.ILoginView;
import com.tru2specs.android.objects.request.SignUpRequest;
import com.tru2specs.android.objects.responses.loginSignup.Data;
import com.tru2specs.android.signup.SignUpActivity;
import com.tru2specs.android.signup.listener.OnSignUpFinishedListener;
import com.tru2specs.android.signup.model.SignUpInteractor;
import com.tru2specs.android.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener, ILoginView, OnSignUpFinishedListener {

    /*
    note: windows: keytool -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android
    mac: keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
    reference: http://www.androidhive.info/2014/02/android-login-with-google-plus-account-1/
     */
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    @BindView(R.id.img_back)
    ImageView nBack;
    @BindView(R.id.edt_email)
    CustomEditText mEmail;
    @BindView(R.id.edt_password)
    CustomEditText mPwd;
    @BindView(R.id.btn_login_sign_in)
    Button mSignIn;
    @BindView(R.id.txt_login_forgot_password)
    TextView mForgotPassword;
    @BindView(R.id.btn_google_sign_in)
    Button mGoogleSignIn;
    @BindView(R.id.btn_fb_sign_in)
    Button mFbSignIn;
    @BindView(R.id.txt_login_sign_up)
    TextView mSignUp;
    @BindView(R.id.rl_progress)
    RelativeLayout mProgress;
    @BindView(R.id.txt_pre_login_toolbar_title)
    TextView mScreenTitle;
    private GoogleApiClient mGoogleApiClient;

    private CallbackManager mFbCallBackManager;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        init();
    }



    private void init() {
        setScreenTitle();
        mFbCallBackManager = CallbackManager.Factory.create();
        mPresenter = new LoginPresenter(this);
        hideProgressView();
        mGoogleSignIn = (Button) findViewById(R.id.btn_google_sign_in);
        mFbSignIn = (Button) findViewById(R.id.btn_fb_sign_in);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.google_client_id))
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mGoogleSignIn.setOnClickListener(this);
        mFbSignIn.setOnClickListener(this);
        mForgotPassword.setOnClickListener(this);
        mSignUp.setOnClickListener(this);
        mSignIn.setOnClickListener(this);
        nBack.setOnClickListener(this);

        LoginManager.getInstance().registerCallback(mFbCallBackManager, mResult);

    }

    FacebookCallback<LoginResult> mResult = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(final LoginResult loginResult) {
//            LoginResult result = loginResult;


            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    Log.i("LoginActivity",
                            response.toString());
                    try {
//                        String id = object.getString("id");
//
//                        String name = object.getString("name");
//                        String email = object.getString("email");
//                        String gender = object.getString("gender");
//                        String birthday = object.getString("birthday");


                        SignUpRequest request = new SignUpRequest();
                        request.setFirstName(object.getString("name"));
                        request.setSocialMediaType(Constants.SOCIAL_MEDIA_TYPE_FACEBOOK);
                        request.setEmail(object.getString("email"));
                        request.setIsSocialMedia(Constants.SOCIAL_MEDIA_LOGIN_TRUE);
                        request.setSocialMediaAccessToken(loginResult.getAccessToken().getToken());

                        request.setLoginType(Constants.LOGIN_TYPE_CUSTOMER);
                        request.setPassword("");

                        SignUpInteractor interactor = new SignUpInteractor();
                        interactor.submitSignUp(LoginActivity.this, request);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields",
                    "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            onSignUpFailure(error.getMessage());
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mFbCallBackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            SignUpRequest request = new SignUpRequest();


            request.setFirstName(acct.getDisplayName());
            request.setSocialMediaType(Constants.SOCIAL_MEDIA_TYPE_GOOGLE);
            request.setEmail(acct.getEmail());
            request.setIsSocialMedia(Constants.SOCIAL_MEDIA_LOGIN_TRUE);
            request.setSocialMediaAccessToken(acct.getIdToken());
            request.setLoginType(Constants.LOGIN_TYPE_CUSTOMER);

            SignUpInteractor interactor = new SignUpInteractor();
            interactor.submitSignUp(this, request);

        } else {
            onSignUpFailure(Constants.SOMETHING_WENT_WRONG_MSG);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.btn_login_sign_in:
                mPresenter.attempToLogin();
                break;
            case R.id.btn_google_sign_in:
                googleSignIn();
                break;

            case R.id.btn_fb_sign_in:
                facebookSignIn();
                break;

            case R.id.txt_login_sign_up:
                navigateToSignUp();
                break;

            case R.id.txt_login_forgot_password:
                navigateToForgotPassword();
                break;
        }
    }

    @Override
    public void setScreenTitle() {
        mScreenTitle.setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        mScreenTitle.setText(getString(R.string.title_sign_in));
    }

    @Override
    public String getEmail() {
        return String.valueOf(mEmail.getText());
    }

    @Override
    public String getPassword() {
        return String.valueOf(mPwd.getText());
    }

    @Override
    public void navigateToForgotPassword() {
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void facebookSignIn() {
        LoginManager.getInstance().logInWithReadPermissions(
                this,
                Arrays.asList("user_photos", "email", "user_birthday", "public_profile")
        );
    }

    @Override
    public void showProgressView() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onLoginSuccess(String message, Data data) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.KEY_LOGIN_SIGNUP_RESPONSE, data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onLoginFailed(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void googleSignOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
//                        updateUI(false);
                    }
                });
    }

    @Override
    public void onSignUpSuccess(Data data) {
        hideProgressView();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.KEY_LOGIN_SIGNUP_RESPONSE, data);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onSignUpFailure(String message) {
        hideProgressView();
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
