package com.example.helloworld;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void hasTextOnScreen() {

        onView(withId(R.id.tv_logo))
                .check(matches(withText(R.string.app_name)));

        onView(withId(R.id.author))
                .check(matches(withText(R.string.name_year)));
    }

    @Test
    public void canGoToSignInActivityWithUser() {
        onView(withId(R.id.et_user)).perform(typeText("Test"));

        try {
            Intents.init();
            onView(withId(R.id.btn_submit)).perform(scrollTo(), click());
            intended(hasComponent(SignInActivity.class.getName()));
            intended(hasExtra(Constants.USER_NAME, "Test"));
        } finally {
            Intents.release();
        }
    }
}


