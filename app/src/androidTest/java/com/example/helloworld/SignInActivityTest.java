package com.example.helloworld;

import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)

public class SignInActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> activityTestRule
            = new ActivityScenarioRule<>(SignInActivity.class);

    @Test
    public void hasTextOnScreen() {
        onView(withId(R.id.age))
                .check(matches(withText(R.string.show_age)));
        onView(withId(R.id.identified_as))
                .check(matches(withText(R.string.identify_as)));
        onView(withId(R.id.seeking))
                .check(matches(withText(R.string.seeking_id)));
        onView(withId(R.id.occupation))
                .check(matches(withText(R.string.occupation_stat)));
        onView(withId(R.id.descript_title))
                .check(matches(withText(R.string.about_me)));

    }

}
