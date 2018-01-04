package com.androidparty.partyapplication.network.entities;

import com.androidparty.partyapplication.network.NetworkModule;
import com.androidparty.partyapplication.network.entities.response.ApiError;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Created by 1 on 1/4/2018.
 */

public class RetrofitErrorHandler {
    public static ApiError handleError(Throwable error) {
        ApiError retrofitError = new ApiError();
        if (error instanceof HttpException) {
            HttpException httpError = (HttpException) error;
            try {
                return NetworkModule.gson.fromJson(httpError.response().errorBody().string(), ApiError.class);
            } catch (IOException | IllegalStateException e) {
                e.printStackTrace();
            }
        } else if (error instanceof SocketTimeoutException || error instanceof ConnectException) {
            retrofitError = new ApiError();
            retrofitError.setError("Something went wrong");
        } else throw new RuntimeException(error);
        return retrofitError;

    }
}
