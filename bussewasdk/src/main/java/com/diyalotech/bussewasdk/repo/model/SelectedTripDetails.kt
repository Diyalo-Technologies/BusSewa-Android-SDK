package com.diyalotech.bussewasdk.repo.model

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

data class BookingInfo(
    val ticketSrlNo: String,
    val timeout: LocalDateTime,
    val boardingPoints: List<String>
) {
    @OptIn(ExperimentalTime::class)
    fun remainingCountDown(): Long {
        return (timeout.toLocalInstant() - localTimeNow().toLocalInstant()).inWholeSeconds
    }
}