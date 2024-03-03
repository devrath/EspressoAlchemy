package com.istudio.mockwebserver.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.istudio.mockwebserver.ui.composables.ErrorComposable
import com.istudio.mockwebserver.ui.composables.ListOfMoviesComposable
import com.istudio.mockwebserver.ui.composables.LoadingComposable
import com.istudio.mockwebserver.ui.theme.MockWebServerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MockWebServerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyComposable()
                }
            }
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun MyComposable() {

        val viewModel: MainViewModel = hiltViewModel()

        when (val dataState =  viewModel.dataState.value) {

            is DataState.Loading -> {
                // Show loading UI
                LoadingComposable()
            }
            is DataState.Success -> {
                // Display the fetched data
                ListOfMoviesComposable(dataState)
            }
            is DataState.Error -> {
                // Show error UI
                ErrorComposable(dataState)
            }
            else -> {}
        }
    }


}