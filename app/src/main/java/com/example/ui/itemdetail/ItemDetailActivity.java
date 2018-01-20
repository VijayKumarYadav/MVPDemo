/*
 * Copyright (C) VijayK
 */

package com.example.ui.itemdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.R;
import com.example.di.DataManagerProvider;
import com.example.tracking.data.db.tables.Category;

/**
 * Activity class for Item detail images.
 */
public class ItemDetailActivity extends AppCompatActivity {

    ItemDetailPresenter mItemDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holder);
        Category category = (Category) getIntent().getSerializableExtra(ItemDetailFragment.KEY);
        ItemDetailFragment fragment = (ItemDetailFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) fragment = ItemDetailFragment.newInstance(category);
        mItemDetailPresenter = new ItemDetailPresenter(DataManagerProvider.provideDataManager(this));
        mItemDetailPresenter.onAttach(this, fragment);
    }
}
