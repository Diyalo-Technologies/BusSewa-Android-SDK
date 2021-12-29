package com.diyalotech.bussewasdk.ui.searchtrip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.repo.DataStoreRepository
import com.diyalotech.bussewasdk.repo.LocationType
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

data class SearchTripModel(val source: String, val destination: String, val date: LocalDate)

class SearchTripViewModel(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    val searchTripState = dataStoreRepository.searchTripStore

    fun setSearchDate(date: LocalDate) {
        dataStoreRepository.setSearchDate(date)
    }

    fun swapLocation() {
        dataStoreRepository.swapLocation()
    }

    fun setLocationSelectionMode(locationSelectionMode: LocationType) {
        viewModelScope.launch {
            dataStoreRepository.setLocationSelectionMode(locationSelectionMode)
        }
    }


}

