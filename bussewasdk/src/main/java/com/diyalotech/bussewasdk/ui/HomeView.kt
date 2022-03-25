package com.diyalotech.bussewasdk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.diyalotech.bussewasdk.DaggerLibraryComponent
import com.diyalotech.bussewasdk.ui.calendar.DatePicker
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripView
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripViewModel
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripEvent
import kotlinx.coroutines.flow.collectLatest


@Composable
internal fun HomeView(
    searchTripViewModel: SearchTripViewModel,
    onNavigateToTrip: () -> Unit,
    onLocationClicked: () -> Unit
) {
    var showDatePicker: Boolean by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        searchTripViewModel.eventsFlow.collectLatest { value ->
            when (value) {
                is SearchTripEvent.Navigation -> {
                    when (value.direction) {
                        NavDirection.FORWARD -> {
                            onNavigateToTrip()
                        }
                        NavDirection.BACKWARD -> TODO()
                    }
                }
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = stringResource(R.string.find_trips_title),
            showBack = true
        ) {

        }
        SearchTripView(
            sourceErrorModel = searchTripViewModel.sourceErrorModel,
            destinationErrorModel = searchTripViewModel.destinationErrorModel,
            tripDataStore = searchTripViewModel.searchTripState,
            onSearchClicked = {
                searchTripViewModel.onSearchClicked()
            },
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
        val component = DaggerLibraryComponent.builder()
            .build()
        val searchTripViewModel = component.getSearchTripVM()
        HomeView(searchTripViewModel, {}) {}
    }
}