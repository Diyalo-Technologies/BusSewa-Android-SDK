package com.diyalotech.bussewasdk.network.retrofit

import com.diyalotech.bussewasdk.network.dto.RouteListDTO
import com.diyalotech.bussewasdk.network.dto.SeatLayoutDTO
import com.diyalotech.bussewasdk.network.dto.TripListDTO
import com.diyalotech.bussewasdk.network.dto.TripListRequestDTO
import retrofit2.http.*

interface ApiService {

    @GET("booking/routes/v2/")
    suspend fun getRoutes(): RouteListDTO

    @POST("booking/trips/v2/")
    suspend fun findTrips(@Body requestDTO: TripListRequestDTO): TripListDTO

    @GET("booking/refresh/{id}/")
    suspend fun refreshDetails(@Path("id") id: String): SeatLayoutDTO
}