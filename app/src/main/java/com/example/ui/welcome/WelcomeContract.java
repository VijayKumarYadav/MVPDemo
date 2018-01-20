/*
 * Copyright (C) VijayK
 */

package com.example.ui.welcome;

import android.content.Context;

import com.example.ui.base.BaseView;

/**
 * Interface for view to be used by presenter for communication.
 */
public interface WelcomeContract extends BaseView {

    void startCategoryActivity(Context context, Boolean isFinish);

    void loadImage(String url);
}
