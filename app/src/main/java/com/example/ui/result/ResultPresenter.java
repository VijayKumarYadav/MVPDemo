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

    public void onAttach(Context context, ResultContract contract) {
        super.onAttach(contract);
        mContract = contract;
        contract.addToActivity(context);
    }

    public void loadImages() {
        mDataManager.getImages((data, status) -> mContract.populateImages(data));
    }
}
