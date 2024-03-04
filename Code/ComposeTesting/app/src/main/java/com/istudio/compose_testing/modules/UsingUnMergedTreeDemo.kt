package com.istudio.compose_testing.modules

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun UsingUnMergedTreeDemo() {

    Button(
        modifier = Modifier.semantics { contentDescription = "our tag" },
        onClick = {}) {
        Text(text = "Hello")
        Text(text = "World")
    }

}