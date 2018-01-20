/*
 * Copyright (C) VijayK
 */

package com.example.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.R;

/**
 * This provides methods to help Activities load their UI.
 */
public class CommonUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    /**
     * Start an activities.
     */
    public static void startActivity(Context context, Class<?> activityClass) {
        context.startActivity(new Intent(context, activityClass));
    }

    /**
     * Shows loading dialog.
     */
    public static void showLoadingDialog(final View view) {
        if (view == null) {
            return;
        }
        View progress = view.findViewById(R.id.progressbar);
        if (progress != null) {
            progress.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Hides loading dialog.
     */
    public static void hideLoadingDialog(final View view) {
        if (view == null) {
            return;
        }
        View progress = view.findViewById(R.id.progressbar);
        if (progress != null) {
            progress.setVisibility(View.GONE);
        }
    }
}
