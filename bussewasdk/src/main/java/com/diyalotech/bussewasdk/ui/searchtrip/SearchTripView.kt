package com.diyalotech.bussewasdk.ui.searchtrip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diyalotech.bussewasdk.ui.sharedcomposables.Chip
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.utils.localDateNow
import kotlinx.datetime.*

@Composable
fun SearchTripView(state: SearchTripModel, onDateSelected: (LocalDate?) -> Unit) {
    var source by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }

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
                    .align(Alignment.CenterEnd),
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
    }
}

@Composable
fun LocationView(
    text: String = "",
    label: String,
    leadingIcon: ImageVector? = null,
    onClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .clickable {
            onClick()
        }
        .padding(8.dp)
    ) {
        if (leadingIcon != null) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colors.primary.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(8.dp)

            ) {
                Icon(leadingIcon, "", tint = MaterialTheme.colors.primary)
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        ) {
            Text(
                text = label,
                color = MaterialTheme.colors.onSurface.copy(0.6f),
                fontSize = 12.sp
            )
            Text(
                text = text,
                modifier = Modifier,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
private fun DateSelectionView(
    label: String,
    date: String,
    onDateSelected: (date: LocalDate?) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .clickable {
            onDateSelected(null)
        }
        .padding(8.dp)
    ) {

        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colors.primary.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(8.dp)

        ) {
            Icon(Icons.Outlined.CalendarToday, "", tint = MaterialTheme.colors.primary)
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        ) {
            Text(label, fontSize = 12.sp)
            Text(
                if (date.isEmpty()) "yyyy-MM-dd" else date,
                color = if (date.isEmpty()) MaterialTheme.colors.onSurface.copy(0.5f) else MaterialTheme.colors.primary,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Chip(modifier = Modifier.align(Alignment.CenterVertically), {
            onDateSelected(localDateNow())
        }) {
            Text("Today", color = MaterialTheme.colors.onSurface.copy(0.55f))
        }
        Spacer(modifier = Modifier.width(8.dp))
        Chip(modifier = Modifier.align(Alignment.CenterVertically), {
            onDateSelected(localDateNow().plus(DatePeriod(days = 1)))
        }) {
            Text("Tomorrow", color = MaterialTheme.colors.onSurface.copy(0.55f))
        }
    }
}


@Preview
@Composable
fun DateChangePreview() {
    BusSewaSDKTheme(darkTheme = true) {
        Surface {
            SearchTripView(
                SearchTripModel(
                    "Ktm",
                    "Pokhara",
                    localDateNow()
                )
            ) {

            }
            /*LocationView(
                "Kathmandu",
                "Destination",
                Icons.Outlined.Place
            ) {

            }*/
        }
    }
}

@Preview
@Composable
fun DateChangePreview2() {
    BusSewaSDKTheme(darkTheme = false) {
        Surface {
            SearchTripView(
                SearchTripModel(
                    "Ktm",
                    "Pokhara",
                    localDateNow()
                )
            ) {

            }
        }
    }
}
