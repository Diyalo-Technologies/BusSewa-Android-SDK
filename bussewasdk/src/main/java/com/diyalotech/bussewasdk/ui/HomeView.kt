package com.diyalotech.bussewasdk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.diyalotech.bussewasdk.ui.calendar.DatePicker
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripView
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.R


@Composable
fun HomeView(
    searchTripViewModel: SearchTripViewModel,
    onSearchClicked: () -> Unit,
    onLocationClicked: () -> Unit
) {
    val searchTripModel = searchTripViewModel.searchTripState.getSearchTripModel()
    var showDatePicker: Boolean by remember {
        mutableStateOf(false)
    }

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = stringResource(R.string.find_trips_title),
            showBack = true
        ) {

        }
        SearchTripView(
            state = searchTripModel,
            onSearchClicked = onSearchClicked,
            onSwap = {
                searchTripViewModel.swapLocation()
            },
            onDateSelected = {
                if (it == null) {
                    showDatePicker = true
                } else {
                    searchTripViewModel.setSearchDate(it)
                }
            },
            onLocationClicked = {
                searchTripViewModel.setLocationSelectionMode(it)
                onLocationClicked()
            }
        )
    }

    if (showDatePicker) {
        DatePicker(onDateSelected = {
            searchTripViewModel.setSearchDate(it)
        }) {
            showDatePicker = false
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