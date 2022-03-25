package com.diyalotech.bussewasdk.network.dto

//for basic info
internal data class PassengerInfoDTO(
    val boardingPoint: String,
    val contactNumber: String,
    val email: String? = null,
    val id: String,
    val name: String? = null,
    val ticketSrlNo: String,
    val passengerTypeDetail: List<PassengerTypeDetail>? = null,
    val passengerPriceDetail: List<PassengerPriceDetail>? = null
)

internal data class PassengerTypeDetail(
    val seat: String,
    val passengerDetail: List<PassengerDetailValues>
)

internal data class PassengerDetailValues(
    val id: Int,
    var detail: String
)

internal data class PassengerPriceDetail(
    val id: Int,
    val name: String,
    val seat: String
)