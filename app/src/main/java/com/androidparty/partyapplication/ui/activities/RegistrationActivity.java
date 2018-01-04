package com.androidparty.partyapplication.ui.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.androidparty.partyapplication.Application;
import com.androidparty.partyapplication.data.PreferencesRepository;
import com.androidparty.partyapplication.R;
import com.androidparty.partyapplication.ui.fragments.LoginFragment;

import javax.inject.Inject;

public class RegistrationActivity extends AppCompatActivity {

    @Inject
    PreferencesRepository preferencesRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Application.getAppComponent().inject(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_registration);
        navigateToNextScreen();
    }

    private void navigateToNextScreen(){
        if (TextUtils.isEmpty(preferencesRepository.getToken())) {
            if (getSupportFragmentManager().findFragmentByTag(LoginFragment.TAG) == null) {
                Fragment loginFragment = new LoginFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.registration_container, loginFragment, LoginFragment.TAG)
                        .commit();
            }
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
