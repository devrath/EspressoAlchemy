package com.istudio.compose_testing.modules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.istudio.compose_testing.Greeting
import com.istudio.compose_testing.ui.theme.ComposetestingTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DisplayButtonDemoKtTest {

    // This rule is used to test if there os just a composable we are testing without activity
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun myTest() {

        // <------------------------ ASSIGN ------------------------>
        val textToBeMatched = "Hello World"

        // Set the content
        composeTestRule.setContent {
            ComposetestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayButtonDemo()
                }
            }
        }

        // <------------------------ ACT -------------------------->
        // Find the composable on the UI tree
        composeTestRule.onNodeWithText(textToBeMatched).isDisplayed()

    }



}