package com.example.hoot;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

public class MainActivityTest {

    private FirebaseAuth mAuth;

    @Rule
    public ActivityTestRule<MainActivity> activityRule;

    public MainActivityTest() {
        activityRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.hoot", appContext.getPackageName());
    }

    @Test
    public void userCanPressSignUpLink() {
        onView(withId(R.id.BTNsignuplink)).perform(click());
        onView(withId(R.id.TVpic)).check(matches(withText("Select your profile picture:")));
        onView(withId(R.id.ETpassword)).check(matches(withHint("Password")));
    }

    @Test
    public void userCanLogin() {
        onView(withId(R.id.ETLoginEmail)).perform(typeText("test@test.com"));
        onView(withId(R.id.ETLoginPassword)).perform(typeText("password123"));
        onView(withId(R.id.BTNsignin)).check(matches(isDisplayed())).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.feedTitle)).check(matches(isDisplayed()));
    }
}
