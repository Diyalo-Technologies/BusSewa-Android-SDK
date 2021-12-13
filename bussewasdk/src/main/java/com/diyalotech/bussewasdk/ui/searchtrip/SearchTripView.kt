package com.diyalotech.bussewasdk.ui.searchtrip

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.SwapVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.utils.localDateNow
import kotlinx.datetime.LocalDate

@Composable
fun SearchTripView(
    state: SearchTripModel,
    onDateSelected: (LocalDate?) -> Unit,
    onSwap: () -> Unit,
) {

    var shouldRotate by remember { mutableStateOf(false) }
    val rotation: Float by animateFloatAsState(
        targetValue = if (shouldRotate) 180f else 0f,
        animationSpec = tween(400, 0),
        finishedListener = { shouldRotate = false }
    )

    Column {
        Box(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Column {
                LocationView(
                    state.source,
                    "Source",
                    Icons.Outlined.MyLocation
                ) {

                }
                Divider(Modifier.padding(vertical = 8.dp))
                LocationView(
                    state.destination,
                    "Destination",
                    Icons.Outlined.Place
                ) {

                }
            }

            Icon(
                Icons.Outlined.SwapVert,
                "",
                modifier = Modifier
                    .background(MaterialTheme.colors.primary, CircleShape)
                    .padding(4.dp)
                    .align(Alignment.CenterEnd)
                    .rotate(if (shouldRotate) rotation else 0f)
                    .clickable(!shouldRotate) {
                        onSwap()
                        shouldRotate = true
                    },
                tint = MaterialTheme.colors.onPrimary
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            DateSelectionView(
                label = "Departure Date",
                date = if (state.date == null) "yyyy-MM-dd" else "${state.date.year}-${state.date.monthNumber}-${state.date.dayOfMonth}",
                onDateSelected
            )
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(16.dp)
                .widthIn(320.dp, 480.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Search Bus")
        }
    }
}


@Preview
@Composable
fun DateChangePreview() {
    Surface {
        SearchTripView(
            SearchTripModel(
                "Ktm",
                "Pokhara",
                localDateNow()
            ), {}) {
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DateChangePreview2() {
    BusSewaSDKTheme {
        Surface {
            SearchTripView(
                SearchTripModel(
                    "Ktm",
                    "Pokhara",
                    localDateNow()
                ), {}) {
            }
        }
    }
}
