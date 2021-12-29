package com.diyalotech.bussewasdk.network.dto

data class InputDetailConfigDTO(
    val ticketPrice: Double,
    val ticketPriceList: List<MultiPrice>,
    val inputDetailList: List<PassengerDetail>
)

data class  MultiPrice(
    val id: Int,
    val passengerType: String,
    val priceInDollar: Double = 0.0,
    val priceInRs: Double = 0.0
)

data class PassengerDetail(
    val tyepId: Int,
    val detailName: String,
    val inputType: InputType,
    val rangeTo: Int = 0,
    val rangeFrom: Int = 0,
    val manditory: Boolean = false,
    val valueList: List<String> = listOf()
)

enum class InputType(val key: String) {
    TEXT_FIELD("textField"),
    DROP_DOWN("dropDown"),
    NUMBER_RANGE("numberRange")
}