package com.diyalotech.bussewasdk.repo

import com.diyalotech.bussewasdk.repo.model.SelectedTripDetails
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import kotlinx.datetime.LocalDate

class DataStoreRepository {

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

    fun saveBookingInfo(ticketSrlNo: String, timeoutString: String, boardingPoints: List<String>) {
        tripDataStore.saveBookingInfo(ticketSrlNo, timeoutString, boardingPoints)
    }

    //for multi price only
    fun saveTicketPrice(price: Double) {
        tripDataStore.saveTicketPrice(price)
    }
}