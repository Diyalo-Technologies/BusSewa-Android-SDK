package com.diyalotech.bussewasdk.ui.sharedmodels

sealed class BookingState {
    object Init : BookingState()
    object Loading : BookingState()
}