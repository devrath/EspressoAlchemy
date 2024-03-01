package com.istudio.espresso.demos

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.istudio.espresso.R
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.atomic.AtomicBoolean


@RunWith(AndroidJUnit4::class)
@LargeTest
class ResourceIdlingActivityTest {

    // SUT
    @get:Rule
    val activityRule = ActivityScenarioRule(ResourceIdlingActivity::class.java)

    // Our network manager
    private val networkManager = NetworkManager()
    // Create and register the custom IdlingResource
    private val networkIdlingResource = NetworkIdlingResource(networkManager)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(networkIdlingResource)
    }

    @After
    fun tearDown() {
        // Unregister the IdlingResource
        IdlingRegistry.getInstance().unregister(networkIdlingResource)
    }


    @Test
    fun testNetworkRequest() {

        // Perform UI interactions - onClick of button
        Espresso.onView(withId(R.id.btnResourceIdlingId))
            .perform(click())

        // Perform the network request and wait for it to complete
        networkIdlingResource.performNetworkRequestAndWait()

        // Perform assertions
        Espresso.onView(withId(R.id.txtInputFieldId))
            .check(matches(withText("Complete")))
    }



}


class NetworkIdlingResource(private val networkManager: NetworkManager) : IdlingResource {

    private val isIdleNow = AtomicBoolean(true)
    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        // Provide the current class implementation name
        return NetworkIdlingResource::class.java.name
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    override fun isIdleNow(): Boolean {
        return isIdleNow.get()
    }

    fun performNetworkRequestAndWait() {
        isIdleNow.set(false)

        // Perform the network request
        networkManager.performNetworkRequest {
            // Callback when the network request is complete
            isIdleNow.set(true)

            callback?.onTransitionToIdle()
        }
    }

}