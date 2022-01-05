package com.diyalotech.bussewasdk.ui.triplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.diyalotech.bussewasdk.network.dto.InputTypeCode
import com.diyalotech.bussewasdk.network.dto.getTripList
import com.diyalotech.bussewasdk.repo.DataStoreRepository
import com.diyalotech.bussewasdk.repo.TripRepository
import com.diyalotech.bussewasdk.repo.model.SelectedTripDetails
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

internal data class Trip(
    val id: String,
    val amenities: List<String>,
    val operatorName: String,
    val departureTime: String,
    val busType: String,
    val ticketPrice: Double,
    val availableSeat: Int,
    val inputTypeCode: Int,
    val availablePercent: Float,
    val locked: Boolean,
)

internal sealed class TripListState {
    object Loading : TripListState()
    class Success(val tripList: List<Trip>) : TripListState()
    class Error(val message: String) : TripListState()
}

internal class TripListViewModel constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val tripRepository: TripRepository
) : ViewModel() {

    init {
        println("trip list init")
    }

    //cancellable jobs
    private var job: Job? = null

    val tripDataStore = dataStoreRepository.tripDataStore

    private val _uiState = MutableStateFlow<TripListState>(TripListState.Loading)
    val uiState: StateFlow<TripListState> = _uiState

    init {
        findTrips()
    }

    private fun findTrips() {
        job?.cancel()
        _uiState.value = TripListState.Loading
        job = viewModelScope.launch {
            val searchModel = dataStoreRepository.getSearchModel()
            val result = tripRepository.findTrips(
                searchModel.source,
                searchModel.destination,
                "${searchModel.date.year}-${searchModel.date.monthNumber}-${searchModel.date.dayOfMonth}"
            )

            when (result) {
                is ApiResult.Error -> {
                    println(result.error)
                }
                is ApiResult.Success -> {
                    if (result.data.status == 1) {
                        _uiState.value = TripListState.Success(result.data.getTripList())
                    } else {
                        _uiState.value =
                            TripListState.Error(result.data.message ?: "Something went wrong.")
                    }
                }
            }
        }
    }

    fun onDateChanged(date: LocalDate) {
        dataStoreRepository.saveDate(date)
        findTrips()
    }

    fun onTripClicked(trip: Trip) {
        val selectedTripDetails = SelectedTripDetails(
            id = trip.id,
            inputTypeCode = InputTypeCode.getType(trip.inputTypeCode),
            operatorName = trip.operatorName,
            ticketPrice = trip.ticketPrice
        )
        dataStoreRepository.setSelectedTrip(selectedTripDetails)
    }


}