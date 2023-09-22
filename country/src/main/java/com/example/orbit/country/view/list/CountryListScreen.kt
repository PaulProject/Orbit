package com.example.orbit.country.view.list

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.orbit.country.view.list.item.CountryListItem
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun CountryListScreen(viewModel: CountryListViewModel = viewModel()) {
    val state by viewModel.collectAsState()
    CountryListScreen(
        state = state,
        onCountryClick = viewModel::onCountryClick,
        onRetry = viewModel::onRetry
    )
}

@Composable
private fun CountryListScreen(
    state: CountryListState,
    onCountryClick: (item: CountryListItem) -> Unit,
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
    item: CountryListItem,
    onCountryClick: (item: CountryListItem) -> Unit,
) {
    AsyncImage(
        modifier = Modifier.clickable {
            onCountryClick(item)
        },
        model = item.image,
        contentDescription = null,
    )
}