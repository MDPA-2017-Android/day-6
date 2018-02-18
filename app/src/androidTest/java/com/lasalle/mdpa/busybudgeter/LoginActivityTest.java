package com.lasalle.mdpa.busybudgeter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.errorprone.annotations.DoNotMock;
import com.lasalle.mdpa.busybudgeter.manager.UserManager;
import com.lasalle.mdpa.busybudgeter.view.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

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
import static org.mockito.Mockito.mock;


@RunWith(AndroidJUnit4.class)
@MediumTest
public class LoginActivityTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<>(LoginActivity.class, false, false);

    @Mock
    private static UserManager userManagerMock;

    private static BudgetingApplication application;
    private static Scope applicationScope;

    @Before
    public void setup() {
        application = (BudgetingApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        applicationScope = Toothpick.openScope(application);

        // We need to install the modules and launch the activity manually
        applicationScope.installTestModules(new Module() {{
            bind(UserManager.class).toInstance(userManagerMock);
        }});

        loginActivityRule.launchActivity(null);
    }

    @After
    public void tearDown() {
        Toothpick.reset(applicationScope);
        application.installToothPickModules(applicationScope);
    }

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
