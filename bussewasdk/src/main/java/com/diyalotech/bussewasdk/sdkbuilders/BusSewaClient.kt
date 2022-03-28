package com.diyalotech.bussewasdk.sdkbuilders

import android.os.Parcelable
import androidx.annotation.ColorRes
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.theme.createDefaultTheme
import kotlinx.parcelize.Parcelize

//keys for activity intent/results
const val BUS_SDK_CLIENT_INFO = "bus_sdk_client_info"
const val BUS_SDK_MESSAGE = "bus_sdk_message"
const val BUS_SDK_RESPONSE = "bus_sdk_result"

//known keys for required data
const val BUS_SDK_AMOUNT = "amount"
const val BUS_SDK_REQ_ID = "requestId"
const val BUS_SDK_PROPERTIES = "properties"
const val BUS_SDK_TRIP_ID = "tripId"
const val BUS_SDK_TICKET = "ticketSerialNo"

@Parcelize
data class BusSewaClient(
    val clientId: String,
    val busTheme: BusTheme = createDefaultTheme()
) : Parcelable

/**
 * @param primary Customize primary color
 * @param busThemeDarkLight Customize dark/light mode theme
 * */
@Parcelize
data class BusTheme(
    @ColorRes
    val primary: Int = R.color.design_default_color_primary,
    @ColorRes
    val primaryVariant: Int = R.color.design_default_color_primary_variant,
    @ColorRes
    val secondary: Int = R.color.design_default_color_secondary,
    val busThemeDarkLight: BusThemeDarkLight = BusThemeDarkLight.DEFAULT
) : Parcelable

/**
 * @property DEFAULT follows system theme
 * */
@Parcelize
enum class BusThemeDarkLight: Parcelable {
    LIGHT,
    DARK,
    DEFAULT
}


