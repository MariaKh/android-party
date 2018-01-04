package com.androidparty.partyapplication.data;

import android.util.Log;

import com.androidparty.partyapplication.data.dao.ContentDao;
import com.androidparty.partyapplication.network.ApiToDbMapper;
import com.androidparty.partyapplication.network.AppRemoteApi;
import com.androidparty.partyapplication.network.entities.request.LoginRequest;
import com.androidparty.partyapplication.network.entities.response.DataResponse;
import com.androidparty.partyapplication.network.entities.response.LoginResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 1 on 12/20/2017.
 */

public class AppRepository {

    @Inject
    AppRemoteApi userRemoteApi;

    @Inject
    ContentDao contentDao;

    @Inject
    public AppRepository() {

    }

    public Single<LoginResponse> login(LoginRequest loginRequest) {
        return userRemoteApi.login(loginRequest)
                .subscribeOn(Schedulers.io())
                .map(resp -> resp);
    }

    public Single<List<DataResponse>> getList() {
        return userRemoteApi.getDataList()
                .subscribeOn(Schedulers.io())
                .doOnSuccess(response -> {
                    contentDao.insertContent(ApiToDbMapper.map(response));
                });
    }
}
