package com.diyalotech.bussewasdk.repo

import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.diyalotech.bussewasdk.network.dto.RouteListDTO
import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.diyalotech.bussewasdk.network.retrofit.safeApiCall
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val apiService: ApiService
) {

    private var apiCache: ApiResult<RouteListDTO>? = null

    suspend fun fetchLocationList(): ApiResult<RouteListDTO> {
        if (apiCache == null) {
            apiCache = safeApiCall {
                apiService.getRoutes()
            }
        }

        return apiCache!!
    }
}