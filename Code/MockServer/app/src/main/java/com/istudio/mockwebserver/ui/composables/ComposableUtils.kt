package com.istudio.mockwebserver.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.istudio.mockwebserver.view.DataState

@Composable
fun ErrorComposable(dataState: DataState.Error) {
    Text(text = "Error: ${dataState.errorMessage}")
}

@Composable
fun ListOfMoviesComposable(dataState: DataState.Success) {
    val state = rememberLazyListState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        val resultList = dataState.data

        items(
            count = resultList.size
        ) { count ->
            // Render each item in the lazy list
            val imageUrl = resultList[count].poster ?: ""

            Image(
                painter = rememberImagePainter(
                    data = imageUrl,
                ),
                contentDescription = null, // Provide a meaningful content description
                modifier = Modifier
                    .padding(16.dp)
                    .size(120.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

        }
    }
}

@Composable
fun LoadingComposable() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}