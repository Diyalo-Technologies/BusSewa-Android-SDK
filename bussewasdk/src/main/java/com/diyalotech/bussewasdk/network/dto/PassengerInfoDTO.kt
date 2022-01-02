package com.diyalotech.bussewasdk.network.dto

//for basic info
data class BasicPassengerInfoDTO(
    val boardingPoint: String,
    val contactNumber: String,
    val email: String,
    val id: String,
    val name: String? = null,
    val ticketSrlNo: String,
    val passengerTypeDetail: List<PassengerTypeDetail>? = null,
    val passengerPriceDetail: List<PassengerPriceDetail>? = null
)

data class PassengerTypeDetail(
    val passengerDetail: List<PassengerDetailValues>,
    val seat: String
)

data class PassengerDetailValues(
    var detail: String,
    val id: Int
)

data class PassengerPriceDetail(
    val id: Int,
    val name: String,
    val seat: String
)