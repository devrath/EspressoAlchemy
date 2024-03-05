package com.istudio.uiautomator

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchActivityTest {

    @Test
    fun launchApp() {
        // Get the context of the app under test
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Specify the package name of the app you want to launch
        val appPackageName = "com.istudio.uiautomator"

        // Launch the app
        appContext.startActivity(appContext.packageManager.getLaunchIntentForPackage(appPackageName))

        // Wait for the app to launch (you may adjust the duration as needed)
        Thread.sleep(5000) // Wait for 5 seconds in this example

        // Perform additional actions if needed after launching the app
        // For example, you might interact with UI elements on the initial screen
    }

}