/*
 * Copyright (C) VijayK
 */

package com.example.ui.welcome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.R;
import com.example.di.DataManagerProvider;

/**
 * First welcome screen activity class.
 */
public class WelcomeActivity extends AppCompatActivity {

    WelcomePresenter mWelcomePresenter;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holder);
        WelcomeFragment fragment = (WelcomeFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) fragment = WelcomeFragment.newInstance();
        mWelcomePresenter = new WelcomePresenter(DataManagerProvider.provideDataManager(this));
        mWelcomePresenter.onAttach(this, fragment);
    }
}
