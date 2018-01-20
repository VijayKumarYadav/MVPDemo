/*
 * Copyright (C) VijayK
 */

package com.example.data;

import android.widget.ImageView;

import com.example.tracking.data.db.tables.Category;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * A data module to interact with Data. Wrapper on top of Network & Tracking module.
 */
public interface DataManager {


    /**
     * Callback for operation complete.
     */
    interface CallBack<T> {

        /**
         * On complete callback.
         *
         * @param data   data of operation
         * @param status status of operation
         */
        void onComplete(T data, String status);
    }

    /**
     * Gets if user is first time accessing the application.
     */
    void isFirstTimeUser(CallBack<Boolean> callBack);

    /**
     * Gets the most viewed category url.
     */
    void getMostViewedUrl(CallBack<String> callBack);

    /**
     * Gets the category.
     */
    public void getCategories(final CallBack<List<Category>> callback);

    /**
     * Gets the random images.
     */
    void getImages(CallBack<List<Category>> callBack);

    /**
     * Tracks when user click on category.
     */
    void trackClick(String url);

    /**
     * Load images in given image view. weak reference is required because if in case image view is destroyed,
     * the call will be ignored.
     */
    void loadImages(String url, WeakReference<ImageView> imageView);
}
