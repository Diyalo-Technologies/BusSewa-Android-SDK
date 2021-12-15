package com.diyalotech.bussewasdk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.diyalotech.bussewasdk.ui.models.LocationType
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripView
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import kotlinx.datetime.LocalDate


@Composable
fun HomeView(
    searchTripViewModel: SearchTripViewModel,
    onDateSelected: (LocalDate?) -> Unit,
    onLocationClicked: (LocationType) -> Unit,
    onSearchClicked: () -> Unit
) {
    BusSewaSDKTheme {

        Column {
            val searchTripModel = searchTripViewModel.searchTripState.getSearchTripModel()
            TopAppBar(
                title = "BusSewa - Find trips",
                showBack = true
            ) {

            }
            SearchTripView(
                searchTripModel,
                onDateSelected,
                { searchTripViewModel.swapLocation() },
                onLocationClicked,
                onSearchClicked
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusSewaSDKTheme {
        /*val searchTripViewModel = SearchTripViewModel()
        Greeting(searchTripViewModel, {}) {}*/
    }
}