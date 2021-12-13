package com.diyalotech.bussewasdk.ui.locationlist

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
fun SearLocationView(viewModel: SearchLocationViewModel = viewModel(), onBackPressed: () -> Unit) {
    Column {
        SearchBarView(
            text = viewModel.searchString,
            onValueChanged = { viewModel.onSearchChanged(it) },
            onBackPressed = onBackPressed
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchLocationPreview() {
    BusSewaSDKTheme {
        Surface {
            SearLocationView(SearchLocationViewModel()) {

            }
        }
    }
}