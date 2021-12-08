package com.diyalotech.bussewasdk.ui.models

import com.google.gson.annotations.SerializedName

enum class BookingStatus {
    @SerializedName("yes")
    BOOKED,
    @SerializedName("na", alternate = ["no"])
    AVAILABLE
}