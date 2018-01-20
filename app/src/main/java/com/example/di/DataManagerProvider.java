/*
 * Copyright (C) VijayK
 */

package com.example.di;

import android.content.Context;

import com.example.data.DataManagerImpl;
import com.example.data.network.Network;
import com.example.data.network.NetworkImpl;
import com.example.tracking.data.Tracking;
import com.example.tracking.data.TrackingImpl;
import com.example.tracking.data.db.CategoryDatabase;

/**
 * Gets the data manager instance. Responsible to create & inject the dependencies.
 */
public class DataManagerProvider {

    /**
     * Gets the {@link com.example.data.DataManager}.
     */
    public static DataManagerImpl provideDataManager(Context context) {
        Tracking tracking = TrackingImpl.getInstance(CategoryDatabase.getInstance(context).categoryDao());
        Network network = NetworkImpl.getInstance();
        return DataManagerImpl.getInstance(tracking, network);
    }
}
