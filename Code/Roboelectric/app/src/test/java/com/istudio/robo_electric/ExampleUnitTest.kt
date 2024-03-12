package com.istudio.robo_electric

import android.content.Context
import android.widget.Button
import androidx.test.core.app.ApplicationProvider
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ExampleUnitTest {

    //private lateinit var activity: MainActivity
    private lateinit var context: Context

    @Before
    fun setUp() {
        //activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        context = ApplicationProvider.getApplicationContext()
    }
    /*@Test
    fun testActivity() {
        val stringResource = "Hello World!"
        // Simulate a button click
        activity.findViewById<Button>(R.id.my_button).performClick()
        // Verify a text view's text
        val btnTxt = activity.findViewById<Button>(R.id.my_button).text.toString()
        assertThat(btnTxt).isEqualTo(stringResource)
    }
*/
    @Test
    fun testStringResource() {
        val stringResource = "Hello World!"
        // Get a string resource using the shadow context
        val resourceId: Int = com.istudio.robo_electric.R.string.app_name
        // Retrieve the string from the resource
        val stringValue = RuntimeEnvironment.application.getString(resourceId)
        // Verify the string resource value
        assertThat(stringValue).isEqualTo(stringResource)
    }

}