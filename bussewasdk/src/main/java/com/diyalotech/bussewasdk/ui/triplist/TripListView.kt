package com.diyalotech.bussewasdk.ui.triplist

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.bussewasdk.R
import com.diyalotech.bussewasdk.network.dto.singleTrip
import com.diyalotech.bussewasdk.ui.sharedcomposables.Chip
import com.diyalotech.bussewasdk.ui.sharedcomposables.ErrorMessage
import com.diyalotech.bussewasdk.ui.sharedcomposables.LoadingView
import com.diyalotech.bussewasdk.ui.sharedcomposables.TopAppBar
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.ui.theme.Shapes
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun TripListView(
    tripListViewModel: TripListViewModel,
    onBackPressed: () -> Unit,
    onTripClicked: (Trip) -> Unit
) {

    val uiState = tripListViewModel.uiState.collectAsState().value
    val tripDataStore = tripListViewModel.tripDataStore.getSearchTripModel()
    val insets = LocalWindowInsets.current

    Column(
        Modifier
            .navigationBarsPadding()
            .padding(bottom = 16.dp)
    ) {
        TopAppBar(
            title = "Trips: ${tripDataStore.source} - ${tripDataStore.destination}",
            showBack = true,
            backAction = onBackPressed
        )

        Crossfade(
            targetState = uiState,
            animationSpec = tween(250),
            modifier = Modifier.weight(1f)
        ) { it ->
            when (it) {
                TripListState.Loading -> {
                    LoadingView()
                }
                is TripListState.Success -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(
                            top = 16.dp,
                            bottom = insets.navigationBars.bottom.dp + 48.dp
                        )
                    ) {
                        items(it.tripList, { trip -> trip.id }) {
                            TripView(trip = it, onTripClicked)
                            Divider(modifier = Modifier.padding(bottom = 12.dp))
                        }
                    }
                }
                is TripListState.Error -> {
                    ErrorMessage(it.message)
                }
            }
        }

        DateChangeView(
            tripDataStore.date,
            uiState == TripListState.Loading,
            Modifier
                .widthIn(max = 480.dp)
        ) {
            tripListViewModel.onDateChanged(it)
        }
    }

}

@Composable
internal fun TripView(trip: Trip, onTripClicked: (Trip) -> Unit) {
    Column(Modifier.clickable(!trip.locked) {
        onTripClicked(trip)
    }) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Bus",
                modifier = Modifier.padding(end = 8.dp)
            )
            Column(
                modifier = Modifier.weight(1f, fill = true)
            ) {
                Row {
                    Text(
                        text = trip.operatorName,
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f)
                    )
                    Text(
                        text = trip.departureTime, style = MaterialTheme.typography.overline,
                        textAlign = TextAlign.End
                    )
                }
                Text(
                    text = trip.busType,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(0.55f)
                )

                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        text = "NPR ${trip.ticketPrice}",
                        color = MaterialTheme.colors.onSurface.copy(0.55f)
                    )

                    Text(
                        text = buildAnnotatedString {
                            append("${trip.availableSeat}")
                            withStyle(SpanStyle(MaterialTheme.colors.onSurface.copy(0.55f))) {
                                append(" seats available")
                            }
                        },
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }

                LinearProgressIndicator(
                    progress = trip.availablePercent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(Shapes.small)
                )

            }
        }

        LazyRow(
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
        ) {
            items(trip.amenities, { it }) {
                Chip(Modifier.padding(end = 4.dp), content = {
                    Text(
                        it.trim(),
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(0.55f)
                    )
                })
            }
        }
    }
}

@Preview
@Composable
fun TripViewPreview() {
    BusSewaSDKTheme {
        Surface {
            TripView(trip = singleTrip()) { }
        }
    }
}