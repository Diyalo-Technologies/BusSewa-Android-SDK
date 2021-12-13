package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.ui.theme.Shapes

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.onSurface.copy(0.1f), shape = Shapes.small)
            .clickable(onClick != null) {
                onClick?.invoke()
            }
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)


    ) {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusSewaSDKTheme {
        Surface {
            Chip(onClick = {}) {
                Text("test")
            }
        }
    }
}