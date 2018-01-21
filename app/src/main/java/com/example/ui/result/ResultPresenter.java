/*
 * Copyright (C) VijayK
 */

package com.example.ui.result;

import android.content.Context;

import com.example.data.DataManager;
import com.example.ui.base.BasePresenter;

/**
 * Presenter class for {@link ResultFragment} view.
 */
public class ResultPresenter extends BasePresenter {

    ResultContract mContract;

    public ResultPresenter(DataManager dataManager) {
        super(dataManager);
    }

    /**
     * Attaches the presenter to given view.
     *
     * @param context  context for view to populate its data.
     * @param contract Contract to communicate with view.
     */
    public void onAttach(Context context, ResultContract contract) {
        super.onAttach(contract);
        mContract = contract;
        contract.addToActivity(context);
    }

    /**
     * Loads the random images. Call will be forwarded to {@link DataManager} which will decided where to get the data ( from cache,
     * or from network).
     */
    public void loadImages() {
        mDataManager.getImages((data, status) -> mContract.populateImages(data));
    }
}
