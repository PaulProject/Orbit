package com.example.orbit.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.orbit.view.dialog.CountryDialog
import com.example.orbit.view.item.CountryItem
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CountryListScreen(viewModel: CountryListViewModel) {
    var name: String? by remember { mutableStateOf(null) }
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect { effect ->
        when (effect) {
            is CountryListSideEffect.ShowNameOfCountryDialog -> name = effect.name
        }
    }
    CountryListScreen(
        state = state,
        onCountryClick = viewModel::onCountryClick,
        onRetry = viewModel::onRetry
    )
    name?.let {
        CountryDialog(it) {
            name = null
        }
    }
}

@Composable
private fun CountryListScreen(
    state: CountryListState,
    onCountryClick: (item: CountryItem) -> Unit,
    onRetry: () -> Unit
) = if (state.error != null) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            content = {
                Text(text = "Retry")
            },
            onClick = onRetry,
        )
    }
} else {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(state.items) { item ->
            CountryItem(
                item = item,
                onCountryClick = onCountryClick
            )
        }
    }
}

@Composable
private fun CountryItem(
    item: CountryItem,
    onCountryClick: (item: CountryItem) -> Unit,
) {
    AsyncImage(
        modifier = Modifier.clickable {
            onCountryClick(item)
        },
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.image)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
    )
}