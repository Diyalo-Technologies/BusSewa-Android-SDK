package com.diyalotech.bussewasdk.ui.searchtrip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.repo.SearchParamRepository
import com.diyalotech.bussewasdk.ui.models.LocationType
import com.diyalotech.bussewasdk.utils.localDateNow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import javax.inject.Inject

data class SearchTripModel(val source: String, val destination: String, val date: LocalDate)

@HiltViewModel
class SearchTripViewModel @Inject constructor(
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

