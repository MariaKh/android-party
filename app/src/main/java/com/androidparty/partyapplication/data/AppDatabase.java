package com.androidparty.partyapplication.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.androidparty.partyapplication.data.dao.ContentDao;
import com.androidparty.partyapplication.network.entities.response.DataResponse;


/**
 * Created by 1 on 12/20/2017.
 */

@Database(entities = {DataResponse.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContentDao contentDao();
}
