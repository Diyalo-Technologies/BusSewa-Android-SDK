package com.diyalotech.bussewasdk.ui.bookingcustomer

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.NavDirection
import com.diyalotech.bussewasdk.ui.bookingcustomer.models.BookingDetailsState
import com.diyalotech.bussewasdk.ui.seatlayout.SelectedSeatsBottomBar
import com.diyalotech.bussewasdk.ui.sharedcomposables.ErrorMessage
import com.diyalotech.bussewasdk.ui.sharedcomposables.ErrorMessageDialog
import com.diyalotech.bussewasdk.ui.sharedcomposables.LoadingView
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BookingConfirmView(viewModel: BookingConfirmViewModel, onBackPressed: () -> Unit) {

    val boardingPoints = viewModel.tripDataStore.bookingInfo.boardingPoints
    val selectedSeats = viewModel.tripDataStore.selectedSeats
    val totalPrice = viewModel.tripDataStore.ticketPrice
    val bookingState = viewModel.bookingUiState.collectAsState().value
    val uiState = viewModel.uiState.collectAsState().value
    var alertDialogVisibility = remember { false }
    val alertMessage = remember { mutableStateOf("message") }

    LaunchedEffect(key1 = Unit) {
        viewModel.eventsFlow.collectLatest { value ->
            when (value) {
                is BookingConfirmEvent.Error -> {
                    alertMessage.value = value.msg
                    alertDialogVisibility = true
                }
                is BookingConfirmEvent.Navigation -> {
                    when (value.direction) {
                        NavDirection.FORWARD -> {
                        }
                        NavDirection.BACKWARD -> {
                            onBackPressed()
                        }
                    }
                }
            }
        }
    }

    BackHandler {
        viewModel.cancelQueue()
    }

    Column {
        TopAppBar(
            title = stringResource(id = R.string.booking_confirmation),
            showBack = true,
            backAction = {
                viewModel.cancelQueue()
            }
        )

        Box(Modifier.weight(1f)) {
            when (uiState) {
                is BookingDetailsState.Loading -> {
                    LoadingView()
                }
                is BookingDetailsState.SuccessBasic -> {
                    CustomerBasicDetailsView(
                        boardingPointModel = uiState.boardingPoint,
                        nameModel = uiState.nameState,
                        emailModel = uiState.emailState,
                        mobileModel = uiState.mobileState,
                        boardingPoints = boardingPoints,
                    ) { field, value ->
                        viewModel.onBasicDetailsChanged(field, value)
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
                            viewModel.onBasicDetailsChanged(field, value)
                        },
                        onDynamicDetailsChanged = { seat, id, value ->
                            viewModel.onDynamicDetailsChanged(seat, id, value)
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
                        onBasicDetailsChanged = { field, value ->
                            viewModel.onBasicDetailsChanged(field, value)
                        },
                        onNameChanged = { seat, value ->
                            viewModel.onMultiPriceNameChanged(seat, value)
                        },
                        onPriceSelected = { seat, multiPrice ->
                            viewModel.onMultiPricePriceChanged(seat, multiPrice)
                        })
                }
                is BookingDetailsState.SuccessMultiDynamic -> {

                }
                is BookingDetailsState.Error -> {
                    ErrorMessage(text = stringResource(id = R.string.failed_to_fetch_config))
                }
            }
        }

        SelectedSeatsBottomBar(
            bookingState = bookingState,
            selectedSeats = selectedSeats,
            totalPrice = totalPrice,
            buttonLabel = stringResource(id = R.string.confirm_booking)
        ) {
            viewModel.confirmBooking()
        }
    }

    AnimatedVisibility(visible = alertDialogVisibility) {
        ErrorMessageDialog(text = alertMessage.value) {
            alertDialogVisibility = false
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BookingPreview() {
    BusSewaSDKTheme {
        Surface {
            BookingConfirmView(viewModel()) {

            }
        }
    }
}
