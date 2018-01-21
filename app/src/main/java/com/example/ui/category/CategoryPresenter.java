/*
 * Copyright (C) VijayK
 */

package com.example.ui.category;

import android.content.Context;

import com.example.data.DataManager;
import com.example.ui.base.BasePresenter;

/**
 * Presenter class for {@link CategoryFragment} view.
 */
public class CategoryPresenter extends BasePresenter {

    CategoryContract mContract;

    public CategoryPresenter(DataManager dataManager) {
        super(dataManager);
    }

    /**
     * Attaches the presenter to given view.
     *
     * @param context  context for view to populate its data.
     * @param contract Contract to communicate with view.
     */
    public void onAttach(Context context, CategoryContract contract) {
        super.onAttach(contract);
        mContract = contract;
        contract.addToActivity(context);
    }

    /**
     * Loads the category images. Call will be forwarded to {@link DataManager} which will decided where to get the data ( from cache,
     * or sqlite or from network).
     */
    public void loadCategoryImages() {
        mDataManager.getCategories((data, status) -> mContract.populateCategory(data));
    }

    /**
     * Records click for given url. Call will be forwarded to Tracking module in the end.
     */
    public void recordClick(String url) {
        mDataManager.trackClick(url);
    }
}
