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

import com.androidparty.partyapplication.Application;
import com.androidparty.partyapplication.data.PreferencesRepository;
import com.androidparty.partyapplication.R;
import com.androidparty.partyapplication.data.AppRepository;
import com.androidparty.partyapplication.network.entities.request.LoginRequest;
import com.androidparty.partyapplication.ui.activities.MainActivity;
import com.androidparty.partyapplication.utils.NetworkUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 1 on 12/20/2017.
 */

public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getName();

    private EditText mUserName;
    private EditText mPassword;
    private Button mLoginBtn;
    private LinearLayout mLoginContainer;
    private ImageView mLogo;
    private LinearLayout mProgressContainer;
    private ProgressBar mProgress;
    @Inject
    AppRepository appRepository;
    @Inject
    PreferencesRepository preferencesRepository;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Application.getAppComponent().inject(this);
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        mUserName = rootView.findViewById(R.id.et_username);
        mPassword = rootView.findViewById(R.id.et_password);
        mLoginBtn = rootView.findViewById(R.id.btn_login);
        mLoginContainer = rootView.findViewById(R.id.login_container);
        mProgressContainer = rootView.findViewById(R.id.container_progress);
        mLogo = rootView.findViewById(R.id.logo);
        mProgress = rootView.findViewById(R.id.progress);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLoginBtn.setOnClickListener(view -> {
            if (validateFields()) {
                if (NetworkUtils.isOnline(getContext())) {
                    appRepository.login(new LoginRequest(mUserName.getText().toString(), mPassword.getText().toString()))
                            .subscribeOn(Schedulers.io())
                            .doOnSubscribe(d -> mProgress.setVisibility(View.VISIBLE))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response -> {
                                mProgress.setVisibility(View.GONE);
                                preferencesRepository.setToken(response.getToken());
                                fetchList();
                            }, throwable -> {
                                Toast.makeText(getContext(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            });
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), getString(R.string.error_fill_fields), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateFields() {
        return !TextUtils.isEmpty(mUserName.getText().toString()) && !TextUtils.isEmpty(mPassword.getText().toString());
    }

    private void fetchList() {
        if (NetworkUtils.isOnline(getContext())) {
            appRepository.getList()
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(d -> {
                        mLoginContainer.setVisibility(View.GONE);
                        mLogo.setVisibility(View.GONE);
                        mProgressContainer.setVisibility(View.VISIBLE);
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        new Handler().postDelayed(() -> {
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                            getActivity().finish();
                        }, 1000);

                    }, throwable -> {
                        Toast.makeText(getContext(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(getContext(), getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show();
        }
        mLoginContainer.setVisibility(View.GONE);
        mLogo.setVisibility(View.GONE);
        mProgressContainer.setVisibility(View.VISIBLE);
    }
}
