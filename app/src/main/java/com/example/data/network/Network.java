/*
 * Copyright (C) VijayK
 */
package com.example.data.network;

import com.example.tracking.data.db.tables.Category;

import java.util.List;

/**
 * Interface for interaction with network.
 */
public interface Network {

    /**
     * Get Category from network.
     */
    List<Category> getCategories();

    /**
     * Get Images from network.
     */
    List<Category> getImages();
}
