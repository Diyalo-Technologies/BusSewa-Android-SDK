package com.diyalotech.bussewasdk.utils

import kotlinx.datetime.*

fun localDateNow(): LocalDate {
    return Clock.System.todayAt(TimeZone.currentSystemDefault())
}

/*
* Do not use this if time needs to be accurate
* Only for dates
* */
fun LocalDate.toLocalDateTime(): LocalDateTime {
    return LocalDateTime(year, monthNumber - 1, dayOfMonth, 0, 0, 0)
}

/*
* Do not use this if time needs to be accurate
* Only for dates
* */
fun LocalDate.toInstant(): Instant {
    return toLocalDateTime()
        .toInstant(TimeZone.currentSystemDefault())
}