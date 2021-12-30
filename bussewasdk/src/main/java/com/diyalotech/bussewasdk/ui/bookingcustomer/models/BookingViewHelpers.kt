package com.diyalotech.bussewasdk.ui.bookingcustomer.models

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.diyalotech.bussewasdk.network.dto.MultiPrice
import com.diyalotech.bussewasdk.network.dto.PassengerDetail


@Stable
class TextFieldModel {
    var value by mutableStateOf("")
    var isError by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
}

@Stable
class PriceFieldModel {
    var value by mutableStateOf<MultiPrice?>(null)
    var isError by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
}

@Stable
class DynamicTextFieldModel(
    val id: Int
) {
    var value by mutableStateOf("")
    var isError by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
}

@Stable
data class PassengerPriceDetail(
    var priceFieldModel: PriceFieldModel,
    var textFieldModel: TextFieldModel
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