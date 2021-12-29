package com.diyalotech.bussewasdk.ui.seatlayout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.sharedcomposables.ErrorMessage
import com.diyalotech.bussewasdk.ui.sharedcomposables.ErrorMessageDialog
import com.diyalotech.bussewasdk.ui.sharedcomposables.LoadingView
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar

@Composable
fun SelectSeatsView(
    viewModel: SelectSeatsViewModel,
    onBooked: () -> Unit,
    onBackPressed: () -> Unit = {}
) {

    val uiState = viewModel.uiState.collectAsState().value
    val bookingState = viewModel.bookingUiState.collectAsState().value
    val selectedSeats = viewModel.selectSeatList
    val totalPrice = viewModel.totalPrice.value
    val snackBarHostState = rememberScaffoldState()
    var alertDialogVisibility = remember { false }

    Box {
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
    }

    //side effects
    when (bookingState) {
        is BookingState.Error -> {
            alertDialogVisibility = true
            AnimatedVisibility(visible = alertDialogVisibility) {
                ErrorMessageDialog(text = bookingState.message) {
                    alertDialogVisibility = false
                }
            }
        }
        BookingState.Success -> {
            onBooked()
        }
    }

}