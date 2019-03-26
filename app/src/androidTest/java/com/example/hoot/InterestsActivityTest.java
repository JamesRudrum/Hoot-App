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
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

public class InterestsActivityTest {


    private FirebaseAuth mAuth;

    @Rule
    public IntentsTestRule<signup> intentsTestRule =
            new IntentsTestRule<>(signup.class);

    @Test
    public void youngUserTakesYouToInterestsActivityandChecksAllInterests() {
        onView(withId(R.id.ETfirstname)).perform(typeText("Bob"));
        onView(withId(R.id.ETemail)).perform(typeText("bob@bob.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("bob123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ETaboutme)).perform(typeText("Bob's here"));
        Espresso.closeSoftKeyboard();
        Intent resultData = new Intent();
        intending(allOf(hasAction(equalTo(Intent.ACTION_GET_CONTENT)),
                hasType(is("image/*"))));
        onView(withId(R.id.BTNsignup)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.RBBoardGames)).perform(click());
        onView(withId(R.id.RBBoardGames)).check(matches(isChecked()));
        onView(withId(R.id.RBBooks)).perform(click());
        onView(withId(R.id.RBBooks)).check(matches(isChecked()));
        onView(withId(R.id.RBPuzzles)).perform(click());
        onView(withId(R.id.RBPuzzles)).check(matches(isChecked()));
        onView(withId(R.id.RBKnitting)).perform(click());
        onView(withId(R.id.RBKnitting)).check(matches(isChecked()));
        onView(withId(R.id.RBMusic)).perform(click());
        onView(withId(R.id.RBMusic)).check(matches(isChecked()));
        onView(withId(R.id.RBFilms)).perform(click());
        onView(withId(R.id.RBFilms)).check(matches(isChecked()));
        onView(withId(R.id.RBCurrentAffairs)).perform(click());
        onView(withId(R.id.RBCurrentAffairs)).check(matches(isChecked()));
        onView(withId(R.id.RBCardGames)).perform(click());
        onView(withId(R.id.RBCardGames)).check(matches(isChecked()));
        onView(withId(R.id.RBPhotography)).perform(click());
        onView(withId(R.id.RBPhotography)).check(matches(isChecked()));
        onView(withId(R.id.RBSport)).perform(click());
        onView(withId(R.id.RBSport)).check(matches(isChecked()));
        onView(withId(R.id.BTNinterestsSubmit)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.TVprofilePageName)).check(matches(withText(containsString("Bob"))));
        onView(withId(R.id.TVprofileWiseOrYoung)).check(matches(withText(containsString("Young"))));
        onView(withId(R.id.TVAboutMeProfile)).check(matches(withText(containsString("Bob's here"))));
        onView(withId(R.id.TVMyInterests)).check(matches(withText(containsString("My Interests"))));
        onView(withId(R.id.TVinterestsListProfilePage)).check(matches(withText(containsString("Card Games\nBoard Games\nPuzzles\nKnitting\nMusic\nFilms\nCurrent Affairs\nPhotography\nBooks\nSport\n"))));

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.delete();

    }
}