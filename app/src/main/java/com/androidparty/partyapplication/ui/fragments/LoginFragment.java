package com.androidparty.partyapplication.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidparty.partyapplication.R;
import com.androidparty.partyapplication.network.entities.response.ApiError;
import com.androidparty.partyapplication.ui.activities.MainActivity;
import com.androidparty.partyapplication.ui.presentation.contracts.LoginContract;
import com.androidparty.partyapplication.ui.presentation.presenters.LoginPresenter;
import com.androidparty.partyapplication.utils.AppUtils;
import com.androidparty.partyapplication.utils.NetworkUtils;

/**
 * Created by 1 on 12/20/2017.
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    public static final String TAG = LoginFragment.class.getName();

    private EditText mUserName;
    private EditText mPassword;
    private Button mLoginBtn;
    private LinearLayout mLoginContainer;
    private ImageView mLogo;
    private LinearLayout mProgressContainer;
    private ProgressBar mProgress;
    private LoginContract.Presenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initFields(rootView);
        return rootView;
    }

    private void initFields(View rootView) {
        mUserName = rootView.findViewById(R.id.et_username);
        mPassword = rootView.findViewById(R.id.et_password);
        mLoginBtn = rootView.findViewById(R.id.btn_login);
        mLoginContainer = rootView.findViewById(R.id.login_container);
        mProgressContainer = rootView.findViewById(R.id.container_progress);
        mLogo = rootView.findViewById(R.id.logo);
        mProgress = rootView.findViewById(R.id.progress);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPresenter == null) mPresenter = new LoginPresenter();
        mLoginBtn.setOnClickListener(view -> login());
    }

    @Override
    public void onFetchingList() {
        mProgress.setVisibility(View.GONE);
        mLoginContainer.setVisibility(View.GONE);
        mLogo.setVisibility(View.GONE);
        mProgressContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void goToMainScreen() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            getActivity().finish();
        }, 1000);
    }

    @Override
    public void onErrorResponse(ApiError error) {
        Toast.makeText(getContext(), error.getError(), Toast.LENGTH_SHORT).show();
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.attachView(this);
    }

    @Override
    public void onPause() {
        mPresenter.detachView(this);
        super.onPause();
    }

    @Override
    public boolean validateLoginFields(String userName, String password) {
        return !TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password);
    }

    @Override
    public void login() {
        if (validateLoginFields(mUserName.getText().toString(), mPassword.getText().toString())) {
            AppUtils.hideKeyboard(getContext());
            if (NetworkUtils.isOnline(getContext())) {
                mProgress.setVisibility(View.VISIBLE);
                mPresenter.login(mUserName.getText().toString(), mPassword.getText().toString());
            } else {
                Toast.makeText(getContext(), getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), getString(R.string.error_fill_fields), Toast.LENGTH_SHORT).show();
        }
    }
}
