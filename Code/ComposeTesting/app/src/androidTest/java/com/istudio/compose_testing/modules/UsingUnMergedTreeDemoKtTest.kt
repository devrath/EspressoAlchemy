package com.istudio.compose_testing.modules

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class UsingUnMergedTreeDemoKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mergedTreeLog() {

        composeTestRule.setContent {
            UsingUnMergedTreeDemo()
        }

        composeTestRule.onRoot().printToLog("TAG")

    }

    @Test
    fun unMergedTreeLog() {

        composeTestRule.setContent {
            UsingUnMergedTreeDemo()
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")

    }

}