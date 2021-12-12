package com.diyalotech.bussewasdk.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

fun localDateNow(): LocalDate {
    return Clock.System.todayAt(TimeZone.currentSystemDefault())
}