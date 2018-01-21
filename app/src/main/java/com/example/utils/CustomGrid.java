/*
 * Copyright (C) VijayK
 */

package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.R;
import com.example.tracking.data.db.tables.Category;

import java.util.List;

/**
 * Custom grid on top of {@link GridLayout} to populate the {@link Category} data based on category image click (weight).
 */
public class CustomGrid extends GridLayout {

    private Listener mListener;

    public CustomGrid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGrid(Context context) {
        super(context);
    }

    /**
     * Populate grid with category data.
     */
    public void populate(List<Category> data, int totalCell) {
        if (data == null || data.size() == 0) {
            return;
        }
        Point size = new Point();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getSize(size);
        int gap = (int) getResources().getDimension(R.dimen.default_gap);
        int screenWidth = size.x - 4 * gap;
        int singleCellWidth = (int) (screenWidth / 3);

        int row = 0, col = 0;
        int left = 0;
        for (int i = 0; i < totalCell; i++) {
            left = (col == 0) ? gap : 0;

            float click = (float) data.get(i).getNumberOfClick() / getHighestClick(data);

            GridLayout.Spec spec;
            int width = singleCellWidth;
            int height = singleCellWidth;
            if (click <= 0.2f) {
                spec = GridLayout.spec(col);
                width = singleCellWidth;
                col++;
            } else if (click <= 0.3f) {
                width = 2 * singleCellWidth + gap;
                if (col > 1) {
                    row++;
                    col = 0;
                    left = gap;
                }
                spec = GridLayout.spec(col, 2);
                col += 2;
            } else {
                width = screenWidth + 2 * gap;
                if (col != 0) {
                    col = 0;
                    left = gap;
                    row++;
                }
                spec = GridLayout.spec(col, 3);
                col += 3;
            }

            GridLayout.Spec row0 = GridLayout.spec(row);
            GridLayout.Spec col0 = spec;
            GridLayout.LayoutParams first = new GridLayout.LayoutParams(row0, col0);
            first.height = height;
            first.width = width;
            first.setMargins(left, gap, gap, 0);

            ImageView imageView = getImageView();
            imageView.setTag(data.get(i));
            addView(imageView, first);

            if (mListener != null) {
                mListener.getImage(data.get(i).getUrl(), imageView);
            }
            if (col == 3) {
                col = 0;
                left = gap;
                row++;
            }
        }
    }

    private ImageView getImageView() {
        final ImageView imageView = (ImageView) ((Activity) getContext()).getLayoutInflater().inflate(R.layout.category_item, null);
        imageView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onImageClicked(v);
            }
        });
        return imageView;
    }

    private int getHighestClick(List<Category> data) {
        int max = 0;
        for (Category category : data) {
            max = max + category.getNumberOfClick();
        }

        return max;
    }

    /**
     * Listener to listen image for image clicked or to get {@link android.graphics.drawable.Drawable} for image.
     */
    public interface Listener {
        /**
         * Called when image is clicked.
         */
        void onImageClicked(View v);

        /**
         * Called to get {@link android.graphics.drawable.Drawable} for {@link ImageView}.
         */
        void getImage(String url, ImageView view);
    }

    /**
     * Set the Listener to listen events.
     */
    public void setListener(Listener listener) {
        mListener = listener;
    }
}
