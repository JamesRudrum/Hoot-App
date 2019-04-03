package com.example.hoot;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
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

public class RequestContactPageActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule;

    public RequestContactPageActivityTest() {
        activityRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    }

    @Rule
    public IntentsTestRule<signup> intentsTestRule =
            new IntentsTestRule<>(signup.class);

    private FirebaseAuth mAuth;

    @Test
    public void userCanViewRequestContactPageReturnToFeedAndViewAnotherRCP() {
        onView(withId(R.id.ETLoginEmail)).perform(typeText("elliot.jennings66@googlemail.com"));
        onView(withId(R.id.ETLoginPassword)).perform(typeText("elliot1"));
        onView(withId(R.id.BTNsignin)).check(matches(isDisplayed())).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.BTNViewProfile)));
        SystemClock.sleep(5000);
        onView(withId(R.id.TVwiseOrYoungRCPage)).check(matches(isDisplayed()));
        onView(withId(R.id.BTNFeedRCP)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, MyViewAction.clickChildViewWithId(R.id.BTNViewProfile)));
        SystemClock.sleep(5000);
        onView(withId(R.id.TVwiseOrYoungRCPage)).check(matches(isDisplayed()));
    }

    @Test
    public void userCanSignUpNavigateToFeedAndClickOnViewRequestContactPage() {
        onView(withId(R.id.BTNsignuplink)).perform(click());
        onView(withId(R.id.ETfirstname)).perform(typeText("Brooke"));
        onView(withId(R.id.ETemail)).perform(typeText("queenbrooke@test.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ETaboutme)).perform(typeText("I am the queen of everything"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.RByoung)).perform(click());
        Intent resultData = new Intent();
        intending(allOf(hasAction(equalTo(Intent.ACTION_GET_CONTENT)),
                hasType(is("image/*"))));
        onView(withId(R.id.BTNsignup)).perform(click());
        SystemClock.sleep(8000);
        onView(withId(R.id.RBPhotography)).perform(click());
        onView(withId(R.id.RBPhotography)).check(matches(isChecked()));
        onView(withId(R.id.RBSport)).perform(click());
        onView(withId(R.id.RBSport)).check(matches(isChecked()));
        onView(withId(R.id.RBBooks)).perform(click());
        onView(withId(R.id.RBBooks)).check(matches(isChecked()));
        onView(withId(R.id.BTNinterestsSubmit)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.TVinterestsListProfilePage)).check(matches(withText(containsString("Books\nPhotography\nSport\n"))));
        onView(withId(R.id.BTNFeedProfile)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(5, MyViewAction.clickChildViewWithId(R.id.BTNViewProfile)));
        SystemClock.sleep(5000);
        onView(withId(R.id.TVwiseOrYoungRCPage)).check(matches(isDisplayed()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.delete();
    }

    @Test
    public void userCanViewRequestContactPageAndFillInEmailForm() {
        onView(withId(R.id.ETLoginEmail)).perform(typeText("elliot.jennings66@googlemail.com"));
        onView(withId(R.id.ETLoginPassword)).perform(typeText("elliot1"));
        onView(withId(R.id.BTNsignin)).check(matches(isDisplayed())).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.BTNViewProfile)));
        SystemClock.sleep(5000);
        onView(withId(R.id.TVwiseOrYoungRCPage)).check(matches(isDisplayed()));
        onView(withId(R.id.FABchatButtonRCPage)).perform(scrollTo(), click());
//        onView(withId(R.id.FABchatButtonRCPage)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.ETemail_subject)).perform(typeText("Hi there!"));
        SystemClock.sleep(5000);
        onView(withId(R.id.ETemail_message)).perform(typeText("Let's arrange a meet up :)"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.BTsend)).check(matches(isDisplayed()));
    }

}
