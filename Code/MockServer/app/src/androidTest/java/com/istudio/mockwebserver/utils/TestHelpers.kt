package com.istudio.mockwebserver.utils

import androidx.multidex.BuildConfig
import androidx.test.platform.app.InstrumentationRegistry
import com.istudio.mockwebserver.view.MainActivityTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.HeldCertificate
import java.io.IOException
import java.io.InputStreamReader
import java.net.InetAddress

class TestHelpers(){

    fun localhostCertificate(): HeldCertificate {
        val localhost: String = InetAddress.getByName("localhost").canonicalHostName
        return HeldCertificate.Builder()
            .addSubjectAlternativeName(localhost)
            .build()
    }

    fun serverCertificate(localhostCertificate: HeldCertificate): HandshakeCertificates {
        return HandshakeCertificates.Builder()
            .heldCertificate(localhostCertificate)
            .build()
    }

    fun clientCertificate(localhostCertificate: HeldCertificate): HandshakeCertificates {
        return HandshakeCertificates.Builder()
            .addTrustedCertificate(localhostCertificate.certificate)
            .build()
    }


    fun prepareMockServer(
        serverCertificates: HandshakeCertificates,
        mockResponse: MockResponse
    ): MockWebServer {
        val mockWebServer = MockWebServer()
        mockWebServer.useHttps(serverCertificates.sslSocketFactory(), false)
        mockWebServer.enqueue(mockResponse)
        return mockWebServer
    }

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