package com.lasalle.mdpa.busybudgeter;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lasalle.mdpa.busybudgeter.view.ContainerActivity;
import com.lasalle.mdpa.busybudgeter.view.HomeFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class ContainerActivityTest {

    @Rule
    public ActivityTestRule<ContainerActivity> containerActivityRule = new ActivityTestRule<>(ContainerActivity.class);

    @Test
    public void checkFirstFragmentLoadedIsHome() {
        FragmentManager fragment = containerActivityRule.getActivity().getSupportFragmentManager();
        Fragment currentFragment = fragment.findFragmentById(R.id.fragment_container);

        assertThat(currentFragment, instanceOf(HomeFragment.class));
    }

}
