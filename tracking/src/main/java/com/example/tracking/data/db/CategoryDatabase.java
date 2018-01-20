/*
 * Copyright (C) VijayK
 */

package com.example.tracking.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.tracking.data.db.tables.Category;


/**
 * The Room Database that contains the Category table.
 */
@Database(entities = {Category.class}, version = 1)
public abstract class CategoryDatabase extends RoomDatabase {

    private static CategoryDatabase INSTANCE;

    public abstract CategoryDao categoryDao();

    private static final Object sLock = new Object();

    public static CategoryDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CategoryDatabase.class, "CategoryDatabase.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
