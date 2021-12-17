package com.diyalotech.bussewasdk.ui.triplist

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diyalotech.bussewasdk.ui.theme.BusSewaSDKTheme
import com.diyalotech.bussewasdk.utils.localDateNow
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import java.util.*

@ExperimentalAnimationApi
@Composable
fun DateChangeView(date: LocalDate) {
    var dateRem by remember { mutableStateOf(date) }
    val today = localDateNow()
    Card {
        Row(modifier = Modifier.height(48.dp)) {
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .fillMaxHeight()
                    .clickable(enabled = dateRem >= today) {
                        dateRem -= DatePeriod(days = 1)
                    }
            ) {
                if (dateRem >= today) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIos, contentDescription = "",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            AnimatedContent(
                targetState = dateRem,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f),
                transitionSpec = {
                    // Compare the incoming number with the previous number.
                    if (targetState > initialState) {
                        // If the target number is larger, it slides up and fades in
                        // while the initial (smaller) number slides up and fades out.
                        slideInHorizontally(initialOffsetX = { width -> width }) + fadeIn() with
                                fadeOut() + slideOutHorizontally(targetOffsetX = { width -> -width })
                    } else {
                        // If the target number is smaller, it slides down and fades in
                        // while the initial number slides down and fades out.
                        fadeIn() + slideInHorizontally(initialOffsetX = { width -> -width }) with
                                slideOutHorizontally(targetOffsetX = { width -> width }) + fadeOut()
                    }.using(
                        // Disable clipping since the faded slide-in/out should
                        // be displayed out of bounds.
                        SizeTransform(clip = true)
                    )
                }
            ) { targetDate ->
                Text(
                    text = "${targetDate.year}-${
                        targetDate.month.name.substring(0, 3)
                    }-${targetDate.dayOfMonth}",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary
                )
            }
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .fillMaxHeight()
                    .clickable {
                        dateRem += DatePeriod(days = 1)
                    }
            ) {
                Icon(
                    Icons.Rounded.ArrowForwardIos,
                    "",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun DateChangePreview() {
    BusSewaSDKTheme {
        Surface {
            val calendar = Calendar.getInstance()
            calendar.set(2021, 11, 9)
            DateChangeView(localDateNow())
        }
    }
}