package com.diyalotech.bussewasdk.ui.seatlayout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.diyalotech.bussewasdk.ui.NavDirection
import com.diyalotech.bussewasdk.ui.sharedcomposables.ErrorMessage
import com.diyalotech.bussewasdk.ui.sharedcomposables.ErrorMessageDialog
import com.diyalotech.bussewasdk.ui.sharedcomposables.LoadingView
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar
import com.diyalotech.bussewasdk.ui.sharedmodels.BookingState
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun SelectSeatsView(
    viewModel: SelectSeatsViewModel,
    onNavToConfirmBooking: () -> Unit,
    onBackPressed: () -> Unit = {}
) {

    val uiState = viewModel.uiState.collectAsState().value
    val bookingState = viewModel.bookingUiState.collectAsState().value
    val selectedSeats = viewModel.selectSeatList
    val totalPrice = viewModel.totalPrice.value
    var alertDialogVisibility = remember { false }
    val alertMessage = remember { mutableStateOf("message") }

    LaunchedEffect(key1 = Unit) {
        viewModel.eventsFlow.collectLatest { value ->
            when (value) {
                is SelectSeatEvents.Error -> {
                    alertMessage.value = value.msg
                    alertDialogVisibility = true
                }
                is SelectSeatEvents.Navigation -> {
                    when (value.direction) {
                        NavDirection.FORWARD -> {
                            onNavToConfirmBooking()
                        }
                        NavDirection.BACKWARD -> TODO()
                    }
                }
            }
        }
    }

    Column(
        Modifier.fillMaxHeight()
    ) {
        TopAppBar(title = "Select Seats", showBack = true, backAction = onBackPressed)
        Crossfade(
            targetState = uiState,
            animationSpec = tween(250),
            modifier = Modifier.weight(1f)
        ) { state ->
            when (state) {
                SelectSeatState.Loading -> {
                    LoadingView()
                }
                is SelectSeatState.Success -> {

                    SeatLayoutView(
                        noOfColumn = state.selectSeatModel.noOfColumn,
                        seatLayout = state.selectSeatModel.seatLayout,
                        selectedSeats = selectedSeats
                    ) {
                        if (bookingState != BookingState.Loading)
                            viewModel.onSeatClicked(it)
                    }
                }
                is SelectSeatState.Error -> {
                    ErrorMessage(text = state.message)
                }
            }
        }

        SelectedSeatsBottomBar(
            bookingState = bookingState,
            selectedSeats = selectedSeats.map { it.displayName },
            totalPrice = totalPrice,
            onBookClicked = {
                viewModel.bookSeats()
            }
        )
    }

    AnimatedVisibility(visible = alertDialogVisibility) {
        ErrorMessageDialog(text = alertMessage.value) {
            alertDialogVisibility = false
        }
    }

}