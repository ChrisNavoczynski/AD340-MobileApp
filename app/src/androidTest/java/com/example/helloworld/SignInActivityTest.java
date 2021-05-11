package com.example.helloworld;

import android.content.Intent;
import android.view.Gravity;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)

public class SignInActivityTest {
    @Rule
    public ActivityTestRule<SignInActivity> activityTestRule
            = new ActivityTestRule<SignInActivity>(SignInActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent testIntent = new Intent();
            testIntent.putExtra(Constants.PROFILE_NAME, "Jasper Doodoohead");
            testIntent.putExtra(Constants.AGE, 11);
            testIntent.putExtra(Constants.ID_AS, "Male");
            testIntent.putExtra(Constants.SEEKING, "Female");
            testIntent.putExtra(Constants.OCCUPATION, "Guard Dog");
            testIntent.putExtra(Constants.ABOUT_ME, "I love walks, treats, and bones");
            return testIntent;
        }
    };

    @Test
    public void clickProfileFragmentDrawer() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_profile));

        onView(allOf(withId(R.id.descript_title))).check((matches(withText("About Me:"))));
    }

    @Test
    public void clickMatchesFragmentDrawer() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_matches));

        onView(allOf(withId(R.id.matches_will_go_here))).check((matches(withText("My Matches"))));
    }

    @Test
    public void clickSettingsFragmentDrawer() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_settings));

        onView(allOf(withId(R.id.future_settings))).check((matches(withText("Settings will go here"))));
    }

    @Test
    public void Constants() {
    }

}
