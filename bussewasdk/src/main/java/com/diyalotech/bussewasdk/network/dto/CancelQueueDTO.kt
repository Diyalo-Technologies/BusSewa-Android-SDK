package com.diyalotech.bussewasdk.network.dto

data class CancelQueueRequestDTO(
    val tripId: String,
    val ticketSrlNo: String
)