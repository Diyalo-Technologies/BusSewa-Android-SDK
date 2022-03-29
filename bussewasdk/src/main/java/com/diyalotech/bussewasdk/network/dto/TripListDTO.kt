package com.diyalotech.bussewasdk.network.dto

import com.diyalotech.bussewasdk.ui.triplist.Trip
import com.google.gson.Gson
import java.lang.reflect.Type

internal data class TripListDTO(
    val status: Int,
    val trips: List<TripDTO>?,
    val message: String?
)

internal data class TripDTO(
    val id: String,
    val inputTypeCode: Int,
    val serviceCode: String?,
    val amenities: List<String>,
    val availableSeat: Int,
    val busNo: String,
    val busType: String,
    val date: String,
    val dateEn: String,
    val departureTime: String,
    val imgList: List<String>,
    val lockStatus: Boolean,
    val multiPrice: Boolean,
    val operator: String,
    val operator_name: String,
    val rating: Double,
    val shift: String,
    val ticketPrice: Double,
    val ticketPriceList: List<MultiPrice>,
    val totalSeat: Int
)

//
internal data class TripListRequestDTO(
    val from: String,
    val to: String,
    val date: String
)

internal enum class InputTypeCode(val code: Int) {
    BASIC(1),
    DYNAMIC(2),
    MULTI_PRICE(3),
    MULTI_DYNAMIC(4);

    companion object{
        fun getType(code: Int): InputTypeCode {
            return when (code) {
                1 -> BASIC
                2 -> DYNAMIC
                3 -> MULTI_PRICE
                4 -> MULTI_DYNAMIC
                else -> BASIC
            }
        }
    }
}

//map to ui model
internal fun TripListDTO.getTripList(): List<Trip> {
    return trips?.map {
        Trip(
            it.id,
            it.amenities,
            it.operator_name,
            it.departureTime,
            it.busType,
            it.ticketPrice,
            it.availableSeat,
            it.inputTypeCode,
            (it.availableSeat / it.totalSeat) * 100f,
            it.serviceCode,
            it.lockStatus
        )
    } ?: emptyList()
}

internal fun TripDTO.getTrip(): Trip {
    return Trip(
        id,
        amenities,
        operator_name,
        departureTime,
        busType,
        ticketPrice,
        availableSeat,
        inputTypeCode,
        (availableSeat / totalSeat) * 100f,
        serviceCode,
        lockStatus
    )
}

internal fun singleTrip() = Gson().fromJson(testTripString, TripDTO::class.java).getTrip()

internal val testTripString = """{
    "id" : "NTgwNzc3OjA6MA==",
"operator" : "Desh Darshan Bus Sewa",
"date" : "2078-Paush-5",
"busNo" : "n/a",
"busType" : "Tourist AC",
"departureTime" : "07:00 AM",
"shift" : "Day",
"dateEn" : "2021-12-20",
"lockStatus" : false,
"multiPrice" : false,
"totalSeat" : 37,
"inputTypeCode" : 1,
"availableSeat" : 37,
"rating" : 0.0,
"imgList" : [ ],
"amenities" : [ "Surgical Mask", " Sanitizer", " Water Bottle", " AC" ],
"ticketPrice" : 900.0,
"passengerDetail" : [ ],
"operator_name" : "Desh Darshan Bus Sewa"
        }"""