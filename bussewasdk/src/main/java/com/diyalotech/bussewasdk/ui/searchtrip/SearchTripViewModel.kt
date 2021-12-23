package com.diyalotech.bussewasdk.ui.searchtrip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.repo.SearchParamRepository
import com.diyalotech.bussewasdk.repo.LocationType
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import javax.inject.Inject

data class SearchTripModel(val source: String, val destination: String, val date: LocalDate)

class SearchTripViewModel(
    private val searchParamRepository: SearchParamRepository
) : ViewModel() {

    val searchTripState = searchParamRepository.searchTripStore

    fun setSearchDate(date: LocalDate) {
        searchParamRepository.setSearchDate(date)
    }

    fun swapLocation() {
        searchParamRepository.swapLocation()
    }

    fun setLocationSelectionMode(locationSelectionMode: LocationType) {
        viewModelScope.launch {
            searchParamRepository.setLocationSelectionMode(locationSelectionMode)
        }
    }


}

