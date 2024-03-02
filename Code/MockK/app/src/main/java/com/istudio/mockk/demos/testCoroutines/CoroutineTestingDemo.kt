package com.istudio.mockk.demos.testCoroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineTestingDemo {


    suspend fun suspendingFunctionDemo() =  coroutineScope {
        delay(100)
        return@coroutineScope "Hello World!"
    }

    suspend fun coroutineLaunchDemo() =  coroutineScope {
        launch {
            delay(100)
        }
    }


}