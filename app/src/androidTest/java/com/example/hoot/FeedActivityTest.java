package com.example.hoot;

import android.content.Intent;
import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.hamcrest.Matcher;
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

class MyViewAction {

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

}

public class FeedActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule;

    public FeedActivityTest() {
        activityRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    }

    private FirebaseAuth mAuth;

    @Test
    public void userCanViewRequestContactPage() {
        onView(withId(R.id.ETLoginEmail)).perform(typeText("elliot.jennings66@googlemail.com"));
        onView(withId(R.id.ETLoginPassword)).perform(typeText("elliot1"));
        onView(withId(R.id.BTNsignin)).check(matches(isDisplayed())).perform(click());
        SystemClock.sleep(6000);
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.BTNViewProfile)));
        onView(withId(R.id.TVwiseOrYoungRCPage)).check(matches(isDisplayed()));
    }

    @Test
    public void userCanSignUpThenNavigateFromProfileToFeed() {
        onView(withId(R.id.BTNsignuplink)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.ETfirstname)).perform(typeText("Bob"));
        onView(withId(R.id.ETemail)).perform(typeText("bob@bob.com"));
        onView(withId(R.id.ETpassword)).perform(typeText("bob123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.ETaboutme)).perform(typeText("Bob's here"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.RByoung)).perform(click());
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
        onView(withId(R.id.BTNFeedProfile)).perform(click());
        SystemClock.sleep(5000);
        onView(withId(R.id.BTNLogoutFeed)).check(matches(isDisplayed()));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.delete();
    }


}
