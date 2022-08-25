package com.diyalotech.bussewasdk.ui.locationlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.diyalotech.bussewasdk.repo.DataStoreRepository
import com.diyalotech.bussewasdk.repo.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal sealed class SearchLocationState {
    object Loading : SearchLocationState()
    class Success(val locationList: List<String>) : SearchLocationState()
    class Error(val msg: String) : SearchLocationState()
}

internal class SearchLocationViewModel(
    private val locationRepository: LocationRepository,
    private val dataStoreRepo: DataStoreRepository
) : ViewModel() {

    init {
        println("Initialized sdlfkjlskdfjl locationsli st")
    }

    //private data store
    private var locationList: List<String> = emptyList()

    var searchString by mutableStateOf("")
        private set

    private var _uiState = MutableStateFlow<SearchLocationState>(SearchLocationState.Loading)
    val uiState: StateFlow<SearchLocationState> = _uiState

    fun onSearchChanged(searchString: String) {
        this.searchString = searchString

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

    fun fetchLocationList() {
        viewModelScope.launch {
            when (val result = locationRepository.fetchLocationList()) {
                is ApiResult.Error -> {
                    _uiState.value = SearchLocationState.Error(result.error)
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
        dataStoreRepo.saveSelectedLocation(selectedLocation)
    }

}