package com.androidparty.partyapplication.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.androidparty.partyapplication.network.entities.response.DataResponse;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by 1 on 12/20/2017.
 */
@Dao
public interface ContentDao {
    @Insert(onConflict = REPLACE)
    void insertContent(List<DataResponse> content);

    @Query("SELECT * FROM " + DataResponse.Table.TABLE_NAME)
    Flowable<List<DataResponse>> getContent();

    @Query("DELETE FROM " + DataResponse.Table.TABLE_NAME)
    void deleteTable();
}
