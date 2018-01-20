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

    public void onAttach(Context context, CategoryContract contract) {
        super.onAttach(contract);
        mContract = contract;
        contract.addToActivity(context);
    }

    public void loadCategoryImages() {
        mDataManager.getCategories((data, status) -> mContract.populateCategory(data));
    }

    public void recordClick(String url) {
        mDataManager.trackClick(url);
    }
}
