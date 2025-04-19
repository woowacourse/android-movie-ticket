package woowacourse.movie.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

interface DateScheduler {
    fun reservableDates(
        screeningDate: ScreeningDate,
        currentDate: LocalDate,
    ): List<LocalDate>

    fun startDate(
        startDate: LocalDate,
        currentDate: LocalDate,
    ): LocalDate
}
