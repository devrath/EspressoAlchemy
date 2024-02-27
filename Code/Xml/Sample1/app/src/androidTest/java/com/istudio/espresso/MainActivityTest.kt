package com.istudio.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.istudio.espresso.demos.MainActivity
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    // SUT
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Matching the text by ID
     */
    @Test
    fun testMatchById() {
        // ARRANGE
        val textToBeMatched = "Hello World!"
        // ACT AND ASSERT
        onView(withId(R.id.demoTextId)).check(matches(withText(textToBeMatched)))
    }

    /**
     * Matching by text
     */
    @Test
    fun testMatchByText() {
        // ARRANGE
        val textToBeMatched = "Hello World!"
        // ACT AND ASSERT
        onView(withText(textToBeMatched)).check(matches(isDisplayed()))
    }

    /**
     * Matching by tag
     */
    @Test
    fun testMatchByTag() {
        // ARRANGE
        val tagToBeFound = "demoTextTag"
        val textToBeMatched = "Hello World!"
        // ACT AND ASSERT
        onView(withTagValue(`is`(tagToBeFound))).check(matches(withText(textToBeMatched)))
    }


}