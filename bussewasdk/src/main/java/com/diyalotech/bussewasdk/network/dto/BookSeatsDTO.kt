package com.diyalotech.bussewasdk.network.dto

internal data class BookSeatsDTO(
    val status: Int,
    val timeOut: String,
    val ticketSrlNo: String,
    val boardingPoints: List<String>
)

internal data class BookSeatsRequestDTO(
    val id: String,
    val seat: List<String>
)