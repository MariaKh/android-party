package com.androidparty.partyapplication.network;


import com.androidparty.partyapplication.network.entities.request.LoginRequest;
import com.androidparty.partyapplication.network.entities.response.DataResponse;
import com.androidparty.partyapplication.network.entities.response.LoginResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 1 on 12/20/2017.
 */

public interface AppRemoteApi {
    /**
     * Login
     */
    @POST("v1/tokens")
    Single<LoginResponse> login(@Body LoginRequest loginData);

    @GET("v1/servers")
    Single<List<DataResponse>> getDataList();
}
