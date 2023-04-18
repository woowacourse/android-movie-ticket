package com.woowacourse.movie.domain

import java.time.LocalDate

object RunningDate {
    private infix fun LocalDate.max(other: LocalDate): LocalDate {
        if (this > other) return this
        return other
    }

    fun getRunningDates(
        startDate: LocalDate,
        endDate: LocalDate,
        today: LocalDate = LocalDate.now()
    ): List<LocalDate> {
        if (today > endDate) return emptyList()
        return generateSequence(today max startDate) { it.plusDays(1) }
            .takeWhile { it <= endDate }
            .map { it }
            .toList()
    }
}
