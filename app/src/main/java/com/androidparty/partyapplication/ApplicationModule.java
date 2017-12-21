package com.androidparty.partyapplication;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 1 on 12/20/2017.
 */

@Module
@Singleton
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return application;
    }
}