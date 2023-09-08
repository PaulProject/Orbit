package com.example.orbit.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.orbit.R
import com.example.orbit.view.detail.item.CountryDetailItem
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun CountryDetailScreen(viewModel: CountryDetailViewModel = viewModel()) {
    val state by viewModel.collectAsState()
    CountryDetailScreen(
        state = state,
        onBackClick = viewModel::onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CountryDetailScreen(
    state: CountryDetailState,
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .displayCutoutPadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = state.name
                    )
                },
                navigationIcon = {
                    Image(
                        modifier = Modifier.clickable {
                            onBackClick()
                        },
                        painter = painterResource(R.drawable.ic_arrow_black_back),
                        contentDescription = null,
                    )
                },
            )
        },
        content = { paddingValues ->
            when {
                state.isProgress -> CircularProgressIndicator()
                state.item == null -> Text(
                    text = "Detail is empty"
                )

                state.error != null -> Text(
                    text = "Detail has error"
                )

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            AsyncImage(
                                model = state.item.flag,
                                contentDescription = null,
                            )
                            Column {
                                Text(
                                    text = "title",
                                )
                                Text(
                                    text = "description",
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun CountryDetailScreenPreview() {
    CountryDetailScreen(
        CountryDetailState(
            name = "Russia",
            item = CountryDetailItem(
                name = "Russia",
                currency = "RUB",
                capital = "Moscow"
            )
        ),
        onBackClick = {},
    )
}
