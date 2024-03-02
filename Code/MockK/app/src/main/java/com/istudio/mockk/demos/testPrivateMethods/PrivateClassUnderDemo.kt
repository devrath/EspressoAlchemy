package com.istudio.mockk.demos.testPrivateMethods

class PrivateClassUnderDemo {

    private fun privateMethod(): String {
        return "Hello, Private!"
    }

    fun publicMethod(): String {
        return privateMethod()
    }

}