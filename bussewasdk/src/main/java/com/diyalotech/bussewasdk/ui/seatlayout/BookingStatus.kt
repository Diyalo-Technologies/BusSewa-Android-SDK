package com.diyalotech.bussewasdk.ui.seatlayout

import com.google.gson.annotations.SerializedName

internal enum class BookingStatus {
    @SerializedName("yes")
    BOOKED,
    @SerializedName("na", alternate = ["no"])
    AVAILABLE
}