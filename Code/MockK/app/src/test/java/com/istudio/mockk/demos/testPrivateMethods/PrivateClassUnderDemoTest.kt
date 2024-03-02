package com.istudio.mockk.demos.testPrivateMethods

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Test

class PrivateClassUnderDemoTest {

    @Test
    fun testPrivateMethod() {

        // <--------------------> Arrange <-------------------->
        val resultToCheck = "Mocked Private result!"
        val privateMethodName = "privateMethod"

        // <--------------------> Act <------------------------>
        // Create a spy of our class
        val ourClass = spyk( PrivateClassUnderDemo() )
        // Stub a private method to return a value
        every { ourClass[privateMethodName]() } returns resultToCheck
        // Call the public method, That will invoke the public method
        val result = ourClass.publicMethod()

        // <--------------------> Assert <-------------------->
        // Assert the result
        assertEquals(resultToCheck, result)

    }


}