package com.istudio.mockwebserver.view

import androidx.multidex.BuildConfig
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.istudio.mockwebserver.di.AppModule
import com.istudio.mockwebserver.network.ApiService
import com.istudio.mockwebserver.utils.TestHelpers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.HeldCertificate
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Thread.sleep
import java.net.InetAddress


@LargeTest
@UninstallModules(AppModule::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)



    companion object {

        private lateinit var mockWebServer: MockWebServer
        lateinit var baseUrl: String
        lateinit var localhostCertificate: HeldCertificate
        lateinit var serverCertificates: HandshakeCertificates
        lateinit var clientCertificates: HandshakeCertificates
        private val helpers = TestHelpers()

        @BeforeClass
        @JvmStatic
        fun setup() {

            val mockResponse : MockResponse = MockResponse().apply {
                setResponseCode(200)
                setBody(helpers.getStringFromFile("movies.json"))
            }

            localhostCertificate = helpers.localhostCertificate()
            serverCertificates =  helpers.serverCertificate(localhostCertificate)
            clientCertificates = helpers.clientCertificate(localhostCertificate)
            mockWebServer = helpers.prepareMockServer(serverCertificates,mockResponse)


            mockWebServer.start()

            baseUrl = mockWebServer.url("").toString()
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    class TestAppModule {

        @Provides
        fun provideCustomerService(): ApiService {
            var clientBuilder = OkHttpClient.Builder()

            clientBuilder.sslSocketFactory(
                clientCertificates.sslSocketFactory(),
                clientCertificates.trustManager
            )

            if (BuildConfig.DEBUG) {
                clientBuilder.addNetworkInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    ))
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
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