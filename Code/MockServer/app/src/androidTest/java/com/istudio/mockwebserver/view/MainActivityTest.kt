package com.istudio.mockwebserver.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import androidx.test.rule.ActivityTestRule
import org.junit.Test
import org.junit.rules.RuleChain
import java.lang.Thread.sleep


@LargeTest
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        // If any dependencies I am injecting, We can use this, So at the beginning of test all the required dependencies are injected
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun happyPath() {
        // Can already use analyticsAdapter here.
        sleep(15000)

    }
}