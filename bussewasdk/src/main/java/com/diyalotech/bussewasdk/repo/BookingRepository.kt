package com.diyalotech.bussewasdk.repo

import com.diyalotech.bussewasdk.network.dto.CancelQueueRequestDTO
import com.diyalotech.bussewasdk.network.dto.PassengerInfoDTO
import com.diyalotech.bussewasdk.network.dto.PassengerPriceDetail
import com.diyalotech.bussewasdk.network.dto.PassengerTypeDetail
import com.diyalotech.bussewasdk.network.retrofit.ApiService
import com.diyalotech.bussewasdk.network.retrofit.safeApiCall
import javax.inject.Inject

internal class BookingRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun fetchInputConfig(tripId: String) = safeApiCall {
        apiService.inputDetailConfig(tripId)
    }

    suspend fun cancelQueue(tripId: String, ticketSrlNo: String) = safeApiCall {
        apiService.cancelQueue(CancelQueueRequestDTO(tripId, ticketSrlNo))
    }


    suspend fun savePassengerInfo(
        id: String,
        ticketSrlNo: String,
        mobile: String,
        boardingPoint: String,
        name: String? = null,
        email: String? = null,
        passengerDetails: List<PassengerTypeDetail>?,
        passengerPriceDetails: List<PassengerPriceDetail>?
    ) = safeApiCall {
        apiService.savePassengerInfo(
            PassengerInfoDTO(
                id = id,
                ticketSrlNo = ticketSrlNo,
                contactNumber = mobile,
                boardingPoint = boardingPoint,
                name = name,
                email = email,
                passengerTypeDetail = passengerDetails,
                passengerPriceDetail = passengerPriceDetails
            )
        )
    }
}