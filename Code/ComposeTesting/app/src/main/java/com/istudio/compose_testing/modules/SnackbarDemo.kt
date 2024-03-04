package com.istudio.compose_testing.modules

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DemoSnackBar() {

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(content = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {

                coroutineScope.launch {
                    // Launch the snack-bar in the co-routine
                    val snackBarResult = snackBarHostState.showSnackbar(
                        message = "SnackBarDemo",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Short
                    )
                    when (snackBarResult) {
                        SnackbarResult.ActionPerformed -> {
                            Log.d("Snackbar", "Action Performed")
                        }

                        else -> {
                            Log.d("Snackbar", "Snackbar dismissed")
                        }
                    }
                }

            }) {
                Text(text = "Show Snack Bar", color = Color.White)
            }
        }
    }, snackbarHost = { SnackbarHost(hostState = snackBarHostState) })
}