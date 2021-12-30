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
import com.diyalotech.bussewasdk.ui.seatlayout.SelectSeatEvents
import com.diyalotech.bussewasdk.utils.Validator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class BookingConfirmEvent {
    class Error(val msg: String): BookingConfirmEvent()
    class Navigation(val direction: NavDirection): BookingConfirmEvent()
}

class BookingConfirmViewModel(
    private val dataStoreRepo: DataStoreRepository,
    private val bookingRepo: BookingRepository,
) : ViewModel() {

    //prefetch cache
    private val bookingInfo = dataStoreRepo.getBookingInfo()
    private lateinit var passengerDetailList: List<PassengerDetail>
    private lateinit var ticketPriceList: List<MultiPrice>

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
    private lateinit var valuesHolder: Map<String, List<DynamicTextFieldModel>>

    private var _boardingPoints = MutableStateFlow(emptyList<String>())
    val boardingPoints: StateFlow<List<String>> = _boardingPoints

    private val eventsChannel = Channel<BookingConfirmEvent>()
    val eventsFlow: Flow<BookingConfirmEvent> = eventsChannel.receiveAsFlow()

    init {
        _boardingPoints.value = bookingInfo?.boardingPoints ?: emptyList()
        fetchInputConfig()
    }

    private fun fetchInputConfig() {
        viewModelScope.launch {
            val trip = dataStoreRepo.getSelectedTrip() ?: return@launch
            val seats = dataStoreRepo.getSelectedSeats()
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
                textFieldModel = TextFieldModel()
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
                paramsList.add(DynamicTextFieldModel(id = field.tyepId))
            }
            holderList[seat] = paramsList
        }
        valuesHolder = holderList
    }

    private fun updateDetailsBasedOnType(){
        val trip = dataStoreRepo.getSelectedTrip() ?: return
        val seats = dataStoreRepo.getSelectedSeats()

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
                    valuesHolder
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

    fun onBasicDetailsChanged(field: BasicFields, value: String){
        when(field) {
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
            BasicFields.BOARDING_POINT ->  {
                this.boardingPointState.value = value
            }
        }

        updateDetailsBasedOnType()
    }

    fun onDynamicDetailsChanged() {

    }

    fun cancelQueue() {
        val trip = dataStoreRepo.getSelectedTrip() ?: return
        bookingInfo?.ticketSrlNo?.let {
            viewModelScope.launch {
                val result = bookingRepo.cancelQueue(trip.id, it)
                when(result) {
                    is ApiResult.Error -> TODO()
                    is ApiResult.Success -> {
                        eventsChannel.send(BookingConfirmEvent.Navigation(NavDirection.BACKWARD))
                    }
                }
            }
        }
    }

}