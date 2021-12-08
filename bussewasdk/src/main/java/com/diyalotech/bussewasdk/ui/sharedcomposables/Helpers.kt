package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.ui.theme.Shapes

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun Chip(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.onSurface.copy(0.1f), shape = Shapes.small)
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)

    ) {
        Text(text, fontSize = 12.sp, color = MaterialTheme.colors.onSurface.copy(0.55f))
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusSewaSDKTheme(darkTheme = true) {
        Surface {
            Chip("hag")
        }
    }
}