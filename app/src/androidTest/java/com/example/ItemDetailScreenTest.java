/*
 * Copyright (C) VijayK
 */

package com.example;

import android.support.test.rule.ActivityTestRule;

import com.example.ui.itemdetail.ItemDetailActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

// test for CategoryScreen
public class ItemDetailScreenTest {

    @Rule
    public ActivityTestRule<ItemDetailActivity> mTasksActivityTestRule =
            new ActivityTestRule<ItemDetailActivity>(ItemDetailActivity.class);

    @Test
    public void testInitUi() {
        // ui should have following components
        onView(withId(R.id.item_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.item_detail_details)).check(matches(isDisplayed()));
        onView(withId(R.id.item_detail_icon)).check(matches(isDisplayed()));
    }
}
