package com.diyalotech.bussewasdk.repo

import com.diyalotech.bussewasdk.network.dto.SeatLayoutDTO
import com.diyalotech.bussewasdk.network.dto.TripListRequestDTO
import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.diyalotech.bussewasdk.network.retrofit.safeApiCall
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import com.diyalotech.bussewasdk.ui.seatlayout.testString
import com.google.gson.Gson
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun findTrips(from: String, to: String,/*yyyy-MM-dd*/ date: String) = safeApiCall {
        delay(100)
        apiService.findTrips(
            TripListRequestDTO(from, to, date)
        )
    }

    suspend fun refreshDetails(id: String)  = safeApiCall{
        //apiService.refreshDetails(id)
        Gson().fromJson(testString, SeatLayoutDTO::class.java)
//        println(parsedSeatLayout)
    }
}