package com.diyalotech.bussewasdk.network.dto

import com.google.gson.annotations.SerializedName

internal data class InputDetailConfigDTO(
    val ticketPrice: Double,
    val ticketPriceList: List<MultiPrice>,
    val inputDetailList: List<PassengerDetail>
)

internal data class MultiPrice(
    val id: Int,
    val passengerType: String,
    val priceInDollar: Double = 0.0,
    val priceInRs: Double = 0.0
)

internal data class PassengerDetail(
    val typeId: Int,
    val detailName: String,
    val inputType: InputType?,
    val rangeTo: Int = 0,
    val rangeFrom: Int = 0,
    val manditory: Boolean = false,
    val valueList: List<String> = listOf()
)

internal enum class InputType(val key: String) {

    @SerializedName("textField")
    TEXT_FIELD("textField"),

    @SerializedName("dropDown")
    DROP_DOWN("dropDown"),

    @SerializedName("numberRange")
    NUMBER_RANGE("numberRange")
}