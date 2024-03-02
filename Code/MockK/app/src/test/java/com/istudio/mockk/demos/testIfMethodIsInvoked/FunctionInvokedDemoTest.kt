package com.istudio.mockk.demos.testIfMethodIsInvoked

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class FunctionInvokedDemoTest {


    @Test
    fun testMethodInvocation() {
        // <--------------------> Arrange <-------------------->
        // SUT
        val sut = mockk<FunctionInvokedDemo>()
        // Tell the SUT with Mockk that the method is invoked functionality
        every { sut.myMethod() } returns Unit
        // <--------------------> Act <------------------------>
        sut.myMethod()
        sut.myMethod()
        // <--------------------> Assert <--------------------->
        verify(exactly = 2) { sut.myMethod() }
    }


}