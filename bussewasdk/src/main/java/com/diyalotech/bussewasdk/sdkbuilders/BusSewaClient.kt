package com.diyalotech.bussewasdk.sdkbuilders

import android.os.Parcelable
import androidx.annotation.ColorRes
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.theme.createDefaultTheme
import kotlinx.parcelize.Parcelize

const val BUS_SDK_CLIENT_INFO = "bus_sdk_client_info"
const val BUS_SDK_MESSAGE = "bus_sdk_message"

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
    val primaryVariant: Int = R.color.design_default_color_primary_variant,
    val secondary: Int = R.color.design_default_color_secondary,
    val busThemeDarkLight: BusThemeDarkLight = BusThemeDarkLight.DEFAULT
) : Parcelable

/**
 * @property DEFAULT follows system theme
 *
 * */
enum class BusThemeDarkLight {
    LIGHT,
    DARK,
    DEFAULT
}


