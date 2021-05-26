package com.parvesh.pixasearch.utils

import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers


object TestUtils {
    fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return Matchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.isAssignableFrom(SearchView::class.java)
                )
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}