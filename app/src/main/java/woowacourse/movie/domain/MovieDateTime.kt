package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalDateTime

class MovieDateTime(
    private val startDateTime: LocalDateTime,
    private val endDateTime: LocalDateTime,
) {
    fun betweenDates(targetDate: LocalDate = LocalDate.now()): List<LocalDate> =
        buildList {
            val startDate = startDateTime.toLocalDate()
            val endDate = endDateTime.toLocalDate()

            var standardDate = targetDate
            if (standardDate.isBefore(startDate)) standardDate = startDate
            if (standardDate.isAfter(endDate)) throw IllegalStateException("이미 상영이 종료된 영화입니다.")
            while (!standardDate.isAfter(endDate)) {
                add(standardDate)
                standardDate = standardDate.plusDays(1)
            }
        }
}
