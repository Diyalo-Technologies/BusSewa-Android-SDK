package com.diyalotech.bussewasdk.ui.locationlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.diyalotech.bussewasdk.repo.SearchParamRepository
import com.diyalotech.bussewasdk.repo.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SearchLocationState {
    object Loading : SearchLocationState()
    class Success(val locationList: List<String>) : SearchLocationState()
}

class SearchLocationViewModel(
    private val locationRepository: LocationRepository,
    private val searchParamRepo: SearchParamRepository
) : ViewModel() {

    init {
        println("Initialized sdlfkjlskdfjl locationsli st")
    }

    //private data store
    private var locationList: List<String> = emptyList()

    private var _searchString = MutableStateFlow("")
    val searchString: StateFlow<String> = _searchString

    private var _uiState = MutableStateFlow<SearchLocationState>(SearchLocationState.Loading)
    val uiState: StateFlow<SearchLocationState> = _uiState

    fun onSearchChanged(searchString: String) {
        _searchString.value = searchString

        if(locationList.isNotEmpty()) {
            val filteredList = locationList.filter {
                it.contains(searchString, true)
            }
            updateLocationList(filteredList)
        }

    }

    init {
        fetchLocationList()
    }

    private fun fetchLocationList() {
        viewModelScope.launch {
            when (val result = locationRepository.fetchLocationList()) {
                is ApiResult.Error -> {
                    println(result.error)
                }
                is ApiResult.Success -> {
                    locationList = result.data.routes
                    updateLocationList(locationList)
                }
            }
        }
    }

    private fun updateLocationList(locationList: List<String>) {
        _uiState.value = SearchLocationState.Success(locationList = locationList)
    }

    fun saveSelectedLocation(selectedLocation: String) {
        searchParamRepo.saveSelectedLocation(selectedLocation)
    }

}