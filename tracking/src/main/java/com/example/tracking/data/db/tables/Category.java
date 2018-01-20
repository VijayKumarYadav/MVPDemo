/*
 * Copyright (C) VijayK
 */

package com.example.tracking.data.db.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Immutable model class for a Category.
 */
@Entity(tableName = "category")
public final class Category implements Serializable {

    @Nullable
    @ColumnInfo(name = "title")
    private final String mTitle;

    @ColumnInfo(name = "url")
    @NonNull
    @PrimaryKey
    private final String mUrl;

    @ColumnInfo(name = "numberOfClick")
    private final int mNumberOfClick;

    @ColumnInfo(name = "dateTaken")
    private final String mDateTaken;

    /**
     * Category constructor.
     */
    public Category(@Nullable String title, @NonNull String url, int numberOfClick, String dateTaken) {
        mTitle = title;
        mUrl = url;
        this.mNumberOfClick = numberOfClick;
        this.mDateTaken = dateTaken;
    }

    @NonNull
    public String getUrl() {
        return mUrl;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    public int getNumberOfClick() {
        return mNumberOfClick;
    }

    public String getDateTaken() {
        return mDateTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return mUrl.equals(category.mUrl) && mDateTaken.equals(category.mDateTaken);
    }

    @Override
    public int hashCode() {
        return 31 * mUrl.hashCode();
    }

    @Override
    public String toString() {
        return "Category with url " + mUrl;
    }
}
