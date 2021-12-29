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
    return LocalDateTime(year, month, dayOfMonth, 1, 1, 1)
}

/*
* Do not use this if time needs to be accurate
* Only for dates
* */
fun LocalDate.toInstant(): Instant {
    return toLocalDateTime()
        .toInstant(TimeZone.currentSystemDefault())
}

fun localTimeNow(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

fun LocalDateTime.toLocalInstant(): Instant {
    return this.toInstant(TimeZone.currentSystemDefault())
}