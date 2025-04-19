package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

interface TimeScheduler {
    fun reservableTimes(
        selectedDate: LocalDate,
        currentDate: LocalDateTime,
    ): List<LocalTime>
}
