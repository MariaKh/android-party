package com.androidparty.partyapplication.network.entities.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1 on 12/20/2017.
 */

public class LoginResponse {

    @SerializedName("token")
    @Expose
    private String mToken;

    public String getToken() {
        return mToken;
    }
}
