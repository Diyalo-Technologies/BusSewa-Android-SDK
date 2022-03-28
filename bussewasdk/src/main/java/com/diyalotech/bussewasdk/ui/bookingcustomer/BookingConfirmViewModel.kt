package com.diyalotech.bussewasdk.ui.bookingcustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.network.dto.*
import com.diyalotech.bussewasdk.repo.BookingRepository
import com.diyalotech.bussewasdk.repo.DataStoreRepository
import com.diyalotech.bussewasdk.sdkbuilders.*
import com.diyalotech.bussewasdk.ui.NavDirection
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.*
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.PassengerPriceValues
import com.diyalotech.bussewasdk.ui.sharedmodels.BookingState
import com.diyalotech.bussewasdk.utils.Validator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal sealed class BookingConfirmEvent {
    class Error(val msg: String) : BookingConfirmEvent()
    class Navigation(val direction: NavDirection) : BookingConfirmEvent()
    class Success(val responseMap: HashMap<String, Any>) : BookingConfirmEvent()
}

internal class BookingConfirmViewModel(
    private val dataStoreRepo: DataStoreRepository,
    private val bookingRepo: BookingRepository,
) : ViewModel() {

    //prefetch cache
    private lateinit var passengerDetailList: List<PassengerDetail>
    private lateinit var ticketPriceList: List<MultiPrice>

    val tripDataStore = dataStoreRepo.tripDataStore
    val ticketPrice = dataStoreRepo.tripDataStore.selectedTripDetails?.ticketPrice ?: 0

    private val _uiState = MutableStateFlow<BookingDetailsState>(BookingDetailsState.Loading)
    val uiState: StateFlow<BookingDetailsState> = _uiState

    private val _bookingUiState = MutableStateFlow<BookingState>(BookingState.Init)
    val bookingUiState: StateFlow<BookingState> = _bookingUiState

    //for basic details
    var nameState = TextFieldModel()
    var emailState = TextFieldModel()
    var mobileState = TextFieldModel()
    var boardingPointState = TextFieldModel()

    //for multi price type
    private lateinit var passengerPriceValues: Map<String, PassengerPriceValues>

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
                        InputTypeCode.MULTI_DYNAMIC -> {
                            passengerDetailList = result.data.inputDetailList
                            ticketPriceList = result.data.ticketPriceList
                            createMultiPriceFieldHolder(seats)
                            createDynamicFieldHolderList(seats, result.data.inputDetailList)
                        }
                    }

                    updateDetailsBasedOnType()
                }
            }
        }
    }

    private fun createMultiPriceFieldHolder(seats: List<String>) {
        val passengerPriceDetails = mutableMapOf<String, PassengerPriceValues>()
        for (seat in seats) {
            passengerPriceDetails[seat] = PassengerPriceValues(
                priceFieldModel = PriceFieldModel(),
                nameModel = TextFieldModel()
            )
        }
        this.passengerPriceValues = passengerPriceDetails
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
                    passengerPriceValues
                )
            }
            InputTypeCode.MULTI_DYNAMIC -> {
                _uiState.value = BookingDetailsState.SuccessMultiDynamic(
                    seats,
                    emailState,
                    mobileState,
                    boardingPointState,
                    ticketPriceList,
                    passengerPriceValues,
                    passengerDetailList,
                    passengerDetailValues
                )
            }
        }
    }

    fun onBasicDetailsChanged(field: BasicFields, value: String) {
        when (field) {
            BasicFields.NAME -> {
                this.nameState.value = value
                this.nameState.clearError()
            }
            BasicFields.EMAIL -> {
                this.emailState.value = value
                this.emailState.clearError()
            }
            BasicFields.PHONE -> {
                this.mobileState.value = value
                this.mobileState.clearError()
            }
            BasicFields.BOARDING_POINT -> {
                this.boardingPointState.value = value
                this.boardingPointState.clearError()
            }
        }
    }

    fun onDynamicDetailsChanged(seat: String, id: Int, value: String) {
        val detail = passengerDetailValues[seat] ?: return
        detail.find { it.id == id }?.let {
            it.value = value
            it.clearError()
        }
    }

    fun onMultiPriceNameChanged(seat: String, value: String) {
        passengerPriceValues[seat]?.let {
            it.nameModel.value = value
            it.nameModel.clearError()
        }
    }

    fun onMultiPricePriceChanged(seat: String, value: MultiPrice) {
        passengerPriceValues[seat]?.let {
            it.priceFieldModel.value = value
            it.priceFieldModel.clearError()
        }
        val sum = passengerPriceValues.map { it.value.priceFieldModel.value }.filterNotNull()
            .sumOf { it.priceInRs }
        dataStoreRepo.saveTicketPrice(sum)
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

    private fun validateBasicDetails(): Boolean {
        val trip = tripDataStore.selectedTripDetails ?: return false

        if (boardingPointState.value.isBlank()) {
            this.boardingPointState.setError("Please select boarding point.")
            return false
        }

        when (trip.inputTypeCode) {
            InputTypeCode.BASIC -> {
                if (nameState.value.isBlank()) {
                    this.nameState.setError("Required.")
                    return false
                }
            }
            else -> {}
        }

        if (emailState.value.isNotBlank()) {
            if (!Validator.isEmailValid(emailState.value)) {
                this.emailState.setError("Invalid email.")
                return false
            }
        }

        if (mobileState.value.isBlank()) {
            this.mobileState.setError("Required.")
            return false
        }

        if (!Validator.isValidMobile(mobileState.value)) {
            this.mobileState.setError("Invalid phone number.")
            return false
        }

        return true
    }

    private fun validateDynamicDetails(): Boolean {
        passengerDetailValues.forEach { map ->
            map.value.forEach { model ->
                val mandatory =
                    passengerDetailList.find { it.typeId == model.id }?.manditory ?: false

                if (mandatory && model.value.isBlank()) {
                    model.setError("Required.")
                    return false
                }
            }
        }

        return true
    }

    private fun validateMultiPriceDetails(): Boolean {
        passengerPriceValues.values.forEach { value ->
            if (value.nameModel.value.isBlank()) {
                value.nameModel.setError("Required.")
                return false
            }

            if (value.priceFieldModel.value == null) {
                value.priceFieldModel.setError("Required.")
                return false
            }
        }

        return true
    }

    /*
    * validate all fields
    * */
    fun confirmBooking() {

        _bookingUiState.value = BookingState.Loading
        val trip = tripDataStore.selectedTripDetails ?: return
        val bookingInfo = tripDataStore.bookingInfo
        val mobileNumber = mobileState.value
        val boardingPoint = boardingPointState.value
        val name = nameState.value.ifBlank { null }
        val email = emailState.value.ifBlank { null }
        var passengerDynamicValues: List<PassengerTypeDetail>? = null
        var passengerMultiPriceValues: List<PassengerPriceDetail>? = null

        val propertiesForResponse = mutableMapOf<String, Any?>()
        propertiesForResponse[BUS_SDK_TICKET] = tripDataStore.bookingInfo.ticketSrlNo
        propertiesForResponse[BUS_SDK_TRIP_ID] = tripDataStore.selectedTripDetails?.id

        when (trip.inputTypeCode) {
            InputTypeCode.BASIC -> {
                propertiesForResponse["name"] = name
                propertiesForResponse["email"] = email
                propertiesForResponse["boardingPoint"] = boardingPoint
                propertiesForResponse["contactInfo"] = mobileNumber
                if (!validateBasicDetails()) return
            }
            InputTypeCode.DYNAMIC -> {
                if (validateDynamicDetails() && validateBasicDetails()) {
                    passengerDynamicValues = passengerDetailValues.map { map ->
                        PassengerTypeDetail(
                            map.key,
                            map.value.map { PassengerDetailValues(it.id, it.value) })
                    }

                    propertiesForResponse["email"] = email
                    propertiesForResponse["boardingPoint"] = boardingPoint
                    propertiesForResponse["contactInfo"] = mobileNumber
                    passengerDetailValues.onEachIndexed { index, entry ->
                        propertiesForResponse["seat_$index"] = entry.key
                        entry.value.forEach { field ->
                            val fieldName =
                                passengerDetailList.find { it.typeId == field.id }?.detailName
                                    ?: "unknown"
                            propertiesForResponse["${fieldName}_${index}"] = field.value
                        }
                    }
                } else {
                    return
                }
            }
            InputTypeCode.MULTI_PRICE -> {
                if (validateMultiPriceDetails() && validateBasicDetails()) {
                    passengerMultiPriceValues = passengerPriceValues.map { map ->
                        PassengerPriceDetail(
                            map.value.priceFieldModel.value?.id!!,
                            map.value.nameModel.value,
                            map.key
                        )
                    }

                    propertiesForResponse["email"] = email
                    propertiesForResponse["boardingPoint"] = boardingPoint
                    propertiesForResponse["contactInfo"] = mobileNumber
                    passengerPriceValues.onEachIndexed { index, entry ->
                        propertiesForResponse["seat_$index"] = entry.key
                        propertiesForResponse["name_$index"] = entry.value.nameModel.value
                        propertiesForResponse["passengerType_$index"] =
                            entry.value.priceFieldModel.value?.passengerType
                    }
                } else {
                    return
                }
            }
            InputTypeCode.MULTI_DYNAMIC -> {
                if (validateBasicDetails() && validateMultiPriceDetails() && validateDynamicDetails()) {
                    passengerDynamicValues = passengerDetailValues.map { map ->
                        PassengerTypeDetail(
                            map.key,
                            map.value.map { PassengerDetailValues(it.id, it.value) })
                    }
                    passengerMultiPriceValues = passengerPriceValues.map { map ->
                        PassengerPriceDetail(
                            map.value.priceFieldModel.value?.id!!,
                            map.value.nameModel.value,
                            map.key
                        )
                    }

                    propertiesForResponse["email"] = email
                    propertiesForResponse["boardingPoint"] = boardingPoint
                    propertiesForResponse["contactInfo"] = mobileNumber

                    passengerPriceValues.onEachIndexed { index, entry ->
                        propertiesForResponse["seat_$index"] = entry.key
                        propertiesForResponse["name_$index"] = entry.value.nameModel.value
                        propertiesForResponse["passengerType_$index"] =
                            entry.value.priceFieldModel.value?.passengerType
                    }
                    passengerDetailValues.onEachIndexed { index, entry ->
                        propertiesForResponse["seat_$index"] = entry.key
                        entry.value.forEach { field ->
                            val fieldName =
                                passengerDetailList.find { it.typeId == field.id }?.detailName
                                    ?: "unknown"
                            propertiesForResponse["${fieldName}_${index}"] = field.value
                        }
                    }
                } else {
                    return
                }
            }
        }

        viewModelScope.launch {
            val result = bookingRepo.savePassengerInfo(
                trip.id,
                bookingInfo.ticketSrlNo,
                mobileNumber,
                boardingPoint,
                name,
                email,
                passengerDynamicValues,
                passengerMultiPriceValues
            )

            when (result) {
                is ApiResult.Error -> {
                    //handle error case
                    _bookingUiState.value = BookingState.Init
                    eventsChannel.send(BookingConfirmEvent.Error("Network error."))
                }
                is ApiResult.Success -> {
                    if (result.data.status == 1) {
                        val responseMap = HashMap<String, Any>()
                        responseMap[BUS_SDK_REQ_ID] = tripDataStore.bookingInfo.ticketSrlNo
                        responseMap[BUS_SDK_AMOUNT] = ticketPrice
                        responseMap[BUS_SDK_PROPERTIES] = propertiesForResponse

                        eventsChannel.send(
                            BookingConfirmEvent.Success(responseMap)
                        )
                    } else {
                        _bookingUiState.value = BookingState.Init
                        eventsChannel.send(BookingConfirmEvent.Error("Server error."))
                    }
                }
            }
        }
    }
}