package com.example;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.robolectric.RuntimeEnvironment.application;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "org.powermock.*"})
public abstract class RobolectricTest {
    @Rule
    public PowerMockRule mRule = new PowerMockRule();

    // activity instance.
    private FragmentActivity mFragmentActivity;

    /**
     * Build an fragment activity.
     */
    public void setup() {
        mFragmentActivity = Robolectric.buildActivity(FragmentActivity.class).create().start()
                .resume().get();
    }

    /**
     * Gets FragmentManager.
     */
    public android.app.FragmentManager getFragmentManager() {
        return mFragmentActivity.getFragmentManager();
    }

    /**
     * Gets getSupportFragmentManager.
     */
    public FragmentManager getSupportFragmentManager() {
        return mFragmentActivity.getSupportFragmentManager();
    }

    /**
     * Gets Application context.
     */
    public Context getApplicationContext() {
        return application.getApplicationContext();
    }

    /**
     * Gets Activity context.
     */
    public Context getActivityContext() {
        return mFragmentActivity;
    }
}

