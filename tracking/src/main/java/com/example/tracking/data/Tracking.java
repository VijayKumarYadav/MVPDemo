/*
 * Copyright (C) VijayK
 */

package com.example.tracking.data;

import com.example.tracking.data.db.tables.Category;

import java.util.List;

/**
 * Interface to interact with tracking module.
 */
public interface Tracking {

    /**
     * Gets if user is first time accessing the application.
     */
    public boolean isFirstTimeUser();

    /**
     * Gets the most viewed category url.
     */
    String getMostViewUrl();

    /**
     * Gets the stored list of category.
     */
    List<Category> getCategories();

    /**
     * Gets the stored list of category who have been clicked.
     */
    List<Category> getClickedCategory();

    /**
     * Delete all from category table.
     */
    void deleteAll();

    /**
     * Insert list of category in category table.
     */
    void insertCategories(List<Category> categories);

    /**
     * Track click of category.
     */
    void trackClick(String url);
}
