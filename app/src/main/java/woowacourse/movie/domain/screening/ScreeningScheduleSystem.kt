package woowacourse.movie.domain.screening

import java.time.LocalDate
import java.time.LocalTime

interface ScreeningScheduleSystem {
    fun getSchedules(
        releaseDate: LocalDate,
        endDate: LocalDate,
    ): ScreeningSchedule

    fun getScreeningTimes(date: LocalDate): List<LocalTime>
}
