package com.androidparty.partyapplication.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by 1 on 12/20/2017.
 */

public class PreferencesRepository {

    private final String TOKEN = "token";

    private SharedPreferences mPreferences;

    public PreferencesRepository(Context context) {
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setToken(String token) {
        mPreferences.edit().putString(TOKEN, token).apply();
    }

    public String getToken() {
        return mPreferences.getString(TOKEN, null);
    }
}
