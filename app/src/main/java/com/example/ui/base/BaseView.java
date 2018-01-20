/*
 * Copyright (C) VijayK
 */

package com.example.ui.base;

import android.content.Context;

/**
 * Base view class.
 */
public interface BaseView {
    void onPresenterAttached(BasePresenter presenter);

    void addToActivity(Context context);

    boolean isActive();
}

