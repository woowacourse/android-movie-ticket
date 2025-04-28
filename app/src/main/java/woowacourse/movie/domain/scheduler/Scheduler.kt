package woowacourse.movie.domain.scheduler

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface Scheduler {
    fun getScreeningDates(
        startDate: LocalDate,
        endDate: LocalDate,
        today: LocalDate,
    ): List<LocalDate>

    fun getShowtimes(
        selectedDate: LocalDate,
        now: LocalDateTime,
    ): List<LocalTime>
}
