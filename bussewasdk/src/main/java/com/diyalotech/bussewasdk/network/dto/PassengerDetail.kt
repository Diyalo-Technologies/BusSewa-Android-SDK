package com.diyalotech.bussewasdk.network.dto

data class InputDetailConfigDTO(
    val price: Double,
    val ticketPriceList: List<MultiPrice>,
    val passengerDetailList: List<PassengerDetail>
)

data class MultiPrice(
    val id: Int,
    val passengerType: String,
    val priceInDollar: String,
    val priceInRs: String
)

data class PassengerDetail(
    val id: Int,
    val detail: String,
    val inputType: InputType,
    val rangeTo: Int = 0,
    val rangeFrom: Int = 0,
    val values: List<String> = listOf()
)

enum class InputType(val key: String) {
    TEXT_FIELD("textField"),
    DROP_DOWN("dropDown"),
    NUMBER_RANGE("numberRange")
}