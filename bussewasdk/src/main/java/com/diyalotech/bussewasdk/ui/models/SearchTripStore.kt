package com.diyalotech.bussewasdk.ui.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import com.diyalotech.bussewasdk.utils.localDateNow
import kotlinx.datetime.LocalDate

class SearchTripStore {
    private var source by mutableStateOf("")
    private var destination by mutableStateOf("")
    private var date by mutableStateOf(localDateNow())
    private var selectionMode by mutableStateOf(LocationType.SOURCE)

    fun saveSource(source: String) {
        this.source = source
        println("Saving: $source")
    }

    fun saveDestination(destination: String) {
        this.destination = destination
    }

    fun saveDate(date: LocalDate) {
        this.date = date
    }

    fun getSearchTripModel(): SearchTripModel {
        val temp = SearchTripModel(
            source,
            destination,
            date
        )
        println("Reading data: $temp")
        return temp
    }

    fun changeSelectionMode(locationSelectionMode: LocationType) {
        selectionMode = locationSelectionMode
    }

    fun fetchSelectionMode(): LocationType {
        return selectionMode
    }

    fun swapLocation() {
        val source = this.source
        this.source = this.destination
        this.destination = source
    }
}

enum class LocationType {
    SOURCE,
    DESTINATION
}