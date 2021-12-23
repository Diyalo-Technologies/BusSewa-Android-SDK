package com.diyalotech.bussewasdk.repo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import com.diyalotech.bussewasdk.utils.localDateNow
import kotlinx.datetime.LocalDate

class TripDataStore {
    private var source by mutableStateOf("Kathmandu")
    private var destination by mutableStateOf("Pokhara")
    private var date by mutableStateOf(localDateNow())
    private var selectionMode by mutableStateOf(LocationType.SOURCE)
    private var selectedTripId by mutableStateOf("")
    private var selectedSeats by mutableStateOf(listOf<String>())

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

    fun saveSelectedTrip(id: String) {
        this.selectedTripId = id
    }

    fun getSelectedTrip(): String {
        return this.selectedTripId
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

    fun saveSelectedSeats(seats: List<String>) {
        this.selectedSeats = seats
    }

    fun fetchSelectedSeats(): List<String> {
        return selectedSeats
    }
}

enum class LocationType {
    SOURCE,
    DESTINATION
}