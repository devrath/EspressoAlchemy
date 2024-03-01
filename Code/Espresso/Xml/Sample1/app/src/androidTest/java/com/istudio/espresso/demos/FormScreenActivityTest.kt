package com.istudio.espresso.demos

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.istudio.espresso.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FormScreenActivityTest {

    // SUT
    @get:Rule
    val activityRule = ActivityScenarioRule(FormScreenActivity::class.java)

    /**
     * Matching the text by ID
     */
    @Test
    fun testDemo() {
        // <-------- ARRANGE -------->
        val textToBeTypedForTest = "Hello Everyone"
        val textToBeMatched = "Updated text"

        // <-------- ACT ------------>
        // Do the actions on the edittext
        onView(withId(R.id.edtInputFieldId))
            // Type the text
            .perform(typeText(textToBeTypedForTest))
            // Close the keyboard
            .perform(closeSoftKeyboard())

        // Do the actions on the button on the screen
        onView(withId(R.id.btnTextCheckId))
            // Click the button
            .perform(click())

        // <-------- ASSERT ----------->
        // Check the new text matches the required match
        onView(withId(R.id.edtInputFieldId)).check(matches(withText(textToBeMatched)))
    }


}