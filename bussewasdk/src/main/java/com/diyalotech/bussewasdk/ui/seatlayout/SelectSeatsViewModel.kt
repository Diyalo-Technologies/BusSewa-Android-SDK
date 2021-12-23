package com.diyalotech.bussewasdk.ui.seatlayout

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.diyalotech.bussewasdk.network.dto.SeatLayout
import com.diyalotech.bussewasdk.network.dto.getSelectedSeatModel
import com.diyalotech.bussewasdk.repo.SearchParamRepository
import com.diyalotech.bussewasdk.repo.TripRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SelectSeatState {
    object Loading : SelectSeatState()
    class Success(val selectSeatModel: SelectSeatModel) : SelectSeatState()
    class Error(val message: String) : SelectSeatState()
}

data class SelectSeatModel(
    val isLocked: Boolean,
    val noOfColumn: Int,
    val price: Double = 200.0, //TODO: remove constant in prod
    val seatLayout: List<SeatLayout>
)

class SelectSeatsViewModel(
    private val tripRepository: TripRepository,
    private val searchParamRepository: SearchParamRepository
) : ViewModel() {

    //cache
    //seatModel
    private lateinit var seatModel: SelectSeatModel

    val selectSeatList = mutableStateListOf<SeatLayout>()
    val totalPrice = mutableStateOf(0.0)

    private val _uiState = MutableStateFlow<SelectSeatState>(SelectSeatState.Loading)
    val uiState: StateFlow<SelectSeatState> = _uiState

    init {
        loadSeatDetails()
    }

    private fun loadSeatDetails() {
        viewModelScope.launch {
            val tripId = searchParamRepository.getSelectedTripId()
            if (tripId.isEmpty()) {
                _uiState.value = SelectSeatState.Error("Could not load trip details.")
                return@launch
            }

            when (val result = tripRepository.refreshDetails(tripId)) {
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
            return
        }

        //add seat to the list
        seatModel.seatLayout.find { it.displayName.equals(id, true) }?.let { layout ->
            selectSeatList.add(layout)
        }

        totalPrice.value = selectSeatList.size * seatModel.price

        searchParamRepository.saveSelectedSeats(selectSeatList.map { it.displayName })
    }

}