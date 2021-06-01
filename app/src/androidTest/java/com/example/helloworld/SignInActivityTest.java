package com.example.helloworld;

import android.content.Intent;
import android.view.Gravity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.helloworld.TestUtils.waitFor;
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

        onView(allOf(withId(R.id.tv_setting_header))).check((matches(withText("Update Your Profile!"))));

        onView(withId(R.id.reminderTime)).perform(click());
        onView(withText("01:00")).perform(click());
        onView(withId(R.id.searchMiles)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("5")))
                .perform(click());
        onView(withId(R.id.accountStatus)).perform(click());
        onView(withId(R.id.ageRangeMin)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("35")))
                .perform(click());
        onView(withId(R.id.ageRangeMax)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("40")))
                .perform(click());
        onView(withId(R.id.genderId)).perform(click());
        onView(withText("Female")).perform(click());
        onView(withId(R.id.updateButton)).perform(click());
    }

    @Test
    public void testMatches() {
        onView(isRoot()).perform(waitFor(1000));
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open()); // Open Drawer
        onView(isRoot()).perform(waitFor(1000));
        onView(withText(R.string.matches))
                .perform(click()); // Select nav button in nav drawer
        onView(isRoot()).perform(waitFor(1000));
        //onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollToPosition(1));
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new TestUtils.ClickOnLikeButton()));
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new TestUtils.ClickOnLikeButton()));

        onView(withText(R.string.message)).inRoot(new TestUtils.ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void Constants() {
    }

}
