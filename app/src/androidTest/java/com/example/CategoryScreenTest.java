/*
 * Copyright (C) VijayK
 */

package com.example;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.ui.category.CategoryActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * UI test for CategoryScreen
 */
@RunWith(AndroidJUnit4.class)
public class CategoryScreenTest {

    @Rule
    public ActivityTestRule<CategoryActivity> mTasksActivityTestRule =
            new ActivityTestRule<CategoryActivity>(CategoryActivity.class);

    @Test
    public void testInitUi() {
        // test if ui is visible with proper content
        onView(withId(R.id.cat_text)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.cat_text), withText(R.string.i_am_interested_in)));
        onView(withId(R.id.categories)).check(matches(isDisplayed()));
    }
}
