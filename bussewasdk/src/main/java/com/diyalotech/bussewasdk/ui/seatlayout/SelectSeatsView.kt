package com.diyalotech.bussewasdk.ui.seatlayout

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.diyalotech.bussewasdk.ui.sharedcomposables.ErrorMessage
import com.diyalotech.bussewasdk.ui.sharedcomposables.LoadingView
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar

@Composable
fun SelectSeatsView(viewModel: SelectSeatsViewModel, onBookClicked: () -> Unit, onBackPressed: () -> Unit = {}) {

    val uiState = viewModel.uiState.collectAsState().value
    val selectedSeats = viewModel.selectSeatList
    val totalPrice = viewModel.totalPrice.value

    Column {

        TopAppBar(title = "Select Seats", showBack = true, backAction = onBackPressed)
        Column(
            Modifier.fillMaxHeight()
        ) {
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
                            viewModel.onSeatClicked(it)
                        }
                    }
                    is SelectSeatState.Error -> {
                        ErrorMessage(text = state.message)
                    }
                }
            }

            SelectedSeatsBottomBar(
                selectedSeats = selectedSeats.map { it.displayName },
                totalPrice = totalPrice,
                onBookClicked = onBookClicked
            )
        }
    }
}