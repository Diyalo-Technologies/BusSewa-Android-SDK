package com.diyalotech.bussewasdk.repo

import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.diyalotech.bussewasdk.network.retrofit.safeApiCall
import javax.inject.Inject

class BookingRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun fetchInputConfig(tripId: String) = safeApiCall {
        apiService.inputDetailConfig(tripId)
    }
}