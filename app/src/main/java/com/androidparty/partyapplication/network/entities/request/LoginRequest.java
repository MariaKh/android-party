package com.androidparty.partyapplication.network.entities.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1 on 12/20/2017.
 */

public class LoginRequest {
    @SerializedName("username")
    @Expose
    private String mUsername;
    @SerializedName("password")
    @Expose
    private String mPassword;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.mUsername = username;
        this.mPassword = password;
    }
}
