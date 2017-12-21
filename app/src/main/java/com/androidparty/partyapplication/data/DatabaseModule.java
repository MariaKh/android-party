package com.androidparty.partyapplication.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.androidparty.partyapplication.data.dao.ContentDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 1 on 12/20/2017.
 */

@Module
@Singleton
public class DatabaseModule {

    @Provides
    @Singleton
    public AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "partyapplication.db").build();
    }

    @Provides
    @Singleton
    public ContentDao provideContentDao(AppDatabase database) {
        return database.contentDao();
    }

}
