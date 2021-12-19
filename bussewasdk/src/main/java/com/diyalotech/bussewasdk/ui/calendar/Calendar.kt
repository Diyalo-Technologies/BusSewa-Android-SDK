package com.diyalotech.bussewasdk.ui.calendar

import android.widget.CalendarView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.diyalotech.bussewasdk.utils.toInstant
import kotlinx.datetime.*

@Composable
fun Calendar(date: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = {
            CalendarView(it).apply {
                setOnDateChangeListener { _, year, month, dayOfMonth ->
                    onDateSelected(
                        LocalDate(
                            year = year,
                            month = Month(month + 1),
                            dayOfMonth = dayOfMonth
                        )
                    )
                }
            }
        }
    )
}