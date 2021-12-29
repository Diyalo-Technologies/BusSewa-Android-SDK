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
    private var source by mutableStateOf("Kathmandu")
    private var destination by mutableStateOf("Pokhara")
    private var date by mutableStateOf(localDateNow())
    private var selectionMode by mutableStateOf(LocationType.SOURCE)
    private var selectedTripDetails by mutableStateOf<SelectedTripDetails?>(null)
    private var selectedSeats by mutableStateOf(listOf<String>())
    private var bookingInfo by mutableStateOf<BookingInfo?>(null)

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
        return temp
    }

    fun saveSelectedTrip(trip: SelectedTripDetails) {
        this.selectedTripDetails = trip
    }

    fun getSelectedTrip(): SelectedTripDetails? {
        return this.selectedTripDetails
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

    fun saveBookingInfo(bookingInfo: BookingInfo) {
        this.bookingInfo = bookingInfo
    }

    fun fetchBookingInfo(): BookingInfo? {
        return bookingInfo
    }


}

enum class LocationType {
    SOURCE,
    DESTINATION
}