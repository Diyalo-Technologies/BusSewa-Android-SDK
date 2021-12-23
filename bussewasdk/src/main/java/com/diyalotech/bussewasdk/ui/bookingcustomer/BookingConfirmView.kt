package com.diyalotech.bussewasdk.ui.bookingcustomer

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
fun BookingConfirmView(viewModel: BookingConfirmViewModel, onBackPressed: () -> Unit) {
    val boardingPoints = viewModel.boardingPoints.collectAsState()
    Column {

        CustomerDetailsView(
            viewModel.boardingPointState.value,
            viewModel.nameState.value,
            viewModel.emailState.value,
            viewModel.mobileState.value,
            boardingPoints.value.map { it.display },
            onBackPressed = onBackPressed
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BookingPreview() {
    BusSewaSDKTheme {
        Surface {

        }
    }
}
