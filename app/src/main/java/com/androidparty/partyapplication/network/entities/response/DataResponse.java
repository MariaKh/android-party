package com.androidparty.partyapplication.network.entities.response;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by 1 on 12/20/2017.
 */
@Entity(tableName = DataResponse.Table.TABLE_NAME)
public class DataResponse {
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = DataResponse.Table.COLUMN_SERVER)
    @NonNull
    @PrimaryKey
    private String name;
    @SerializedName("distance")
    @Expose
    @ColumnInfo(name = DataResponse.Table.COLUMN_DISTANCE)
    @NonNull
    private int distance;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public int getDistance() {
        return distance;
    }

    public void setDistance(@NonNull int distance) {
        this.distance = distance;
    }

    public class Table {

        public static final String TABLE_NAME = "content";

        public static final String COLUMN_SERVER = "server";
        public static final String COLUMN_DISTANCE = "distance";
    }
}
