/**
 * Copyright (C) VijayK
 */
package com.example;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.ui.result.ResultActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
// test for ResultScreen
public class ResultScreenTest {

    @Rule
    public ActivityTestRule<ResultActivity> mTasksActivityTestRule =
            new ActivityTestRule<ResultActivity>(ResultActivity.class);

    @Test
    public void testInitUi() {
        // ui should have following components
        onView(withId(R.id.cat_text)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.cat_text), withText(R.string.result)));
        onView(withId(R.id.categories)).check(matches(isDisplayed()));
    }
}
