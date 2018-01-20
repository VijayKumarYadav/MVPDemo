/*
 * Copyright (C) VijayK
 */

package com.example.ui.result;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.R;
import com.example.tracking.data.db.tables.Category;
import com.example.ui.base.BasePresenter;
import com.example.ui.itemdetail.ItemDetailActivity;
import com.example.ui.itemdetail.ItemDetailFragment;
import com.example.utils.CommonUtils;
import com.example.utils.CustomGrid;

import java.util.List;

/**
 * View for random images.
 */
public class ResultFragment extends Fragment implements ResultContract, CustomGrid.Listener {

    private ResultPresenter mResultPresenter;
    private CustomGrid mGridLayout;

    public static ResultFragment newInstance() {
        return new ResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.grid_screen, container, false);
        mGridLayout = (CustomGrid) root.findViewById(R.id.categories);
        ((TextView) root.findViewById(R.id.cat_text)).setText(getResources().getString(R.string.result));
        mResultPresenter.loadImages();
        return root;
    }

    @Override
    public void onPresenterAttached(BasePresenter presenter) {
        mResultPresenter = (ResultPresenter) presenter;
    }

    @Override
    public void addToActivity(Context context) {
        CommonUtils.addFragmentToActivity(((AppCompatActivity) context).getFragmentManager(), this, R.id.contentFrame);
    }

    @Override
    public void populateImages(List<Category> data) {
        mGridLayout.setListener(this);
        mGridLayout.populate(data, 12);
        CommonUtils.hideLoadingDialog(getView());
    }

    @Override
    public void onImageClicked(View v) {
        Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
        intent.putExtra(ItemDetailFragment.KEY, (Category) v.getTag());
        startActivity(intent);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void getImage(String url, ImageView view) {
        mResultPresenter.loadImage(getActivity(), url, R.drawable.progress_animation, view);
    }
}
