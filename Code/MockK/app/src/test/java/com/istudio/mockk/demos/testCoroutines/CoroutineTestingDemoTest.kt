package com.istudio.mockk.demos.testCoroutines

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestingDemoTest {


    @Test
    fun suspendingFunctionDemo() = runTest {
        val mock = mockk<CoroutineTestingDemo>()
        // Mocking a suspending function
        coEvery { mock.suspendingFunctionDemo() } returns "Hello World!"
    }

    @Test
    fun coroutineLaunchDemo() = runTest {
        val mock = mockk<CoroutineTestingDemo>()
        // Mocking a coroutine function
        mock.coroutineLaunchDemo()
        // Advance the time to make sure the coroutine executes
        advanceTimeBy(100)
        // Verify that the coroutine was launched
        coVerify { mock.coroutineLaunchDemo() }
    }

}