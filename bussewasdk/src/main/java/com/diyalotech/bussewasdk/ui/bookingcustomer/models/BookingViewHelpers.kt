package com.diyalotech.bussewasdk.ui.bookingcustomer.models

import com.diyalotech.bussewasdk.network.dto.MultiPrice
import com.diyalotech.bussewasdk.network.dto.PassengerDetail
import com.diyalotech.bussewasdk.network.dto.SeatLayout
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatModel
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatState

data class TextFieldModel(
    val value: String = "",
    val isError: Boolean = false,
    val errorMessage: String = ""
)

data class PassengerPriceDetail(
    val priceFieldModel: PriceFieldModel,
    val textFieldModel: TextFieldModel
)

data class PriceFieldModel(
    val value: MultiPrice? = null,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

data class DynamicTextFieldModel(
    val id: Int,
    val value: String = "",
    val isError: Boolean = false,
    val errorMessage: String = ""
)

enum class BasicFields {
    NAME, EMAIL, PHONE, BOARDING_POINT
}

//ui state holder
sealed class BookingDetailsState() {
    object Loading : BookingDetailsState()
    class SuccessBasic(
        val seatList: List<String>,
        val nameState: TextFieldModel,
        val emailState: TextFieldModel,
        val mobileState: TextFieldModel,
        val boardingPoint: TextFieldModel
    ) : BookingDetailsState()

    class SuccessDynamic(
        val seatList: List<String>,
        val emailState: TextFieldModel,
        val mobileState: TextFieldModel,
        val boardingPoint: TextFieldModel,
        val passengerDetail: List<PassengerDetail>,
        val valuesHolder: Map<String, List<DynamicTextFieldModel>>,
    ) : BookingDetailsState()

    class SuccessMulti(
        val seatList: List<String>,
        val emailState: TextFieldModel,
        val mobileState: TextFieldModel,
        val boardingPoint: TextFieldModel,
        val priceList: List<MultiPrice>,
        val passengerPriceDetails: Map<String, PassengerPriceDetail>,
    ) : BookingDetailsState()

    class SuccessMultiDynamic() : BookingDetailsState()
    class Error(val message: String) : BookingDetailsState()
}