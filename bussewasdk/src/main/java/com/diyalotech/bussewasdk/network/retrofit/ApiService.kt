package com.diyalotech.bussewasdk.network.retrofit

import com.diyalotech.bussewasdk.network.dto.*
import retrofit2.http.*

interface ApiService {

    @GET("v2/booking/routes")
    suspend fun getRoutes(): RouteListDTO

    @POST("v2/booking/trips")
    suspend fun findTrips(@Body requestDTO: TripListRequestDTO): TripListDTO

    @GET("v2/booking/refresh/{id}/")
    suspend fun refreshDetails(@Path("id") id: String): SeatLayoutDTO

    @POST("v2/booking/book/")
    suspend fun bookSeats(@Body requestDTO: BookSeatsRequestDTO): BookSeatsDTO

    @GET("v2/booking/inputDetailConfig/{id}/")
    suspend fun inputDetailConfig(@Path("id") id: String): InputDetailConfigDTO
}