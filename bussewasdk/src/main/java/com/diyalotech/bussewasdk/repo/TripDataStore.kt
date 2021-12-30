package com.diyalotech.bussewasdk.repo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.diyalotech.bussewasdk.network.dto.InputTypeCode
import com.diyalotech.bussewasdk.repo.model.BookingInfo
import com.diyalotech.bussewasdk.repo.model.SelectedTripDetails
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import com.diyalotech.bussewasdk.utils.localDateNow
import com.diyalotech.bussewasdk.utils.localTimeNow
import com.diyalotech.bussewasdk.utils.toLocalInstant
import kotlinx.datetime.*
import java.lang.Exception
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

/*
* working in memory cache object managed using singleton.
* */
class TripDataStore {
    var source by mutableStateOf("Kathmandu")
        private set
    var destination by mutableStateOf("Pokhara")
        private set
    var date by mutableStateOf(localDateNow())
        private set
    var selectionMode by mutableStateOf(LocationType.SOURCE)
        private set
    var selectedTripDetails by mutableStateOf<SelectedTripDetails?>(null)
        private set
    var selectedSeats by mutableStateOf(listOf<String>())
        private set
    var bookingInfo by mutableStateOf<BookingInfo?>(null)
        private set

    fun saveRoute(route: String) {
        when (selectionMode) {
            LocationType.SOURCE -> {
                source = route
            }
            LocationType.DESTINATION -> {
                destination = route
            }
        }
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
        return temp
    }

    fun saveSelectedTrip(trip: SelectedTripDetails) {
        this.selectedTripDetails = trip
    }

    fun changeSelectionMode(locationSelectionMode: LocationType) {
        selectionMode = locationSelectionMode
    }

    fun swapLocation() {
        val source = this.source
        this.source = this.destination
        this.destination = source
    }

    fun saveSelectedSeats(seats: List<String>) {
        this.selectedSeats = seats
    }

    fun saveBookingInfo(bookingInfo: BookingInfo) {
        this.bookingInfo = bookingInfo
    }
}

enum class LocationType {
    SOURCE,
    DESTINATION
}