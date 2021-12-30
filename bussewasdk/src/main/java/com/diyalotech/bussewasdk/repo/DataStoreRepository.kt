package com.diyalotech.bussewasdk.repo

import com.diyalotech.bussewasdk.repo.model.BookingInfo
import com.diyalotech.bussewasdk.repo.model.SelectedTripDetails
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import com.diyalotech.bussewasdk.utils.localTimeNow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.lang.Exception
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

class DataStoreRepository {

    init {
        println("Instance created")
    }

    val tripDataStore = TripDataStore()

    fun setLocationSelectionMode(locationSelectionMode: LocationType) {
        tripDataStore.changeSelectionMode(locationSelectionMode)
    }

    fun saveSelectedLocation(selectedLocation: String) {
        tripDataStore.saveRoute(selectedLocation)
    }

    fun saveDate(date: LocalDate) {
        tripDataStore.saveDate(date)
    }

    fun swapLocation() {
        tripDataStore.swapLocation()
    }

    fun getSearchModel(): SearchTripModel {
        return tripDataStore.getSearchTripModel()
    }

    fun setSelectedTrip(trip: SelectedTripDetails) {
        tripDataStore.saveSelectedTrip(trip)
    }

    fun saveSelectedSeats(seats: List<String>) {
        tripDataStore.saveSelectedSeats(seats)
    }

    @OptIn(ExperimentalTime::class)
    fun saveBookingInfo(ticketSrlNo: String, timeoutString: String, boardingPoints: List<String>) {

        val bookingInfo = BookingInfo(
            ticketSrlNo = ticketSrlNo,
            boardingPoints = boardingPoints,
            timeout = try {
                val secondsInt = timeoutString.toInt()
                val instant = Clock.System.now().plus(secondsInt.seconds)
                instant.toLocalDateTime(TimeZone.currentSystemDefault())
            } catch (ex: Exception) {
                localTimeNow()
            }
        )

        tripDataStore.saveBookingInfo(bookingInfo)
    }
}