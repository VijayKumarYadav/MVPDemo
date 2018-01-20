/*
 * Copyright (C) VijayK
 */

package com.example.ui.result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.R;
import com.example.di.DataManagerProvider;

/**
 * Activity class to show random images.
 */
public class ResultActivity extends AppCompatActivity {

    ResultPresenter mResultPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holder);
        ResultFragment fragment = (ResultFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) fragment = ResultFragment.newInstance();
        mResultPresenter = new ResultPresenter(DataManagerProvider.provideDataManager(this));
        mResultPresenter.onAttach(this, fragment);
    }
}
