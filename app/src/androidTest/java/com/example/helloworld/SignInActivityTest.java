package com.example.helloworld;

import android.content.Intent;
import androidx.test.rule.ActivityTestRule;
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
    public void hasTextOnScreen() {
        onView(withId(R.id.descript_title))
                .check(matches(withText(R.string.about_me)));
    }

    @Test
    public void Constants() {
    }

}
