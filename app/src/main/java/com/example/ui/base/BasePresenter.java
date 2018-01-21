/*
 * Copyright (C) VijayK
 */

package com.example.ui.base;

import android.content.Context;
import android.widget.ImageView;

import com.example.data.DataManager;

import java.lang.ref.WeakReference;

/**
 * Base presenter class.
 */
public class BasePresenter {

    protected final DataManager mDataManager;
    protected BaseView mContract;

    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    /**
     * Attaches the presenter to given view.
     */
    public void onAttach(BaseView mvpView) {
        mContract = mvpView;
        mContract.onPresenterAttached(this);
    }

    /**
     * Loads images to given {@link ImageView}. Keeps weak reference of images view.
     */
    public void loadImage(Context context, String url, int drawable, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageDrawable(context.getResources().getDrawable(drawable));
        WeakReference<ImageView> imageViewWeakReference = new WeakReference<ImageView>(imageView);
        mDataManager.loadImages(url, imageViewWeakReference);
    }
}
