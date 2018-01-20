/*
 * Copyright (C) VijayK
 */

package com.example.ui.category;

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
import com.example.tracking.data.db.tables.Category;
import com.example.ui.base.BasePresenter;
import com.example.ui.result.ResultActivity;
import com.example.utils.CommonUtils;
import com.example.utils.CustomGrid;

import java.util.Collections;
import java.util.List;

/**
 * View for category.
 */
public class CategoryFragment extends Fragment implements CategoryContract, CustomGrid.Listener {

    private CategoryPresenter mCategoryPresenter;
    private CustomGrid mGridLayout;
    private static final int MAX_ITEM = 6;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.grid_screen, container, false);
        mGridLayout = (CustomGrid) root.findViewById(R.id.categories);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCategoryPresenter.loadCategoryImages(); // to refresh data when coming back from another screen.
        // will be refreshed from cached. loading is important to change weight of images after click.
    }

    @Override
    public void onPresenterAttached(BasePresenter presenter) {
        mCategoryPresenter = (CategoryPresenter) presenter;
    }

    @Override
    public void addToActivity(Context context) {
        CommonUtils.addFragmentToActivity(((AppCompatActivity) context).getFragmentManager(), this, R.id.contentFrame);
    }

    @Override
    public void populateCategory(List<Category> data) {
        Collections.sort(data, (o1, o2) -> o1.getNumberOfClick() - o2.getNumberOfClick());
        mGridLayout.removeAllViews();
        mGridLayout.setListener(this);
        mGridLayout.populate(data, MAX_ITEM);
        CommonUtils.hideLoadingDialog(getView());
    }

    @Override
    public void onImageClicked(View v) {
        mCategoryPresenter.recordClick(((Category) v.getTag()).getUrl());
        CommonUtils.startActivity(getActivity(), ResultActivity.class);
    }

    @Override
    public void getImage(String url, ImageView view) {
        mCategoryPresenter.loadImage(getActivity(), url, R.drawable.progress_animation, view);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
