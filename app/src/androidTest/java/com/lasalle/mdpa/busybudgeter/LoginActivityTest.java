package com.lasalle.mdpa.busybudgeter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lasalle.mdpa.busybudgeter.view.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void emptyUserAndPassword() {
        onView(withId(R.id.username))
                .perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        // Check that a toast is displayed because of empty user and password
        onView(withText(R.string.login_empty)).
                inRoot(withDecorView(not(is(loginActivityRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

}
