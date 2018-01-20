/*
 * Copyright (C) VijayK
 */

package com.example.ui.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.R;
import com.example.di.DataManagerProvider;

/**
 * Activity class for Category images.
 */
public class CategoryActivity extends AppCompatActivity {

    CategoryPresenter mCategoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holder);
        CategoryFragment fragment = (CategoryFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) fragment = CategoryFragment.newInstance();
        mCategoryPresenter = new CategoryPresenter(DataManagerProvider.provideDataManager(this));
        mCategoryPresenter.onAttach(this, fragment);
    }
}
