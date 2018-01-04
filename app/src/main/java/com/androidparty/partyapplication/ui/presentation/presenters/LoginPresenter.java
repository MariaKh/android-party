package com.androidparty.partyapplication.ui.presentation.presenters;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.androidparty.partyapplication.Application;
import com.androidparty.partyapplication.R;
import com.androidparty.partyapplication.data.AppRepository;
import com.androidparty.partyapplication.data.PreferencesRepository;
import com.androidparty.partyapplication.network.entities.RetrofitErrorHandler;
import com.androidparty.partyapplication.network.entities.request.LoginRequest;
import com.androidparty.partyapplication.network.entities.response.ApiError;
import com.androidparty.partyapplication.ui.activities.MainActivity;
import com.androidparty.partyapplication.ui.presentation.contracts.LoginContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 1 on 1/3/2018.
 */

public class LoginPresenter implements LoginContract.Presenter {

    @Inject
    AppRepository appRepository;
    @Inject
    PreferencesRepository preferencesRepository;
    private LoginContract.View view;

    public LoginPresenter() {
        Application.getAppComponent().inject(this);
    }

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView(LoginContract.View view) {
        this.view = null;
    }

    @Override
    public void fetchList() {
        appRepository.getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> view.goToMainScreen(), e -> {
                    view.onErrorResponse(RetrofitErrorHandler.handleError(e));
                });
    }

    @Override
    public void login(String userName, String password) {
        appRepository.login(new LoginRequest(userName, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    preferencesRepository.setToken(response.getToken());
                    view.onFetchingList();
                    fetchList();
                }, e -> view.onErrorResponse(RetrofitErrorHandler.handleError(e)));
    }

}
