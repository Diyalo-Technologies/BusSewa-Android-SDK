package com.diyalotech.bussewasdk.ui.seatlayout

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.network.SeatLayout
import com.diyalotech.bussewasdk.network.SeatLayoutDTO
import com.diyalotech.bussewasdk.ui.models.BookingStatus
import com.diyalotech.bussewasdk.ui.sharedcomposables.dpToSp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.ui.theme.Shapes
import com.google.gson.Gson

@Composable
fun SeatLayoutView(noOfColumn: Int, seatLayout: List<SeatLayout>, selectedSeats: List<String>) {

    val layoutChunked = seatLayout.chunked(noOfColumn)
    var selected by remember {
        mutableStateOf<List<String>>(listOf())
    }

    for (row in layoutChunked) {
        Row {
            for (seat in row) {
                Seat(
                    seat.bookingStatusE(),
                    selected.contains(seat.displayName),
                    seat.displayName
                ) {
                    println("clicked: $it, $selected")
                    selected = if (selected.contains(it)) {
                        (selected - it)
                    } else {
                        (selected + it)
                    }
                }
            }
        }
    }
}

@Composable
fun Seat(
    bookingStatus: BookingStatus,
    isSelected: Boolean,
    displayName: String,
    onClick: (String) -> Unit
) {
    val clickable = bookingStatus == BookingStatus.AVAILABLE && displayName != "na"


    Crossfade(
        targetState = isSelected,
    ) { selected ->

        val resId = if (!selected && bookingStatus == BookingStatus.AVAILABLE) {
            R.drawable.ic_stroke_seat
        } else {
            R.drawable.ic_fill_seat
        }

        var tint = Color.Transparent
        var textColor = Color.Black

        if (bookingStatus == BookingStatus.AVAILABLE) {
            if (selected) {
                tint = MaterialTheme.colors.primary
                textColor = MaterialTheme.colors.onPrimary
            } else {
                tint = MaterialTheme.colors.primary.copy(alpha = 0.6f)
                textColor = MaterialTheme.colors.onSurface.copy(alpha = 0.85f)
            }
        } else {
            tint = MaterialTheme.colors.primary.copy(alpha = 0.6f)
            textColor = MaterialTheme.colors.onPrimary
        }

        Box(
            modifier = Modifier
                .size(56.dp, 56.dp)
                .clip(shape = Shapes.small)
                .clickable(clickable) {
                    onClick(displayName)
                },
        ) {
            if (!displayName.equals("na", ignoreCase = true)) {
                Icon(
                    painter = painterResource(id = resId),
                    contentDescription = "Booked",
                    tint = tint
                )
                Text(
                    text = displayName,
                    color = textColor,
                    fontSize = dpToSp(dp = 13.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(0.55f)
                        .absoluteOffset(y = 10.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val parsedSeatLayout = Gson().fromJson(testString, SeatLayoutDTO::class.java)
    println(parsedSeatLayout)

    BusSewaSDKTheme(darkTheme = false) {

        Surface {

            Column {
                SeatLayoutView(
                    parsedSeatLayout.noOfColumn,
                    parsedSeatLayout.seatLayout,
                    listOf("B12")
                )
                /*Seat(BookingStatus.AVAILABLE, isSelected = true, displayName = "B11") {

                }*/
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {

    val parsedSeatLayout = Gson().fromJson(testString, SeatLayoutDTO::class.java)
    println(parsedSeatLayout)

    BusSewaSDKTheme(darkTheme = true) {

        Surface {

            Column {
                SeatLayoutView(parsedSeatLayout.noOfColumn, parsedSeatLayout.seatLayout, listOf())
                /*Seat(BookingStatus.AVAILABLE, isSelected = true, displayName = "B11") {

                }*/
            }
        }

    }
}

val testString =
    "{\"status\":\"success\",\"noOfColumn\":5,\"isLocked\":true,\"seatLayout\":[{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"B1\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"B2\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A1\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A2\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"B3\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"B4\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A3\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A4\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"B5\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"B6\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A5\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A6\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"B7\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"B8\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A7\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A8\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"B9\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"Yes\"},{\"displayName\":\"B10\",\"bookingStatus\":\"No\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A9\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A10\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"B11\",\"bookingStatus\":\"No\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"B12\",\"bookingStatus\":\"No\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A11\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A12\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"B13\",\"bookingStatus\":\"No\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"B14\",\"bookingStatus\":\"No\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A13\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A14\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"B15\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"B16\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A15\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"A16\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"na\",\"bookingStatus\":\"na\",\"bookedByCustomer\":\"na\"},{\"displayName\":\"B17\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"B18\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"17\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"18\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"19\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"20\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"},{\"displayName\":\"21\",\"bookingStatus\":\"Yes\",\"bookedByCustomer\":\"No\"}]}"