package woowacourse.movie.domain.date.scheduler

import woowacourse.movie.domain.model.movie.ScreeningDate
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
