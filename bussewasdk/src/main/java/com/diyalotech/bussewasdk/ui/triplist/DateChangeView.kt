package com.diyalotech.bussewasdk.ui.triplist

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
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
internal fun DateChangeView(
    date: LocalDate,
    disable: Boolean,
    modifier: Modifier = Modifier,
    onDateChanged: (LocalDate) -> Unit = {}
) {
    var dateRem by remember { mutableStateOf(date) }
    val today = localDateNow()
    val iconTint = if(disable) {
        MaterialTheme.colors.surface.copy(0.5f)
    } else {
        MaterialTheme.colors.surface.copy(0.8f)
    }

    Box(modifier.padding(horizontal = 16.dp)) {

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.onSurface.copy(0.5f)
                .compositeOver(MaterialTheme.colors.surface)
        ) {
            Row(modifier = Modifier.height(48.dp)) {
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .fillMaxHeight()
                        .clickable(enabled = (dateRem > today && !disable)) {
                            dateRem -= DatePeriod(days = 1)
                            onDateChanged(dateRem)
                        }
                ) {
                    if (dateRem > today) {
                        Icon(
                            imageVector = Icons.Rounded.NavigateBefore, contentDescription = "",
                            modifier = Modifier.align(Alignment.Center),
                            tint = iconTint
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
                        color = MaterialTheme.colors.surface
                    )
                }
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .fillMaxHeight()
                        .clickable(!disable) {
                            dateRem += DatePeriod(days = 1)
                            onDateChanged(dateRem)
                        }
                ) {
                    Icon(
                        Icons.Rounded.NavigateNext,
                        "",
                        modifier = Modifier.align(Alignment.Center),
                        tint = iconTint
                    )
                }

            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun DateChangePreview() {
    BusSewaSDKTheme {
        Surface(modifier = Modifier.padding(vertical = 16.dp)) {
            DateChangeView(localDateNow(),true)
        }
    }
}