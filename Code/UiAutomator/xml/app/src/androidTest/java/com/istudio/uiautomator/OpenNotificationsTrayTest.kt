package com.istudio.uiautomator

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class OpenNotificationsTrayTest {

    @Test
    fun openNotificationsTray() {
        // Get the context of the app under test
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Open the notifications tray
        openNotificationsTray(appContext)
    }

    private fun openNotificationsTray(context: Context) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Swipe down from the top of the screen to open the notifications tray
        try {
            device.swipe(500, 0, 500, 1000, 10) // Adjust the coordinates as needed
        } catch (e: UiObjectNotFoundException) {
            e.printStackTrace()
        }

        // Wait for the notifications tray to open (you may adjust the duration as needed)
        Thread.sleep(2000) // Adjust the duration based on the device's responsiveness
    }
}