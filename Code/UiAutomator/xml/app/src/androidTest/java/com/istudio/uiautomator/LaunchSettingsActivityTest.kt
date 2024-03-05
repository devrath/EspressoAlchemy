package com.istudio.uiautomator

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LaunchSettingsActivityTest {

    @Test
    fun launchSettingsScreen() {
        // Get the context of the app under test
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Specify the package name for the device settings
        val settingsPackageName = "com.android.settings"

        // Launch the settings screen
        launchSettingsScreen(appContext, settingsPackageName)
    }

    private fun launchSettingsScreen(context: Context, packageName: String) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Launch the settings app
        val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
        context.startActivity(launchIntent)

        // Wait for the settings app to launch
        Thread.sleep(3000) // Adjust the duration based on the settings app launch time

        // Perform additional actions if needed on the settings screen
        try {
            // For example, let's click on the "Display" option in the settings
            val displayOption = device.findObject(UiSelector().text("Apps"))
            displayOption.click()

            // You can continue interacting with UI elements on the settings screen
            // Add more actions as per your testing requirements
        } catch (e: UiObjectNotFoundException) {
            e.printStackTrace()
        }
    }


}