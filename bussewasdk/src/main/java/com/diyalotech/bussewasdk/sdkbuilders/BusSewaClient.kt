package com.diyalotech.bussewasdk.sdkbuilders

import android.os.Parcelable
import androidx.annotation.ColorRes
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.theme.createDefaultTheme
import kotlinx.parcelize.Parcelize

const val BUS_SDK_CLIENT_INFO = "bus_sdk_client_info"
const val BUS_SDK_MESSAGE = "bus_sdk_message"
const val BUS_SDK_RESPONSE = "bus_sdk_response"

const val BUS_SDK_TICKET = "ticketSerialNo"
const val BUS_SDK_TRIP_ID = "tripId"
const val BUS_SDK_SERVICE_CODE = "serviceCode"
const val BUS_SDK_REQ_ID = "requestId" //String
const val BUS_SDK_AMOUNT = "amount" //Double
const val BUS_SDK_PROPERTIES = "properties" //HashMap

@Parcelize
data class BusSewaClient(
    val clientId: String,
    val clientSecret: String,
    val busTheme: BusTheme = createDefaultTheme(),
    val environment: BusSewaSdkEnv = BusSewaSdkEnv.TEST
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


@Parcelize
enum class BusSewaSdkEnv : Parcelable {
    TEST,
    PROD
}


