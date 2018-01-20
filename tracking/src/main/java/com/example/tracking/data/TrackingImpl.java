package com.example.tracking.data;
/*
 * Copyright (C) VijayK
 */

import com.example.tracking.data.db.CategoryDao;
import com.example.tracking.data.db.tables.Category;

import java.util.List;

/**
 * Implementation of tracking.
 */
public class TrackingImpl implements Tracking {

    private static TrackingImpl sTracking;
    private CategoryDao mCategoryDao;

    private TrackingImpl(CategoryDao dao) {
        mCategoryDao = dao;
    }

    public static TrackingImpl getInstance(CategoryDao dao) {
        synchronized (TrackingImpl.class) {
            if (sTracking == null) {
                synchronized (TrackingImpl.class) {
                    sTracking = new TrackingImpl(dao);
                }
            }
            return sTracking;
        }
    }

    @Override
    public boolean isFirstTimeUser() {
        return getClickedCategory().size() == 0;
    }

    @Override
    public String getMostViewUrl() {
        return mCategoryDao.getMostViewedCategoryURL();
    }

    @Override
    public List<Category> getCategories() {
        return mCategoryDao.getCategories();
    }

    @Override
    public List<Category> getClickedCategory() {
        return mCategoryDao.getClickedCategory();
    }

    @Override
    public void deleteAll() {
        mCategoryDao.deleteCategories();
    }

    @Override
    public void insertCategories(List<Category> categories) {
        mCategoryDao.insertCategories(categories);
    }

    @Override
    public void trackClick(String url) {
        mCategoryDao.updateClick(url, 1);
    }

    /**
     * Dispose instance.
     */
    public void disposeInstance() {
        sTracking = null;
    }
}
