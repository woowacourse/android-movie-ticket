package woowacourse.movie.domain.repository

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface DateRepository {
    fun getDatesBetween(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate>

    fun getDateTimes(date: LocalDate): List<LocalTime>
}
