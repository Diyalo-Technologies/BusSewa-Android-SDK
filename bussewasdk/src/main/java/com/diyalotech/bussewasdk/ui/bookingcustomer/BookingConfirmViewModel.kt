package com.diyalotech.bussewasdk.ui.bookingcustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.diyalotech.bussewasdk.network.dto.InputTypeCode
import com.diyalotech.bussewasdk.network.dto.PassengerDetail
import com.diyalotech.bussewasdk.repo.BookingRepository
import com.diyalotech.bussewasdk.repo.DataStoreRepository
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class BookingConfirmViewModel(
    private val dataStoreRepo: DataStoreRepository,
    private val bookingRepo: BookingRepository,
) : ViewModel() {

    //prefetch cache
    private val bookingInfo = dataStoreRepo.getBookingInfo()

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
                            _uiState.value = BookingDetailsState.SuccessBasic(
                                seats,
                                nameState,
                                emailState,
                                mobileState,
                                boardingPointState
                            )
                        }
                        InputTypeCode.DYNAMIC -> {

                            createDynamicFieldHolderList(seats, result.data.inputDetailList)
                            _uiState.value = BookingDetailsState.SuccessDynamic(
                                seats,
                                emailState,
                                mobileState,
                                boardingPointState,
                                result.data.inputDetailList,
                                valuesHolder
                            )
                        }
                        InputTypeCode.MULTI_PRICE -> {
                            createMultiPriceFieldHolder(seats)
                            _uiState.value = BookingDetailsState.SuccessMulti(
                                seats,
                                emailState,
                                mobileState,
                                boardingPointState,
                                result.data.ticketPriceList,
                                passengerPriceDetails
                            )
                        }
                        InputTypeCode.MULTI_DYNAMIC -> TODO()
                    }

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

}