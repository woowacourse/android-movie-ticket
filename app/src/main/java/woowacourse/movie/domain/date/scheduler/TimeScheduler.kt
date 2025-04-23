package woowacourse.movie.domain.date.scheduler

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface TimeScheduler {
    fun reservableTimes(
        selectedDate: LocalDate,
        currentDate: LocalDateTime,
    ): List<LocalTime>
}
