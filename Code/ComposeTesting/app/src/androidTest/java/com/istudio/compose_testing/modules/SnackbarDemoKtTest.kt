package com.istudio.compose_testing.modules

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SnackbarDemoTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun myTest() {
        // <------------------------ ASSIGN ------------------------>
        val buttonText = "Show Snack Bar"
        val snackBarText = "SnackBarDemo"

        composeTestRule.setContent {
            DemoSnackBar()
        }

        // <------------------------ ACT -------------------------->
        composeTestRule.onNodeWithText(buttonText).performClick()
        composeTestRule.onNodeWithText(snackBarText).isDisplayed()
    }


}