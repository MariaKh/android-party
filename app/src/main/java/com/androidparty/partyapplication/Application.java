package com.androidparty.partyapplication;

import android.content.Context;

/**
 * Created by 1 on 12/20/2017.
 */

public class Application extends android.app.Application {

    private static ApplicationComponent appComponent;
    private static final String TAG = Application.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        appComponent.inject(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static ApplicationComponent getAppComponent() {
        return appComponent;
    }
}
