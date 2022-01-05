package com.diyalotech.bussewasdk.ui.bookingcustomer.models

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.diyalotech.bussewasdk.network.dto.MultiPrice
import com.diyalotech.bussewasdk.network.dto.PassengerDetail

@Stable
internal open class TextFieldModel {
    var value by mutableStateOf("")
    var isError by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun clearError() {
        isError = false
        errorMessage = ""
    }
}

@Stable
internal class PriceFieldModel {
    var value by mutableStateOf<MultiPrice?>(null)
    var isError by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun clearError() {
        isError = false
        errorMessage = ""
    }
}

@Stable
internal class DynamicTextFieldModel(val id: Int) : TextFieldModel()

@Stable
internal data class PassengerPriceValues(
    var priceFieldModel: PriceFieldModel,
    var nameModel: TextFieldModel
)

internal enum class BasicFields {
    NAME, EMAIL, PHONE, BOARDING_POINT
}

//ui state holder
internal sealed class BookingDetailsState() {
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
        val passengerPriceValues: Map<String, PassengerPriceValues>,
    ) : BookingDetailsState()

    class SuccessMultiDynamic(
        val seatList: List<String>,
        val emailState: TextFieldModel,
        val mobileState: TextFieldModel,
        val boardingPoint: TextFieldModel,
        val priceList: List<MultiPrice>,
        val passengerPriceValues: Map<String, PassengerPriceValues>,
        val passengerDetail: List<PassengerDetail>,
        val valuesHolder: Map<String, List<DynamicTextFieldModel>>,
    ) : BookingDetailsState()

    class Error(val message: String) : BookingDetailsState()
}