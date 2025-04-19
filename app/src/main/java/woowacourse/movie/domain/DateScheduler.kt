package woowacourse.movie.domain

import java.time.LocalDate

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
