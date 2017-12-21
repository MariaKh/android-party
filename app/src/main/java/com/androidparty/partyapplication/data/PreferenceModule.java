package com.androidparty.partyapplication.data;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 1 on 12/20/2017.
 */

@Module
@Singleton
public class PreferenceModule {
    @Provides
    @Singleton
    public PreferencesRepository providePreferencesRepository(Context context) {
        return new PreferencesRepository(context);
    }
}
