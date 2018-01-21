/*
 * Copyright (C) VijayK
 */

package com.example.ui.base;

import android.content.Context;

/**
 * Base view class.
 */
public interface BaseView {

    /**
     * To be called when presenter is attached to view.
     */
    void onPresenterAttached(BasePresenter presenter);

    /**
     * Populate its view with given context.
     */
    void addToActivity(Context context);

    /**
     * Gets if view is active now. Presenter will only be active once view will be active.
     */
    boolean isActive();
}

