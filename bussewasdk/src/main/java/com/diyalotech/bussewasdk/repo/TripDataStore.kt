package com.diyalotech.bussewasdk.repo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.diyalotech.bussewasdk.repo.model.BookingInfo
import com.diyalotech.bussewasdk.repo.model.SelectedTripDetails
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import com.diyalotech.bussewasdk.utils.localDateNow
import com.diyalotech.bussewasdk.utils.localTimeNow
import kotlinx.datetime.*
import java.lang.Exception
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

/*
* working in memory cache object managed using singleton.
* */
internal class TripDataStore {
    var source by mutableStateOf("Kathmandu")
        private set
    var destination by mutableStateOf("")
        private set
    var date by mutableStateOf(localDateNow())
        private set
    var selectionMode by mutableStateOf(LocationType.SOURCE)
        private set
    var selectedTripDetails by mutableStateOf<SelectedTripDetails?>(null)
        private set
    var selectedSeats by mutableStateOf(listOf<String>())
        private set
    var bookingInfo = BookingInfo()
        private set
    var ticketPrice by mutableStateOf(0.0)
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

    //for multi price only
    fun saveTicketPrice(price: Double) {
        this.ticketPrice = price
    }

    @OptIn(ExperimentalTime::class)
    fun saveBookingInfo(ticketSrlNo: String, timeoutString: String, boardingPoints: List<String>) {
        this.bookingInfo.ticketSrlNo = ticketSrlNo
        this.bookingInfo.timeout = try {
            val secondsInt = timeoutString.toInt()
            val instant = Clock.System.now().plus(secondsInt.seconds)
            instant.toLocalDateTime(TimeZone.currentSystemDefault())
        } catch (ex: Exception) {
            localTimeNow()
        }
        this.bookingInfo.boardingPoints = boardingPoints
    }
}

enum class LocationType {
    SOURCE,
    DESTINATION
}