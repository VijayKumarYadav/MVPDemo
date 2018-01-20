/*
 * Copyright (C) VijayK
 */

package com.example.ui.welcome;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.R;
import com.example.ui.base.BasePresenter;
import com.example.ui.category.CategoryActivity;
import com.example.utils.CommonUtils;

/**
 * Welcome screen view.
 */
public class WelcomeFragment extends Fragment implements WelcomeContract {

    private ImageView mMostViewedImage;
    private WelcomePresenter mWelcomePresenter;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.welcome_screen, container, false);
        mMostViewedImage = (ImageView) root.findViewById(R.id.welcome_most_view_cat_image);
        root.findViewById(R.id.welcome_go_cat).setOnClickListener(v -> startCategoryActivity(getActivity(), false));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWelcomePresenter != null) {
            mWelcomePresenter.getMostViewImageUrl();  // refresh data when coming back from another screen.
        }
    }

    @Override
    public void addToActivity(Context context) {
        CommonUtils.addFragmentToActivity(((AppCompatActivity) context).getFragmentManager(), this, R.id.contentFrame);
    }

    @Override
    public void startCategoryActivity(Context context, Boolean isFinish) {
        CommonUtils.startActivity(context, CategoryActivity.class);
        if (isFinish) ((Activity) context).finish();
    }

    @Override
    public void loadImage(String url) {
        CommonUtils.hideLoadingDialog(getView());
        if (url != null) {
            mWelcomePresenter.loadImage(getActivity(), url, R.drawable.progress_animation, mMostViewedImage);
        }
    }

    @Override
    public void onPresenterAttached(BasePresenter presenter) {
        mWelcomePresenter = (WelcomePresenter) presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
