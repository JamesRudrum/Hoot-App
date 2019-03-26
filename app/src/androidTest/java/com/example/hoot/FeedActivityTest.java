package com.example.hoot;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class FeedActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule;

    public FeedActivityTest() {
        activityRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    }
    @Test
    public void userCanViewRequestContactPage() {
        onView(withId(R.id.ETLoginEmail)).perform(typeText("test@test.com"));
        onView(withId(R.id.ETLoginPassword)).perform(typeText("password123"));
        onView(withId(R.id.BTNsignin)).check(matches(isDisplayed())).perform(click());
        SystemClock.sleep(6000);
        onView(withId(R.id.BTNViewProfile)).perform(click());
        onView(withId(R.id.TVwiseOrYoungRCPage)).check(matches(isDisplayed()));
    }
}
