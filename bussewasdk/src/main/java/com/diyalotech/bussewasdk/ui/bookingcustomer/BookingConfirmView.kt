package com.diyalotech.bussewasdk.ui.bookingcustomer

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.BookingDetailsState
import com.diyalotech.bussewasdk.ui.sharedcomposables.ErrorMessage
import com.diyalotech.bussewasdk.ui.sharedcomposables.LoadingView
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme

@Composable
fun BookingConfirmView(viewModel: BookingConfirmViewModel, onBackPressed: () -> Unit) {
    val boardingPoints = viewModel.boardingPoints.collectAsState().value
    val uiState = viewModel.uiState.collectAsState().value

    Column {
        TopAppBar(
            title = stringResource(id = R.string.booking_confirmation),
            showBack = true,
            backAction = onBackPressed
        )

        when (uiState) {
            is BookingDetailsState.Loading -> {
                LoadingView()
            }
            is BookingDetailsState.SuccessBasic -> {
                CustomerBasicDetailsView(
                    boardingPointModel = uiState.boardingPoint,
                    emailModel = uiState.emailState,
                    mobileModel = uiState.nameState,
                    boardingPoints = boardingPoints,
                ) { field, value ->

                }
            }
            is BookingDetailsState.SuccessDynamic -> {
                CustomerDynamicDetails(
                    mobileModel = uiState.mobileState,
                    emailModel = uiState.emailState,
                    boardingPointModel = uiState.boardingPoint,
                    seatList = uiState.seatList,
                    boardingPoints = boardingPoints,
                    details = uiState.passengerDetail,
                    valueHolder = uiState.valuesHolder,
                    onBasicDetailChanged = { field, value ->

                    },
                    onDynamicDetailsChanged = { seat, id, value ->

                    })
            }
            is BookingDetailsState.SuccessMulti -> {
                CustomerMultiPriceDetails(
                    mobileModel = uiState.mobileState,
                    emailModel = uiState.emailState,
                    boardingPointModel = uiState.boardingPoint,
                    boardingPoints = boardingPoints,
                    seatList = uiState.seatList,
                    priceList = uiState.priceList,
                    passengerPriceDetails = uiState.passengerPriceDetails,
                    onBasicDetailsChanged = { a, b -> },
                    onNameChanged = { field, value ->

                    },
                    onPriceSelected = { seat, multiPrice ->

                    })
            }
            is BookingDetailsState.SuccessMultiDynamic -> {

            }
            is BookingDetailsState.Error -> {
                ErrorMessage(text = stringResource(id = R.string.failed_to_fetch_config))
            }
        }
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
