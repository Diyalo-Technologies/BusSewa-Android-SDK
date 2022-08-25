package com.diyalotech.bussewasdk.ui.seatlayout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.ui.sharedmodels.BookingState
import com.diyalotech.bussewasdk.utils.toNPRString

@Composable
internal fun SelectedSeatsBottomBar(
    bookingState: BookingState,
    selectedSeats: List<String>,
    totalPrice: Double?,
    modifier: Modifier = Modifier,
    buttonLabel: String = stringResource(id = R.string.book_seats),
    onBookClicked: () -> Unit
) {

    val text = if (selectedSeats.isEmpty()) "" else
        selectedSeats.reduceIndexed { i, text, comb ->
            text + (if (i == 0) "" else ", ") + comb
        }

    Column(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(bottom = 16.dp)
            .navigationBarsPadding()
    ) {

        Divider(Modifier.padding(bottom = 16.dp))
        AnimatedVisibility(
            selectedSeats.isNotEmpty(),
            Modifier.fillMaxWidth(),
        ) {
            Row(Modifier.padding(start = 20.dp, end = 20.dp, bottom = 8.dp)) {
                Column(
                    Modifier
                        .weight(1f)
                        .padding(end = 16.dp)
                ) {
                    Text(
                        stringResource(id = R.string.selected_seats),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primary
                    )
                    Text(
                        text,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .animateContentSize()
                    )
                }

                totalPrice?.let {

                    Column {
                        Text(
                            stringResource(id = R.string.total_price),
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.primary
                        )
                        Text(totalPrice.toNPRString())
                    }
                }
            }
        }


        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .onGloballyPositioned {
                    println(it.size.height)
                },
            onClick = onBookClicked,
            contentPadding = PaddingValues(vertical = 0.dp),
            enabled = selectedSeats.isNotEmpty() && bookingState != BookingState.Loading,
        ) {
            if (bookingState == BookingState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(40.dp)
                )
            } else {
                Text(
                    text = buttonLabel,
                    Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectedSeatsBottomBarPreview() {
    SelectedSeatsBottomBar(
        BookingState.Init,
        listOf(
            "12", "14", "16"
        ),
        1000.0,
        buttonLabel = stringResource(id = R.string.book_seats)
    ) {

    }
}