package com.example.hoot;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasType;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

public class signupTest {

    private FirebaseAuth mAuth;

    @Rule
    public IntentsTestRule<signup> intentsTestRule =
            new IntentsTestRule<>(signup.class);

    @Test
    public void youngUserSubmitSignUpDetails() {
        onView(withId(R.id.ETfirstname)).perform(typeText("James"));
        onView(withId(R.id.ETemail)).perform(typeText("kingjames@test.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ETaboutme)).perform(typeText("Ya Gunners Ya"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.RByoung)).perform(click());
        Intent resultData = new Intent();
        intending(allOf(hasAction(equalTo(Intent.ACTION_GET_CONTENT)),
                hasType(is("image/*"))));
        onView(withId(R.id.BTNsignup)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.RBBoardGames)).check(matches(isDisplayed()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.delete();
    }

    @Test
    public void oldUserSubmitSignUpDetails() {
        onView(withId(R.id.ETfirstname)).perform(typeText("Elliot"));
        onView(withId(R.id.ETemail)).perform(typeText("kingelliot@test.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ETaboutme)).perform(typeText("I love fulham"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.RBwise)).perform(click());
        Intent resultData = new Intent();
        intending(allOf(hasAction(equalTo(Intent.ACTION_GET_CONTENT)),
                hasType(is("image/*"))));
        onView(withId(R.id.BTNsignup)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.RBBoardGames)).check(matches(isDisplayed()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.delete();
    }

    @Test
    public void userCanSignUpThenLogoutAndLogin() {
        onView(withId(R.id.ETfirstname)).perform(typeText("Bob"));
        onView(withId(R.id.ETemail)).perform(typeText("bob@bob.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("bob123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ETaboutme)).perform(typeText("Bob's here"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.RByoung)).perform(click());
        Intent resultData = new Intent();
        intending(allOf(hasAction(equalTo(Intent.ACTION_GET_CONTENT)),
                hasType(is("image/*"))));
        onView(withId(R.id.BTNsignup)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.RBBoardGames)).perform(click());
        onView(withId(R.id.RBBoardGames)).check(matches(isChecked()));
        onView(withId(R.id.RBPuzzles)).perform(click());
        onView(withId(R.id.RBPuzzles)).check(matches(isChecked()));
        onView(withId(R.id.RBMusic)).perform(click());
        onView(withId(R.id.RBMusic)).check(matches(isChecked()));
        onView(withId(R.id.BTNinterestsSubmit)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.TVaboutMeTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.TVMyInterests)).check(matches(withText("My Interests")));
        onView(withId(R.id.BTNLogoutProfile)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.ETLoginEmail)).perform(typeText("bob@bob.com"));
        onView(withId(R.id.ETLoginPassword)).perform(typeText("bob123"));
        onView(withId(R.id.BTNsignin)).check(matches(isDisplayed())).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.BTNLogoutFeed)).check(matches(isDisplayed()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.delete();
    }
}
