/*
 * Copyright (C) VijayK
 */

package com.example.tracking.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tracking.data.db.tables.Category;

import java.util.List;

/**
 * Data Access Object for category table.
 */
@Dao
public interface CategoryDao {

    /**
     * Gets all category from table.
     */
    @Query("SELECT * FROM Category")
    List<Category> getCategories();

    /**
     * Gets list of category who have been clicked.
     */
    @Query("SELECT * FROM Category WHERE numberOfClick > 1")
    List<Category> getClickedCategory();

    /**
     * Gets most view category url.
     */
    @Query("SELECT url FROM Category ORDER BY numberOfClick DESC limit 1")
    String getMostViewedCategoryURL();

    /**
     * Delete categories.
     */
    @Query("DELETE FROM Category")
    void deleteCategories();

    /**
     * Update click.
     */
    @Query("UPDATE Category SET numberOfClick = numberOfClick + :click WHERE url = :url")
    void updateClick(String url, int click);

    /**
     * Insert a category object.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    /**
     * Insert a list of category object.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(List<Category> categories);

    /**
     * Update category.
     */
    @Update
    int updateCategory(Category category);
}
