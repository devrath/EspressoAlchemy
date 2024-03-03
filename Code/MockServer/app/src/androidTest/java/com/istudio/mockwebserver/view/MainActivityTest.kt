package com.istudio.mockwebserver.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.istudio.mockwebserver.di.TestAppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.BeforeClass
import org.junit.Test
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Thread.sleep
import java.net.InetAddress


@LargeTest
@UninstallModules(TestAppModule::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    companion object {

        private lateinit var mockWebServer: MockWebServer
        lateinit var baseUrl: String


        @BeforeClass
        @JvmStatic
        fun setup() {

            val localhost: String = InetAddress.getByName("localhost").canonicalHostName

            mockWebServer = MockWebServer()
            mockWebServer.enqueue(
                MockResponse().setResponseCode(200)
                    .setBody(FileReader.getStringFromFile("movies.json"))
            )
            mockWebServer.start()

            baseUrl = mockWebServer.url("").toString()
        }
    }

    object FileReader {

        @Throws(IOException::class)
        fun getStringFromFile(fileName: String): String {
            try {
                val inputStream = InstrumentationRegistry.getInstrumentation()
                    .context.assets.open(fileName)
                val builder = StringBuilder()
                val reader = InputStreamReader(inputStream, "UTF-8")
                reader.readLines().forEach {
                    builder.append(it)
                }
                return builder.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }
    }


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