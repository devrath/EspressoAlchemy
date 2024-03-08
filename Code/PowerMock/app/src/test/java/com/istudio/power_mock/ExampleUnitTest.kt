package com.istudio.power_mock

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Utility::class)
class ExampleUnitTest {

    @Test
    fun testStaticMethod_WithPowerMockito() {
        val call = " Hi there, I'm using PowerMock with Mockito "
        val callExpectation = " Call Expectation for you. "

        PowerMockito.mockStatic(Utility::class.java)
        PowerMockito.doReturn(callExpectation).`when`(Utility.staticMethod(call))

        val actualCall = Utility.staticMethod(call)
        assertEquals(callExpectation, actualCall)
    }
}