package com.diyalotech.bussewasdk.network.dto

import com.diyalotech.bussewasdk.ui.models.BookingStatus

data class SeatLayoutDTO(
    val isLocked: Boolean,
    val noOfColumn: Int,
    val seatLayout: List<SeatLayout>,
    val status: String
)

data class SeatLayout(
    val displayName: String,
    val bookingStatus: String,
    val bookedByCustomer: String
) {
    fun bookingStatusE() =
        if (bookingStatus.equals("na", ignoreCase = true) || bookingStatus.equals(
                "no",
                ignoreCase = true
            )
        ) {
            BookingStatus.AVAILABLE
        } else {
            BookingStatus.BOOKED
        }
}