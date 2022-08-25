package com.diyalotech.bussewasdk.ui.locationlist

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.ui.sharedcomposables.LoadingView
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun SearchLocationView(
    viewModel: SearchLocationViewModel,
    onBackPressed: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    Column {
        Card(elevation = 4.dp, shape = RectangleShape) {
            SearchBarView(
                text = viewModel.searchString,
                requestFocus = true,
                onValueChanged = { viewModel.onSearchChanged(it) },
                onBackPressed = onBackPressed
            )
        }

        Crossfade(targetState = uiState) {
            when (it) {
                is SearchLocationState.Loading -> {
                    LoadingView()
                }
                is SearchLocationState.Success -> {
                    LazyColumn(
                        contentPadding = WindowInsets.navigationBars.asPaddingValues()
                    ) {
                        items(it.locationList) {
                            ListItem(Modifier.clickable {
                                viewModel.saveSelectedLocation(it)
                                onBackPressed()
                            }) {
                                Text(text = it)
                            }
                        }
                    }
                }
                is SearchLocationState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = it.msg,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 32.dp)
                        )
                        OutlinedButton(
                            onClick = {
                                viewModel.fetchLocationList()
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Reload")
                        }
                    }
                }
            }
        }

    }
}

val testLocations = arrayOf(
    "Kathmandu",
    "Pokhara",
    "Bhaktapur",
    "Kavre",
    "Nuwakot",
    "Lamjung",
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchLocationPreview() {
    BusSewaSDKTheme {
        Surface {
            /*SearLocationView(SearchLocationViewModel()) {

            }*/
        }
    }
}