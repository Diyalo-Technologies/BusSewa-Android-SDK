package com.diyalotech.bussewasdk.ui.seatlayout

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.diyalotech.bussewasdk.network.dto.InputTypeCode
import com.diyalotech.bussewasdk.network.dto.SeatLayout
import com.diyalotech.bussewasdk.network.dto.getSelectedSeatModel
import com.diyalotech.bussewasdk.repo.DataStoreRepository
import com.diyalotech.bussewasdk.repo.TripRepository
import com.diyalotech.bussewasdk.ui.NavDirection
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class SelectSeatState {
    object Loading : SelectSeatState()
    class Success(
        val selectSeatModel: SelectSeatModel
    ) : SelectSeatState()

    class Error(val message: String) : SelectSeatState()
}

sealed class BookingState {
    object Init : BookingState()
    object Loading : BookingState()
}

sealed class SelectSeatEvents {
    class Error(val msg: String) : SelectSeatEvents()
    class Navigation(val direction: NavDirection) : SelectSeatEvents()
}

data class SelectSeatModel(
    val isLocked: Boolean,
    val noOfColumn: Int,
    val seatLayout: List<SeatLayout>
)

class SelectSeatsViewModel(
    private val tripRepository: TripRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    //cache
    //seatModel
    private lateinit var seatModel: SelectSeatModel
    private val trip = dataStoreRepository.getSelectedTrip()

    val selectSeatList = mutableStateListOf<SeatLayout>()
    val totalPrice = mutableStateOf<Double?>(null)

    private val _uiState = MutableStateFlow<SelectSeatState>(SelectSeatState.Loading)
    val uiState: StateFlow<SelectSeatState> = _uiState

    private val _bookingUiState = MutableStateFlow<BookingState>(BookingState.Init)
    val bookingUiState: StateFlow<BookingState> = _bookingUiState

    private val eventsChannel = Channel<SelectSeatEvents>()
    val eventsFlow: Flow<SelectSeatEvents> = eventsChannel.receiveAsFlow()

    init {
        loadSeatDetails()
    }

    private fun loadSeatDetails() {
        viewModelScope.launch {
            if (trip == null) {
                _uiState.value = SelectSeatState.Error("Could not load trip details.")
                return@launch
            }

            when (val result = tripRepository.refreshDetails(trip.id)) {
                is ApiResult.Error -> {
                    _uiState.value = SelectSeatState.Error(result.error)
                }
                is ApiResult.Success -> {
                    seatModel = result.data.getSelectedSeatModel()
                    _uiState.value = SelectSeatState.Success(result.data.getSelectedSeatModel())
                }
            }
        }
    }

    fun onSeatClicked(id: String) {

        //remove the seat if its already selected
        selectSeatList.find { it.displayName.equals(id, true) }?.let {
            //exit and return if successfully removed
            selectSeatList.remove(it)
            calculatePrice()
            return
        }

        //add seat to the list
        seatModel.seatLayout.find { it.displayName.equals(id, true) }?.let { layout ->
            selectSeatList.add(layout)
            calculatePrice()
        }

        dataStoreRepository.saveSelectedSeats(selectSeatList.map { it.displayName })
    }

    private fun calculatePrice() {
        when (trip?.inputTypeCode) {
            InputTypeCode.BASIC, InputTypeCode.DYNAMIC -> {
                totalPrice.value = selectSeatList.size * trip.ticketPrice
            }
            InputTypeCode.MULTI_PRICE, InputTypeCode.MULTI_DYNAMIC -> {
                totalPrice.value = null
            }
            null -> {}
        }
    }

    fun bookSeats() {
        _bookingUiState.value = BookingState.Loading
        viewModelScope.launch {
            val seats = dataStoreRepository.getSelectedSeats()

            when (val result = tripRepository.bookTrip(trip?.id!!, seats)) {
                is ApiResult.Error -> {
                    eventsChannel.send(SelectSeatEvents.Error(result.error))
                }
                is ApiResult.Success -> {
                    dataStoreRepository.saveBookingInfo(
                        result.data.ticketSrlNo,
                        result.data.timeOut,
                        result.data.boardingPoints
                    )
                    _bookingUiState.value = BookingState.Init
                    eventsChannel.send(SelectSeatEvents.Navigation(NavDirection.FORWARD))
                }
            }
        }
    }

}