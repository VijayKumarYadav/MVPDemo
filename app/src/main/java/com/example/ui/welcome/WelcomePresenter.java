/*
 * Copyright (C) VijayK
 */

package com.example.ui.welcome;

import android.content.Context;

import com.example.data.DataManager;
import com.example.ui.base.BasePresenter;

/**
 * Presenter class for {@link WelcomeFragment} view.
 */
public class WelcomePresenter extends BasePresenter {

    private WelcomeContract mContract;

    public WelcomePresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void onAttach(final Context context, final WelcomeContract contract) {
        super.onAttach(contract);
        mContract = contract;
        mDataManager.isFirstTimeUser((data, status) -> {
            if (data) {
                contract.startCategoryActivity(context, true);
            } else {
                contract.addToActivity(context);
            }
        });
    }

    void getMostViewImageUrl() {
        mDataManager.getMostViewedUrl((data, status) -> mContract.loadImage(data));
    }
}
