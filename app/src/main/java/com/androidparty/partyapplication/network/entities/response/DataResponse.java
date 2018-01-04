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
public class DataResponse {
    @SerializedName("name")
    @Expose

    @PrimaryKey
    private String mName;
    @SerializedName("distance")
    @Expose
    private int mDistance;

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

}
