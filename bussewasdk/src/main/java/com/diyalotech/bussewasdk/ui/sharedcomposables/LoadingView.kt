package com.diyalotech.bussewasdk.ui.sharedcomposables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme


@Composable
internal fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    BusSewaSDKTheme {
        Surface {
            Chip(onClick = {}) {
                Text("test")
            }
        }
    }
}