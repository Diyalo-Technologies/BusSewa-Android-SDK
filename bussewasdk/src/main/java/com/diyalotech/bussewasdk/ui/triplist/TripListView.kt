package com.diyalotech.bussewasdk.ui.triplist

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.diyalotech.bussewasdk.repo.SearchParamRepository
import com.diyalotech.bussewasdk.repo.TripRepository
import com.diyalotech.bussewasdk.ui.sharedcomposables.Chip
import com.diyalotech.bussewasdk.ui.sharedcomposables.LoadingView
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.ui.theme.Shapes
import com.google.accompanist.insets.statusBarsPadding
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TripListView(tripListViewModel: TripListViewModel) {
    val uiState = tripListViewModel.uiState.collectAsState().value
    Column(Modifier.statusBarsPadding()) {

        DateChangeView(
            tripListViewModel.searchTripState.getSearchTripModel().date,
        )

        Crossfade(targetState = uiState) { it ->
            when (it) {
                TripListState.Loading -> {
                    LoadingView()
                }
                is TripListState.Success -> {
                    LazyColumn(contentPadding = PaddingValues(top = 12.dp)) {
                        items(it.tripList, { trip -> trip.id }) {
                            TripView(trip = it)
                            Divider(modifier = Modifier.padding(bottom = 12.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TripView(trip: Trip) {
    Column {

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