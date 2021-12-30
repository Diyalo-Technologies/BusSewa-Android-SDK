package com.diyalotech.bussewasdk.ui.bookingcustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.diyalotech.bussewasdk.network.dto.InputTypeCode
import com.diyalotech.bussewasdk.network.dto.MultiPrice
import com.diyalotech.bussewasdk.network.dto.PassengerDetail
import com.diyalotech.bussewasdk.repo.BookingRepository
import com.diyalotech.bussewasdk.repo.DataStoreRepository
import com.diyalotech.bussewasdk.ui.NavDirection
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.*
import com.diyalotech.bussewasdk.utils.Validator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class BookingConfirmEvent {
    class Error(val msg: String) : BookingConfirmEvent()
    class Navigation(val direction: NavDirection) : BookingConfirmEvent()
}

class BookingConfirmViewModel(
    private val dataStoreRepo: DataStoreRepository,
    private val bookingRepo: BookingRepository,
) : ViewModel() {

    //prefetch cache
    private lateinit var passengerDetailList: List<PassengerDetail>
    private lateinit var ticketPriceList: List<MultiPrice>

    val tripDataStore = dataStoreRepo.tripDataStore

    private val _uiState = MutableStateFlow<BookingDetailsState>(BookingDetailsState.Loading)
    val uiState: StateFlow<BookingDetailsState> = _uiState

    //for basic details
    var nameState = TextFieldModel()
    var emailState = TextFieldModel()
    var mobileState = TextFieldModel()
    var boardingPointState = TextFieldModel()

    //for multi price type
    private lateinit var passengerPriceDetails: Map<String, PassengerPriceDetail>

    //dynamic passenger details SeatName : FieldHolders
    private lateinit var passengerDetailValues: Map<String, List<DynamicTextFieldModel>>

    private val eventsChannel = Channel<BookingConfirmEvent>()
    val eventsFlow: Flow<BookingConfirmEvent> = eventsChannel.receiveAsFlow()

    init {
        fetchInputConfig()
    }

    private fun fetchInputConfig() {
        viewModelScope.launch {
            val trip = tripDataStore.selectedTripDetails ?: return@launch
            val seats = tripDataStore.selectedSeats
            val result = bookingRepo.fetchInputConfig(trip.id)

            when (result) {
                is ApiResult.Error -> {

                }

                is ApiResult.Success -> {
                    when (trip.inputTypeCode) {
                        InputTypeCode.BASIC -> {
                        }
                        InputTypeCode.DYNAMIC -> {
                            passengerDetailList = result.data.inputDetailList
                            createDynamicFieldHolderList(seats, result.data.inputDetailList)
                        }
                        InputTypeCode.MULTI_PRICE -> {
                            ticketPriceList = result.data.ticketPriceList
                            createMultiPriceFieldHolder(seats)
                        }
                        InputTypeCode.MULTI_DYNAMIC -> TODO()
                    }

                    updateDetailsBasedOnType()
                }
            }
        }
    }

    private fun createMultiPriceFieldHolder(seats: List<String>) {
        val passengerPriceDetails = mutableMapOf<String, PassengerPriceDetail>()
        for (seat in seats) {
            passengerPriceDetails[seat] = PassengerPriceDetail(
                priceFieldModel = PriceFieldModel(),
                nameModel = TextFieldModel()
            )
        }
        this.passengerPriceDetails = passengerPriceDetails
    }

    private fun createDynamicFieldHolderList(
        seats: List<String>,
        passengerDetails: List<PassengerDetail>
    ) {
        val holderList = mutableMapOf<String, List<DynamicTextFieldModel>>()
        for (seat in seats) {
            val paramsList = mutableListOf<DynamicTextFieldModel>()
            passengerDetails.forEach { field ->
                paramsList.add(DynamicTextFieldModel(id = field.typeId))
            }
            holderList[seat] = paramsList
        }
        passengerDetailValues = holderList
    }

    private fun updateDetailsBasedOnType() {
        val trip = tripDataStore.selectedTripDetails ?: return
        val seats = tripDataStore.selectedSeats

        when (trip.inputTypeCode) {
            InputTypeCode.BASIC -> {
                _uiState.value = BookingDetailsState.SuccessBasic(
                    seats,
                    nameState,
                    emailState,
                    mobileState,
                    boardingPointState
                )
            }
            InputTypeCode.DYNAMIC -> {
                _uiState.value = BookingDetailsState.SuccessDynamic(
                    seats,
                    emailState,
                    mobileState,
                    boardingPointState,
                    passengerDetailList,
                    passengerDetailValues
                )
            }
            InputTypeCode.MULTI_PRICE -> {
                _uiState.value = BookingDetailsState.SuccessMulti(
                    seats,
                    emailState,
                    mobileState,
                    boardingPointState,
                    ticketPriceList,
                    passengerPriceDetails
                )
            }
            InputTypeCode.MULTI_DYNAMIC -> TODO()
        }
    }

    fun onBasicDetailsChanged(field: BasicFields, value: String) {
        when (field) {
            BasicFields.NAME -> {
                this.nameState.value = value
            }
            BasicFields.EMAIL -> {
                this.emailState.value = value
            }
            BasicFields.PHONE -> {
                this.mobileState.value = value
                this.mobileState.isError = !Validator.isValidMobile(value)
            }
            BasicFields.BOARDING_POINT -> {
                this.boardingPointState.value = value
            }
        }

        updateDetailsBasedOnType()
    }

    fun onDynamicDetailsChanged(seat: String, id: Int, value: String) {
        val detail = passengerDetailValues[seat] ?: return
        detail.find { it.id == id }?.let {
            it.value = value
        }
    }

    fun onMultiPriceNameChanged(seat: String, value: String) {
        passengerPriceDetails[seat]?.let {
            it.nameModel.value = value
        }
    }

    fun onMultiPricePriceChanged(seat: String, value: MultiPrice) {
        passengerPriceDetails[seat]?.let {
            it.priceFieldModel.value = value
        }
    }

    fun cancelQueue() {
        val trip = tripDataStore.selectedTripDetails ?: return
        val bookingInfo = tripDataStore.bookingInfo
        viewModelScope.launch {
            val result = bookingRepo.cancelQueue(trip.id, bookingInfo.ticketSrlNo)
            when (result) {
                is ApiResult.Error -> TODO()
                is ApiResult.Success -> {
                    eventsChannel.send(BookingConfirmEvent.Navigation(NavDirection.BACKWARD))
                }
            }
        }
    }

}