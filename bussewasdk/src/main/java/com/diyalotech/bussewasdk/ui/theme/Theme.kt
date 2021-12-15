package com.diyalotech.bussewasdk.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.diyalotech.bussewasdk.sdkbuilders.BusTheme
import com.diyalotech.bussewasdk.sdkbuilders.BusThemeDarkLight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Green400,
    primaryVariant = Purple700,
    secondary = Red200
)

private val LightColorPalette = lightColors(
    primary = Green400,
    primaryVariant = Purple700,
    secondary = Red200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

fun createDefaultTheme() = BusTheme()

@Composable
fun BusSewaSDKTheme(busTheme: BusTheme = createDefaultTheme(), content: @Composable() () -> Unit) {

    val systemUiController = rememberSystemUiController()

    val isDarkTheme = when (busTheme.busThemeDarkLight) {
        BusThemeDarkLight.LIGHT -> false
        BusThemeDarkLight.DARK -> true
        BusThemeDarkLight.DEFAULT -> isSystemInDarkTheme()
    }

    val colors = if (isDarkTheme) {
        darkColors(
            primary = colorResource(id = busTheme.primary),
            primaryVariant = colorResource(id = busTheme.primaryVariant),
            secondary = colorResource(id = busTheme.secondary),
        )
    } else {
        lightColors(
            primary = colorResource(id = busTheme.primary),
            primaryVariant = colorResource(id = busTheme.primaryVariant),
            secondary = colorResource(id = busTheme.secondary)
        )
    }

    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = !isDarkTheme
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}