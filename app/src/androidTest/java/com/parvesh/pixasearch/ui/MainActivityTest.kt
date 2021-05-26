package com.parvesh.pixasearch.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.adapters.MainActivityRecyclerViewAdapter
import com.parvesh.pixasearch.utils.TestUtils.typeSearchViewText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var countingIdlingResource: CountingIdlingResource

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        activityRule.scenario.onActivity { activity ->
            countingIdlingResource = activity.countingIdlingResource
            IdlingRegistry.getInstance().register(countingIdlingResource)
        }
    }

    @Test
    fun checkIfImagesLoadingOnStartup() {
        onView(withId(R.id.main_activity_recycler_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkSearchFunction() {
        onView(withId(R.id.main_activity_search_view))
            .perform(typeSearchViewText("tiger"))

        onView(withId(R.id.main_activity_recycler_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun openDetailsActivity() {
        onView(withId(R.id.main_activity_recycler_view))
            .perform(actionOnItemAtPosition<MainActivityRecyclerViewAdapter.ViewHolder>(0, click()))

        onView(withText("Yes")).perform(click())

        onView(withId(R.id.activity_details_image_view))
            .check(matches(isDisplayed()))
    }


}