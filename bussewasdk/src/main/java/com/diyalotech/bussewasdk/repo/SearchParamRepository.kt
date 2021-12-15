package com.diyalotech.bussewasdk.repo

import com.diyalotech.bussewasdk.ui.models.LocationType
import com.diyalotech.bussewasdk.ui.models.SearchTripStore
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import kotlinx.datetime.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchParamRepository @Inject constructor(){
    val searchTripStore = SearchTripStore()

    suspend fun setLocationSelectionMode(locationSelectionMode: LocationType) {
        searchTripStore.changeSelectionMode(locationSelectionMode)
    }

    fun saveSelectedLocation(selectedLocation: String) {
        when(searchTripStore.fetchSelectionMode()) {
            LocationType.SOURCE -> {
                searchTripStore.saveSource(selectedLocation)
            }
            LocationType.DESTINATION -> {
                searchTripStore.saveDestination(selectedLocation)
            }
        }
    }

    fun setSearchDate(date: LocalDate) {
        searchTripStore.saveDate(date)
    }

    fun swapLocation() {
        searchTripStore.swapLocation()
    }

    fun getSearchModel(): SearchTripModel {
        return searchTripStore.getSearchTripModel()
    }
}