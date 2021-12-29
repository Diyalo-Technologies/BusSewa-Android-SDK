package com.diyalotech.bussewasdk.network.dto

import com.diyalotech.bussewasdk.ui.triplist.Trip
import com.google.gson.Gson
import java.lang.reflect.Type

data class TripListDTO(
    val status: Int,
    val trips: List<TripDTO>?,
    val message: String?
)

data class TripDTO(
    val id: String,
    val inputTypeCode: Int,
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
data class TripListRequestDTO(
    val from: String,
    val to: String,
    val date: String
)

enum class InputTypeCode(val code: Int) {
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
fun TripListDTO.getTripList(): List<Trip> {
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
        )
    } ?: emptyList()
}

fun TripDTO.getTrip(): Trip {
    return Trip(
        id,
        amenities,
        operator_name,
        departureTime,
        busType,
        ticketPrice,
        availableSeat,
        inputTypeCode,
        (availableSeat / totalSeat) * 100f
    )
}

fun singleTrip() = Gson().fromJson(testTripString, TripDTO::class.java).getTrip()

val testTripString = """{
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

val testTripListString = """{
    "status": 1,
    "trips": [
        {
            "id": "NTgyMTY1OjU1NzE0NDE6MA==",
            "operator": "New Manjushree Express",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": " Full Air Suspension",
            "departureTime": "07:00 AM",
            "shift": "Day",
            "journeyHour": 7,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "AC",
                " Heater",
                " Comfortable Seat Gap",
                " Mobile Charger",
                " Mineral Water ",
                " Music",
                " CC Camera ",
                " TV",
                " Fixed Glass",
                " Full Air Suspension "
            ],
            "detailImage": [],
            "ticketPrice": 1015.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A19",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A20",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A21",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B19",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B20",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "New Manjushree Express"
        },
        {
            "id": "NTgwMzY4OjU1NTY5NzY6MA==",
            "operator": "Baba Adventure ",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Tourist Bus",
            "departureTime": "07:30 AM",
            "shift": "Day",
            "journeyHour": 8,
            "dateEn": "2021-12-09",
            "lockStatus": true,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "AC",
                " Air Suspension",
                " Thigh Rest Seats",
                " Music"
            ],
            "detailImage": [],
            "ticketPrice": 1100.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A15",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A16",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A17",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A18",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A19",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "No"
                }
            ],
            "operator_name": "Baba Adventure "
        },
        {
            "id": "NTgwNzA0OjU1NTkyMDU6MA==",
            "operator": "Desh Darshan Bus Sewa",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Tourist AC (Sofa Seater)",
            "departureTime": "07:00 PM",
            "shift": "Night",
            "journeyHour": 7,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "Surgical Mask",
                " Sanitizer",
                " AC",
                " Seat Belt",
                " Heater",
                " LED",
                " Mobile Charger",
                " Water bottle"
            ],
            "detailImage": [
                "https://bussewa.com/bussewaUpload/BusImage_image.MzcwOTgxYWRtaW4=_1607065195915.jpg",
                "https://bussewa.com/bussewaUpload/BusImage_image.MzcwOTgxYWRtaW4=_1607065196281.jpg"
            ],
            "ticketPrice": 1200.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "2",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "3",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "19",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "20",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "21",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "22",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "23",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "24",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "25",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "26",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "27",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "28",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "29",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "30",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "31",
                    "bookingStatus": "No"
                }
            ],
            "operator_name": "Desh Darshan Bus Sewa"
        },
        {
            "id": "NTgyMjgxOjU1NzI3MjQ6MA==",
            "operator": "Sunliner A/C",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Sofa Seater",
            "departureTime": "07:00 PM",
            "shift": "Night",
            "journeyHour": 8,
            "dateEn": "2021-12-09",
            "lockStatus": true,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "Water Bottles",
                " A/C",
                " Mobile Charger",
                " Music",
                " Comfortable Seats",
                " Cake"
            ],
            "detailImage": [],
            "ticketPrice": 1050.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B19",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B20",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "Sunliner A/C"
        },
        {
            "id": "NTgyMjIzOjU1NzE4Njg6MA==",
            "operator": "Mohit Metro A/C",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Sofa Seater",
            "departureTime": "07:00 PM",
            "shift": "Night",
            "journeyHour": 8,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "A/C",
                " Water Bottles",
                " Mobile Charger",
                " Cake",
                " Comfortable Seats",
                " Music"
            ],
            "detailImage": [],
            "ticketPrice": 1055.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B19",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B20",
                    "bookingStatus": "No"
                }
            ],
            "operator_name": "Mohit Metro A/C"
        },
        {
            "id": "NTgyMDc4OjU1NzA5MTY6MA==",
            "operator": "Butwal Manjushree",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Air Suspension (Semi-Sleeper)",
            "departureTime": "06:45 PM",
            "shift": "Night",
            "journeyHour": 11,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "Sleeper",
                " AC",
                " Movies",
                " Music",
                " Water"
            ],
            "detailImage": [],
            "ticketPrice": 960.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "Sp Ka",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "Ka",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "Kha",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "19",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "Butwal Manjushree"
        },
        {
            "id": "NTgxNzUzOjU1NjY5MDk6MA==",
            "operator": "Namaste Kapilvastu Air-Suspension A/C Deluxe",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Namaste  Kapilvastu A/C",
            "departureTime": "07:00 PM",
            "shift": "Night",
            "journeyHour": 10,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "A/C",
                "  Wifi",
                "  TV",
                "  Mobile Charger",
                "  Water",
                "  Music",
                "  Seat Belt",
                "  Mini Shop",
                "  Cold Drinks",
                "  Bus Hostess",
                "  Air Suspension",
                "  "
            ],
            "detailImage": [],
            "ticketPrice": 1000.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A15",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A16",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L1",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "Namaste Kapilvastu Air-Suspension A/C Deluxe"
        },
        {
            "id": "NTgxODcxOjU1Njk2ODI6MA==",
            "operator": "Namaste Kapilvastu Super A/C",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Super AC",
            "departureTime": "06:35 AM",
            "shift": "Day",
            "journeyHour": 10,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "A/C",
                "  Wifi",
                "  TV",
                "  Mobile Charger",
                "  Water",
                "  Music",
                "  Seat Belt",
                "  Air Suspension",
                "  Heater",
                "  CC Camera",
                "  Fixed Glass"
            ],
            "detailImage": [],
            "ticketPrice": 1000.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A15",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A16",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A17",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A18",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A19",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A20",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B19",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B20",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A21",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A22",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "23",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B21",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B22",
                    "bookingStatus": "No"
                }
            ],
            "operator_name": "Namaste Kapilvastu Super A/C"
        },
        {
            "id": "NTU4NDgyOjU1MTcxMzQ6MA==",
            "operator": "Kashthamandab Metro A/C",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Air Suspension",
            "departureTime": "07:00 AM",
            "shift": "Day",
            "journeyHour": 13,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "Heater",
                "  Air Suspension",
                "  Television",
                "  Fixed Glass",
                "  Music",
                "  CC Camera",
                "  Water",
                "  Mobile Charger",
                "  TV",
                "  Wifi",
                "  A/C"
            ],
            "detailImage": [],
            "ticketPrice": 940.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "19",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "20",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "21",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "Kashthamandab Metro A/C"
        },
        {
            "id": "NTYwNjgzOjU2NDI0Mzc6MA==",
            "operator": "Bhairab Darsan",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Bhairav Air Suspension A/C",
            "departureTime": "06:45 PM",
            "shift": "Night",
            "journeyHour": 11,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "A/C",
                "  Wifi",
                "  TV",
                "  Mobile Charger",
                "  Water",
                "  Music",
                "  Air Suspension"
            ],
            "detailImage": [],
            "ticketPrice": 940.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A15",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A16",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "19",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "20",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "21",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "Bhairab Darsan"
        },
        {
            "id": "NTYxOTY0OjU0OTI0MDQ6MA==",
            "operator": "Dev Raj Air Bus A/C",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Air Suspension A/C",
            "departureTime": "07:00 PM",
            "shift": "Night",
            "journeyHour": 11,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "A/C",
                "  Wifi",
                "  Television",
                "  A/C",
                "  Air Suspension",
                "  Heater",
                " Air Suspension",
                " Music"
            ],
            "detailImage": [],
            "ticketPrice": 940.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "15",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "16",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "19",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "18",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "Dev Raj Air Bus A/C"
        },
        {
            "id": "NTYyMjMxOjUzNDUzNTE6MA==",
            "operator": "Saleena A/c",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "A/C",
            "departureTime": "07:00 PM",
            "shift": "Night",
            "journeyHour": 10,
            "dateEn": "2021-12-09",
            "lockStatus": true,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "A/C",
                "  Wifi",
                "  Mobile Charger",
                "  Water",
                "  Music",
                "  Television"
            ],
            "detailImage": [],
            "ticketPrice": 960.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "L19",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "Saleena A/c"
        },
        {
            "id": "NTg5ODEzOjU2NDYxNjM6MA==",
            "operator": "Malmala A/C",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Air Suspension A/C",
            "departureTime": "07:00 PM",
            "shift": "Night",
            "journeyHour": 9,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "Television",
                " A/C",
                "  Wifi",
                "  Television",
                " Air Suspension",
                " Mobile Charger"
            ],
            "detailImage": [],
            "ticketPrice": 940.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B19",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "Malmala A/C"
        },
        {
            "id": "NTc4NDcyOjU1MzY0MTk6MA==",
            "operator": "Sajha Yatayat (Ashok Leyland Viking)",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Deluxe Viking With Air Suspension.",
            "departureTime": "06:00 PM",
            "shift": "Night",
            "journeyHour": 8,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 5,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "Television",
                " WiFi",
                " Mobile Charger",
                " Comfortable Seats"
            ],
            "detailImage": [],
            "ticketPrice": 805.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A1",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A2",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A3",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A4",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A6",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B7",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B8",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B9",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B10",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B11",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B12",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B13",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B14",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A13",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A14",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B15",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B16",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A15",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "A16",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "B17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A17",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A18",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "A19",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B19",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B20",
                    "bookingStatus": "Yes"
                }
            ],
            "operator_name": "Sajha Yatayat (Ashok Leyland Viking)"
        },
        {
            "id": "NTgzMzU5OjA6MA==",
            "operator": "Sagun Yatayat",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Hiace",
            "departureTime": "07:30 AM",
            "shift": "Day",
            "journeyHour": 6,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 4,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "Comfortable Seats",
                " Music"
            ],
            "detailImage": [],
            "ticketPrice": 950.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "A",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "13",
                    "bookingStatus": "No"
                }
            ],
            "operator_name": "Sagun Yatayat"
        },
        {
            "id": "NTgzMzkyOjA6MA==",
            "operator": "Sagun Yatayat",
            "date": "2078-Mangsir-23",
            "faceImage": "",
            "busNo": "n/a",
            "busType": "Hiace",
            "departureTime": "10:00 AM",
            "shift": "Day",
            "journeyHour": 6,
            "dateEn": "2021-12-09",
            "lockStatus": false,
            "multiPrice": false,
            "inputTypeCode": 1,
            "noOfColumn": 4,
            "rating": 0.0,
            "imgList": [],
            "amenities": [
                "Comfortable Seats",
                " Music"
            ],
            "detailImage": [],
            "ticketPrice": 950.0,
            "passengerDetail": [],
            "seatLayout": [
                {
                    "displayName": "A",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "B",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "1",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "2",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "3",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "4",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "5",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "6",
                    "bookingStatus": "Yes"
                },
                {
                    "displayName": "7",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "na",
                    "bookingStatus": "na"
                },
                {
                    "displayName": "8",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "9",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "10",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "11",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "12",
                    "bookingStatus": "No"
                },
                {
                    "displayName": "13",
                    "bookingStatus": "No"
                }
            ],
            "operator_name": "Sagun Yatayat"
        }
    ]
}"""