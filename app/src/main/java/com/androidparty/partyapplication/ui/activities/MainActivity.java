package com.androidparty.partyapplication.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.androidparty.partyapplication.Application;
import com.androidparty.partyapplication.data.PreferencesRepository;
import com.androidparty.partyapplication.R;
import com.androidparty.partyapplication.data.dao.ContentDao;
import com.androidparty.partyapplication.ui.fragments.MainFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private ImageButton mLogout;
    @Inject
    ContentDao contentDao;
    @Inject
    PreferencesRepository preferencesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_main);
        Application.getAppComponent().inject(this);
        initFields();
        addFragment(MainFragment.TAG);
    }

    private void initFields(){
        mToolbar = findViewById(R.id.toolbar);
        mLogout = mToolbar.findViewById(R.id.logout);
        mLogout.setOnClickListener(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void addFragment(String tag){
        if (!TextUtils.isEmpty(tag)) {
            if (tag.equals(MainFragment.TAG)) {
                if (getSupportFragmentManager().findFragmentByTag(MainFragment.TAG) == null) {
                    Fragment mainFragment = new MainFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.main_container, mainFragment, MainFragment.TAG)
                            .commit();
                }
            }
        }else {
            throw new NullPointerException("tag cannot be null");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logout:
                new AsyncLogOut().execute();
                break;
        }
    }

    private class AsyncLogOut extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            contentDao.deleteTable();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            preferencesRepository.setToken(null);
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            finish();
        }
    }
}
