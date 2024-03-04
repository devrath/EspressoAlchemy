package com.istudio.compose_testing.modules

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun DisplayButtonDemo() {

    Button(
        modifier = Modifier.semantics { contentDescription = "Hello World" },
        onClick = {}) {
        Text(text = "Hello World!")
    }


}