package com.example.hoot;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule;

    public ExampleInstrumentedTest() {
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
        onView(withId(R.id.signup)).check(matches(withText("Sign Up")));
    }

    @Test
    public void userSubmitSignUpDetails() {
        onView(withId(R.id.BTNsignuplink)).perform(click());
        onView(withId(R.id.ETfirstname)).perform(typeText("James"));
        onView(withId(R.id.ETemail)).perform(typeText("james@google.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("password123"));
        onView(withId(R.id.BTNsignup)).perform(click());
    }
    @Test
    public void userCanLogin() {
        onView(withId(R.id.ETLoginEmail)).perform(typeText("abc@abc.com"));
        onView(withId(R.id.ETLoginPassword)).perform(typeText("123456"));
        onView(withId(R.id.BTNsignin)).perform(click());
    }
}