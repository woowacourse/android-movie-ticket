package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalDateTime

class MovieDateTime(
    private val startDateTime: LocalDateTime,
    private val endDateTime: LocalDateTime,
) {
    fun betweenDates(targetDate: LocalDate): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        val startDate = startDateTime.toLocalDate()
        val endDate = endDateTime.toLocalDate()

        var standardDate = targetDate
        if (standardDate.isBefore(startDate)) standardDate = startDate
        if (standardDate.isAfter(endDate)) throw IllegalStateException("이미 상영이 종료된 영화입니다.")
        while (!targetDate.isAfter(endDate)) {
            dates.add(standardDate)
            standardDate.plusDays(1)
        }
        return dates.toList()
    }
}