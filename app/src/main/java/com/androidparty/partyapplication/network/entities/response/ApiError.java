package com.androidparty.partyapplication.network.entities.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1 on 1/4/2018.
 */

public class ApiError {

    @Expose
    @SerializedName("code")
    private int mCode;
    @Expose
    @SerializedName("message")
    private String mError;

    public int getStatusCode() {
        return mCode;
    }

    public void setStatusCode(int code) {
        this.mCode = code;
    }

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        this.mError = error;
    }
}
