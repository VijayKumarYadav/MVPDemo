/*
 * Copyright (C) VijayK
 */

package com.example.ui.itemdetail;

import android.app.Fragment;
import android.content.Context;
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
import com.example.ui.base.BaseView;
import com.example.utils.CommonUtils;

/**
 * View for Item detail.
 */
public class ItemDetailFragment extends Fragment implements BaseView {

    private ItemDetailPresenter mItemDetailPresenter;

    public static final String KEY = "DetailKey";

    public static ItemDetailFragment newInstance(Category category) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, category);
        ItemDetailFragment detailFragment = new ItemDetailFragment();
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.item_detail, container, false);
        ImageView iconView = root.findViewById(R.id.item_detail_icon);
        Category category = (Category) getArguments().getSerializable(KEY);
        if (category != null) {
            ((TextView) root.findViewById(R.id.item_detail_title)).setText(category.getTitle());
            ((TextView) root.findViewById(R.id.item_detail_details)).setText(category.getDateTaken());
            mItemDetailPresenter.loadImage(getActivity(), category.getUrl(), R.drawable.progress_animation, iconView);
        }
        return root;
    }

    @Override
    public void onPresenterAttached(BasePresenter presenter) {
        mItemDetailPresenter = (ItemDetailPresenter) presenter;
    }

    @Override
    public void addToActivity(Context context) {
        CommonUtils.addFragmentToActivity(((AppCompatActivity) context).getFragmentManager(), this, R.id.contentFrame);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }
}
