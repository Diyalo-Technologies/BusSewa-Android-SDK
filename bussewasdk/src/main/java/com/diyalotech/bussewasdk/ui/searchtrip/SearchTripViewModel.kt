package com.diyalotech.bussewasdk.ui.searchtrip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.repo.DataStoreRepository
import com.diyalotech.bussewasdk.repo.LocationType
import com.diyalotech.bussewasdk.ui.NavDirection
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.ErrorModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

internal sealed class SearchTripEvent {
    class Navigation(val direction: NavDirection) : SearchTripEvent()
}

internal class SearchTripViewModel(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    val searchTripState = dataStoreRepository.tripDataStore
    val sourceErrorModel = ErrorModel()
    val destinationErrorModel = ErrorModel()

    private val eventsChannel = Channel<SearchTripEvent>()
    val eventsFlow: Flow<SearchTripEvent> = eventsChannel.receiveAsFlow()

    fun setSearchDate(date: LocalDate) {
        dataStoreRepository.saveDate(date)
    }

    fun swapLocation() {
        dataStoreRepository.swapLocation()
        sourceErrorModel.clearError()
        destinationErrorModel.clearError()
    }

    fun onSearchClicked() {
        if (searchTripState.source.isBlank()) {
            sourceErrorModel.setError("Please select source location.")
            return
        }
        if (searchTripState.destination.isBlank()) {
            destinationErrorModel.setError("Please select destination.")
            return
        }

        viewModelScope.launch {
            eventsChannel.send(SearchTripEvent.Navigation(NavDirection.FORWARD))
        }
    }

    fun setLocationSelectionMode(locationSelectionMode: LocationType) {
        viewModelScope.launch {
            dataStoreRepository.setLocationSelectionMode(locationSelectionMode)
        }
        sourceErrorModel.clearError()
        destinationErrorModel.clearError()
    }
}

