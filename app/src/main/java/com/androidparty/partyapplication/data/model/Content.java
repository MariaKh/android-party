package com.androidparty.partyapplication.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.androidparty.partyapplication.network.entities.response.DataResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1 on 1/3/2018.
 */
@Entity(tableName = Content.Table.TABLE_NAME)
public class Content {

    @ColumnInfo(name = Table.COLUMN_SERVER)
    @PrimaryKey
    @NonNull
    private String mName;

    @ColumnInfo(name = Table.COLUMN_DISTANCE)
    private int mDistance;

    public Content(@NonNull String name, int distance){
        this.mName = name;
        this.mDistance = distance;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        this.mName = name;
    }

    public int getDistance() {
        return mDistance;
    }

    public void setDistance(int distance) {
        this.mDistance = distance;
    }

    public class Table {

        public static final String TABLE_NAME = "content";

        public static final String COLUMN_SERVER = "server";
        public static final String COLUMN_DISTANCE = "distance";
    }
}
