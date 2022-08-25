package com.diyalotech.bussewasdk.repo.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.diyalotech.bussewasdk.network.dto.InputTypeCode
import com.diyalotech.bussewasdk.utils.localTimeNow
import com.diyalotech.bussewasdk.utils.toLocalInstant
import kotlin.math.max
import kotlin.time.ExperimentalTime

internal data class SelectedTripDetails(
    val id: String,
    val operatorName: String,
    val ticketPrice: Double,
    val inputTypeCode: InputTypeCode,
    val serviceCode: String?,
)

@Stable
internal class BookingInfo {
    var ticketSrlNo by mutableStateOf("")
    var timeout by mutableStateOf(localTimeNow())
    var boardingPoints by mutableStateOf(emptyList<String>())

    fun remainingCountDown(): String {
        val remaining =
            max(0, (timeout.toLocalInstant() - localTimeNow().toLocalInstant()).inWholeSeconds)
        val seconds = (remaining % 60).toInt()
        val minutes = ((remaining / 60) % 60).toInt()
        return "%02d:%02d".format(minutes, seconds)
    }
}