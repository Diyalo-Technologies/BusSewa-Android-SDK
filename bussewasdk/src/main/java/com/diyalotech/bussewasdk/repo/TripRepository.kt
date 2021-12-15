package com.diyalotech.bussewasdk.repo

import com.diyalotech.bussewasdk.network.dto.TripListRequestDTO
import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.diyalotech.bussewasdk.network.retrofit.safeApiCall
import com.diyalotech.bussewasdk.ui.searchtrip.SearchTripModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TripRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun findTrips(from: String, to: String,/*yyyy-MM-dd*/ date: String) = safeApiCall {
        apiService.findTrips(
            TripListRequestDTO(
                from, to, date
            )
        )
    }

}