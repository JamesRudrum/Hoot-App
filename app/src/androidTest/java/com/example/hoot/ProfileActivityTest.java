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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class ProfileActivityTest {

    private FirebaseAuth mAuth;

    @Rule
    public IntentsTestRule<signup> intentsTestRule =
            new IntentsTestRule<>(signup.class);

    @Test
    public void youngUserSubmitSignUpDetailsAndViewProfile() {
        onView(withId(R.id.ETfirstname)).perform(typeText("Brooke"));
        onView(withId(R.id.ETemail)).perform(typeText("queenbrooke@test.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ETaboutme)).perform(typeText("I am the queen of everything"));
        Espresso.closeSoftKeyboard();
        Intent resultData = new Intent();
        intending(allOf(hasAction(equalTo(resultData.ACTION_GET_CONTENT)),
                hasType(is("image/*"))));
        onView(withId(R.id.BTNsignup)).perform(click());
        SystemClock.sleep(8000);
        onView(withId(R.id.RBBoardGames)).check(matches(isDisplayed()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        user.delete();
    }

    @Test
    public void wiseUserSubmitSignUpDetailsAndViewProfile() {
        onView(withId(R.id.ETfirstname)).perform(typeText("Erin"));
        onView(withId(R.id.ETemail)).perform(typeText("queenerin@test.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ETaboutme)).perform(typeText("I am also the queen of everything"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SWaccounttype)).perform(click());
        Intent resultData = new Intent();
        intending(allOf(hasAction(equalTo(resultData.ACTION_GET_CONTENT)),
                hasType(is("image/*"))));
        onView(withId(R.id.BTNsignup)).perform(click());
        SystemClock.sleep(8000);
        onView(withId(R.id.RBBoardGames)).check(matches(isDisplayed()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        user.delete();
    }
}