package com.diyalotech.bussewasdk.ui.triplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.diyalotech.bussewasdk.network.dto.getTripList
import com.diyalotech.bussewasdk.repo.SearchParamRepository
import com.diyalotech.bussewasdk.repo.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import javax.inject.Inject

data class Trip(
    val id: String,
    val amenities: List<String>,
    val operatorName: String,
    val departureTime: String,
    val busType: String,
    val ticketPrice: Double,
    val availableSeat: Int,
    val availablePercent: Float,
)

sealed class TripListState {
    object Loading : TripListState()
    class Success(val tripList: List<Trip>) : TripListState()
    class Error(val message: String) : TripListState()
}

@HiltViewModel
class TripListViewModel @Inject constructor(
    private val searchParamRepository: SearchParamRepository,
    private val tripRepository: TripRepository
) : ViewModel() {

    //cancellable jobs
    private var job: Job? = null

    private val _uiState = MutableStateFlow<TripListState>(TripListState.Loading)
    val uiState: StateFlow<TripListState> = _uiState

    val searchTripState = searchParamRepository.searchTripStore

    init {
        findTrips()
    }

    private fun findTrips() {
        job?.cancel()
        job = viewModelScope.launch {
            val searchModel = searchParamRepository.searchTripStore.getSearchTripModel()
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
        searchParamRepository.setSearchDate(date)
        _uiState.value = TripListState.Loading
        findTrips()
    }

    fun onTripClicked(id: String) {
        searchParamRepository.setSelectedTrip(id)
    }


}