package com.example.helloworld;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void hasTextOnScreen() {

        onView(withId(R.id.tv_logo))
                .check(matches(withText(R.string.signin_title)));

        onView(withId(R.id.author))
                .check(matches(withText(R.string.name_year)));
    }

    @Test
    public void profileFieldsRequired() {
        onView(withId(R.id.et_name)).perform(typeText(""));
        onView(withId(R.id.occupation)).perform(typeText(""));
        onView(withId(R.id.description)).perform(typeText(""));
        onView(withId(R.id.btn_submit)).perform(scrollTo(),click());
        onView(allOf(withId(R.id.et_name), hasErrorText("Form contains errors")));
        onView(allOf(withId(R.id.occupation), hasErrorText("Form contains errors")));
        onView(allOf(withId(R.id.description), hasErrorText("Form contains errors")));
    }

    @Test
    public void infoStaticOnRotate() {
        onView(withId(R.id.et_name)).perform(typeText("Jasper Doggo"));
        onView(withId(R.id.occupation)).perform(typeText("Best dog around"));

        TestUtils.rotateScreen(TestUtils.getActivity(activityTestRule));

        onView(withId(R.id.et_name)).check(matches(withText("Jasper Doggo")));
        onView(withId(R.id.occupation)).check(matches(withText("Best dog around")));
    }
}


