package com.diyalotech.bussewasdk.repo.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.diyalotech.bussewasdk.network.dto.InputTypeCode
import com.diyalotech.bussewasdk.utils.localTimeNow
import com.diyalotech.bussewasdk.utils.toLocalInstant
import kotlinx.datetime.LocalDateTime
import kotlin.time.ExperimentalTime

data class SelectedTripDetails(
    val id: String,
    val operatorName: String,
    val ticketPrice: Double,
    val inputTypeCode: InputTypeCode,
)

@Stable
class BookingInfo {
    var ticketSrlNo by mutableStateOf("")
    var timeout by mutableStateOf(localTimeNow())
    var boardingPoints by mutableStateOf(emptyList<String>())

    @OptIn(ExperimentalTime::class)
    fun remainingCountDown(): Long {
        return (timeout.toLocalInstant() - localTimeNow().toLocalInstant()).inWholeSeconds
    }
}