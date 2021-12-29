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

    val searchTripStore = TripDataStore()

    suspend fun setLocationSelectionMode(locationSelectionMode: LocationType) {
        searchTripStore.changeSelectionMode(locationSelectionMode)
    }

    fun saveSelectedLocation(selectedLocation: String) {
        when (searchTripStore.fetchSelectionMode()) {
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

    fun setSelectedTrip(trip: SelectedTripDetails) {
        searchTripStore.saveSelectedTrip(trip)
    }

    fun getSelectedTrip(): SelectedTripDetails? {
        return searchTripStore.getSelectedTrip()
    }

    fun saveSelectedSeats(seats: List<String>) {
        searchTripStore.saveSelectedSeats(seats)
    }

    fun getSelectedSeats(): List<String> {
        return searchTripStore.fetchSelectedSeats()
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

        searchTripStore.saveBookingInfo(bookingInfo)
    }

    fun getBookingInfo(): BookingInfo? {
        return searchTripStore.fetchBookingInfo()
    }
}