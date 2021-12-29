package com.diyalotech.bussewasdk.repo

import com.diyalotech.bussewasdk.network.dto.BookSeatsRequestDTO
import com.diyalotech.bussewasdk.network.dto.SeatLayout
import com.diyalotech.bussewasdk.network.dto.SeatLayoutDTO
import com.diyalotech.bussewasdk.network.dto.TripListRequestDTO
import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.diyalotech.bussewasdk.network.retrofit.safeApiCall
import com.diyalotech.bussewasdk.ui.seatlayout.testString
import com.google.gson.Gson
import kotlinx.coroutines.delay
import javax.inject.Inject

class TripRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun findTrips(from: String, to: String,/*yyyy-MM-dd*/ date: String) = safeApiCall {
        delay(100)
        apiService.findTrips(
            TripListRequestDTO(from, to, date)
        )
    }

    suspend fun refreshDetails(id: String) = safeApiCall {
        apiService.refreshDetails(id)
    }

    suspend fun bookTrip(id: String, seats: List<String>) = safeApiCall {
        apiService.bookSeats(BookSeatsRequestDTO(
            id = id,
            seat = seats
        ))
    }
}