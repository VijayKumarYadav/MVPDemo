package com.example;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.ui.welcome.WelcomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Copyright (C) VijayK
 */
@RunWith(AndroidJUnit4.class)
// test for WelcomeScreen
public class WelcomeScreenTest {

    @Rule
    public ActivityTestRule<WelcomeActivity> mTasksActivityTestRule =
            new ActivityTestRule<>(WelcomeActivity.class);

    @Test
    public void testInitUi() {
        onView(withId(R.id.welcome_view)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.welcome_view), withText(R.string.welcome_text)));
        onView(withId(R.id.welcome_most_view_cat_image)).check(matches(isDisplayed()));
        onView(withId(R.id.welcome_go_cat)).check(matches(isDisplayed()));
    }


    /**
     * Integration testing. Clicking on go to category button on welcome screen should go on category screen.
     */
    @Test
    public void testNextScreenLinking() {
        // clicking on go to category should go to category screen.
        // click on item should open result
        onView(withId(R.id.welcome_go_cat)).perform(click());
        // category screen should open
        onView(withId(R.id.cat_text)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.cat_text), withText(R.string.i_am_interested_in)));
    }
}
