package com.parvesh.pixasearch.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.parvesh.pixasearch.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashActivityTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<SplashActivity> =
        ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun setup() {
        activityRule.scenario.onActivity { activity ->
            IdlingRegistry.getInstance().register(activity.countingIdlingResource)
        }
    }

    @Test
    fun checkIfMainActivityOpeningAfterOneMinute() {
        Espresso.onView(ViewMatchers.withId(R.id.main_activity_search_view))
            .check(matches(ViewMatchers.isDisplayed()))
    }


}