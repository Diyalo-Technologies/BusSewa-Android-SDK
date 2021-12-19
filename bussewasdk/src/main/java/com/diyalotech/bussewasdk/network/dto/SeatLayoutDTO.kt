package com.diyalotech.bussewasdk.network.dto

import com.diyalotech.bussewasdk.ui.models.BookingStatus
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatModel

data class SeatLayoutDTO(
    val isLocked: Boolean,
    val noOfColumn: Int,
    val price: Double,
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

fun SeatLayoutDTO.getSelectedSeatModel() : SelectSeatModel {
    return SelectSeatModel(
        isLocked = isLocked,
        noOfColumn = noOfColumn,
        seatLayout = seatLayout,
        price = price
    )
}