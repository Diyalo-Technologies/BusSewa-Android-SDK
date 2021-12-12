package com.diyalotech.bussewasdk.ui.searchtrip

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

data class SearchTripModel(val source: String, val destination: String, val date: LocalDate?)

class SearchTripViewModel : ViewModel() {

    private val _searchTripState: MutableStateFlow<SearchTripModel> = MutableStateFlow(
        SearchTripModel("", "", null)
    )
    val searchTripState: StateFlow<SearchTripModel>
        get() = _searchTripState

    fun setSourceLocation(location: String) {
        _searchTripState.value = searchTripState.value.copy(source = location)
    }

    fun setDestinationLocation(location: String) {
        _searchTripState.value = searchTripState.value.copy(destination = location)
    }

    fun setSearchDate(date: LocalDate) {
        _searchTripState.value = searchTripState.value.copy(date = date)
    }

}

