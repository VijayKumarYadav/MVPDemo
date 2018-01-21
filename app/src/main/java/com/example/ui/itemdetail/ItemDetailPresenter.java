/*
 * Copyright (C) VijayK
 */

package com.example.ui.itemdetail;

import android.content.Context;

import com.example.data.DataManager;
import com.example.ui.base.BasePresenter;
import com.example.ui.base.BaseView;

/**
 * Presenter class for {@link ItemDetailFragment} view.
 */
public class ItemDetailPresenter extends BasePresenter {

    public ItemDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }

    /**
     * Attaches the presenter to given view.
     *
     * @param context  context for view to populate its data.
     * @param contract Contract to communicate with view.
     */
    public void onAttach(Context context, BaseView contract) {
        super.onAttach(contract);
        mContract = contract;
        contract.addToActivity(context);
    }
}
