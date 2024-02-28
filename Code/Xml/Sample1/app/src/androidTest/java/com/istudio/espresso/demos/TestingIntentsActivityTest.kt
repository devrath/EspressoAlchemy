package com.istudio.espresso.demos

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.matcher.IntentMatchers.isInternal
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.truth.content.IntentSubject.assertThat
import androidx.test.rule.GrantPermissionRule
import com.istudio.espresso.R
import com.istudio.espresso.demos.helper_activities.ContactsActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TestingIntentsActivityTest {
    // Define the phone number
    private val VALID_PHONE_NUMBER = "9844802176"
    // Define the Uri=Composed of phone number
    private val INTENT_DATA_PHONE_NUMBER = Uri.parse("tel:$VALID_PHONE_NUMBER")
    // Assume that the phone permission is granted
    @get:Rule var grantPermissionRule = GrantPermissionRule.grant("android.permission.CALL_PHONE")


    private lateinit var scenario: ActivityScenario<TestingIntentsActivity>

    @Before
    fun setUp() {
        // Initialize Intents before each test
        Intents.init()
        // Start the activity
        scenario = ActivityScenario.launch(TestingIntentsActivity::class.java)



        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(
            not(isInternal())
        ).respondWith(
            Instrumentation.ActivityResult(Activity.RESULT_OK, null)
        )
    }

    @Test
    fun typeNumber_ValidInput_InitiatesCall() {
        // Types a phone number into the dialer edit text field and presses the call button.
        onView(withId(R.id.edit_text_caller_number))
            .perform(typeText(VALID_PHONE_NUMBER), closeSoftKeyboard())
        onView(withId(R.id.button_call_number)).perform(click())

        // Verify that an intent to the dialer was sent with the correct action, phone
        // number and package. Think of Intents intended API as the equivalent to Mockito's verify.
        intended(
            allOf(
                hasAction(Intent.ACTION_CALL),
                hasData(INTENT_DATA_PHONE_NUMBER)
            )
        )
    }

    /**
     * Duplicate typeNumber_ValidInput_InitiatesCall, but using truth assertions
     */
    @Test
    fun typeNumber_ValidInput_InitiatesCall_truth() {
        // Types a phone number into the dialer edit text field and presses the call button.
        onView(withId(R.id.edit_text_caller_number))
            .perform(typeText(VALID_PHONE_NUMBER), closeSoftKeyboard())
        onView(withId(R.id.button_call_number)).perform(click())

        // Verify that an intent to the dialer was sent with the correct action, phone
        // number and package.
        val receivedIntent: Intent = Iterables.getOnlyElement(Intents.getIntents()) as Intent

        assertThat(receivedIntent).hasAction(Intent.ACTION_CALL);
        assertThat(receivedIntent).hasData(INTENT_DATA_PHONE_NUMBER);
    }

    @Test
    fun pickContactButton_click_SelectsPhoneNumber() {
        // Stub all Intents to ContactsActivity to return VALID_PHONE_NUMBER. Note that the Activity
        // is never launched and result is stubbed.
        intending(hasComponent(hasShortClassName(".ContactsActivity")))
            .respondWith(
                Instrumentation.ActivityResult(
                    Activity.RESULT_OK,
                    ContactsActivity.createResultData(VALID_PHONE_NUMBER)
                )
            )

        // Click the pick contact button.
        onView(withId(R.id.button_pick_contact)).perform(click())

        // Check that the number is displayed in the UI.
        onView(withId(R.id.edit_text_caller_number))
            .check(matches(withText(VALID_PHONE_NUMBER)))
    }

    @After
    fun tearDown() {
        // Close the activity and release resources
        scenario.close()
        // Release Intents after each test
        Intents.release()
    }


}