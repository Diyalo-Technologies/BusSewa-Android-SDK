package com.diyalotech.bussewasdk.ui.seatlayout

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
fun SelectSeatsView(viewModel: SelectSeatsViewModel, onBackPressed: () -> Unit = {}) {

    val uiState = viewModel.uiState.collectAsState().value
    val selectedSeats = viewModel.selectSeatList
    val totalPrice = viewModel.totalPrice.value

    //for padding purposes + includes insets
    var bottomBarPadding: Int by remember { mutableStateOf(0) }
    val visibility = remember {
        MutableTransitionState(false)
    }
    visibility.targetState = selectedSeats.isNotEmpty()

    Column {

        TopAppBar(title = "Select Seats", showBack = true, backAction = onBackPressed)
        Box(
            Modifier.fillMaxHeight()
        ) {
            Crossfade(targetState = uiState, animationSpec = tween(250)) { state ->
                when (state) {
                    SelectSeatState.Loading -> {
                        LoadingView()
                    }
                    is SelectSeatState.Success -> {

                        SeatLayoutView(
                            noOfColumn = state.selectSeatModel.noOfColumn,
                            seatLayout = state.selectSeatModel.seatLayout,
                            selectedSeats = selectedSeats,
                            paddingValues = PaddingValues(bottom = with(LocalDensity.current) { bottomBarPadding.toDp() })
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
                seatsVisibility = visibility,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onGloballyPositioned {
                        if(visibility.currentState == selectedSeats.isNotEmpty()) {
                            bottomBarPadding = it.size.height
                        }
                    }
            )
        }
    }
}