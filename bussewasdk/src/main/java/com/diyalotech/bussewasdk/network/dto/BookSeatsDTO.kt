package com.diyalotech.bussewasdk.network.dto

data class BookSeatsDTO(
    val status: Int,
    val timeOut: String,
    val ticketSrlNo: String,
    val boardingPoints: List<String>
)

data class BookSeatsRequestDTO(
    val id: String,
    val seat: List<String>
)