package com.example.hoot;
import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private FirebaseAuth mAuth;

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
        onView(withId(R.id.ETpassword)).check(matches(withHint("Password")));
    }

    @Test
    public void youngUserSubmitSignUpDetails() {
        onView(withId(R.id.BTNsignuplink)).perform(click());
        onView(withId(R.id.ETfirstname)).perform(typeText("James"));
        onView(withId(R.id.ETemail)).perform(typeText("james@test.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("password123"));
        onView(withId(R.id.ETaboutme)).perform(typeText("I like chess"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.BTNsignup)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.profileTitle)).check(matches(isDisplayed()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        user.delete();
    }

    @Test
    public void oldUserSubmitSignUpDetails() {
        onView(withId(R.id.BTNsignuplink)).perform(click());
        onView(withId(R.id.ETfirstname)).perform(typeText("James"));
        onView(withId(R.id.ETemail)).perform(typeText("james2@test.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("password123"));
        onView(withId(R.id.ETaboutme)).perform(typeText("I like chess"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SWaccounttype)).perform(click());
        onView(withId(R.id.BTNsignup)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.profileTitle)).check(matches(isDisplayed()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        user.delete();
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