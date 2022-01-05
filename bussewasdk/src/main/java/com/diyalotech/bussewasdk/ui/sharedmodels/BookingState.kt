package com.diyalotech.bussewasdk.ui.sharedmodels

internal sealed class BookingState {
    object Init : BookingState()
    object Loading : BookingState()
}