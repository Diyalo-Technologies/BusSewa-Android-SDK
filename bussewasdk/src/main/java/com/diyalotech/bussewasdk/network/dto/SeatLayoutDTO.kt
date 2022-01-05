package com.diyalotech.bussewasdk.network.dto

import com.diyalotech.bussewasdk.ui.seatlayout.BookingStatus
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatModel

internal data class SeatLayoutDTO(
    val isLocked: Boolean,
    val noOfColumn: Int,
    val price: Double,
    val seatLayout: List<SeatLayout>,
    val status: String
)

internal data class SeatLayout(
    val displayName: String,
    val bookingStatus: String = "na",
    val bookedByCustomer: String? = null
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

internal fun SeatLayoutDTO.getSelectedSeatModel(): SelectSeatModel {
    return SelectSeatModel(
        isLocked = isLocked,
        noOfColumn = noOfColumn,
        seatLayout = seatLayout
    )
}