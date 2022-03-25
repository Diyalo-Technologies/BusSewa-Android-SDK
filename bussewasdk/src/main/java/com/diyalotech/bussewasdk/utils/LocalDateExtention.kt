package com.diyalotech.bussewasdk.utils

import kotlinx.datetime.*

internal fun localDateNow(): LocalDate {
    return Clock.System.todayAt(TimeZone.currentSystemDefault())
}

/*
* Do not use this if time needs to be accurate
* Only for dates
* */
internal fun LocalDate.toLocalDateTime(): LocalDateTime {
    return LocalDateTime(year, month, dayOfMonth, 1, 1, 1)
}

/*
* Do not use this if time needs to be accurate
* Only for dates
* */
internal fun LocalDate.toInstant(): Instant {
    return toLocalDateTime()
        .toInstant(TimeZone.currentSystemDefault())
}

internal fun localTimeNow(): LocalDateTime {
    return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
}

internal fun LocalDateTime.toLocalInstant(): Instant {
    return this.toInstant(TimeZone.currentSystemDefault())
}