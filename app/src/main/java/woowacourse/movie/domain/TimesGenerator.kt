package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

object TimesGenerator {
    private val WEEKDAYS = (9..23 step 2).map { LocalTime.of(it, 0) }
    private val WEEKENDS = (10..22 step 2).map { LocalTime.of(it, 0) }

    fun getTimesByDate(date: LocalDate): List<LocalTime> {
        return when (date.dayOfWeek.value) {
            1, 2, 3, 4, 5 -> {
                WEEKDAYS
            }
            6, 7 -> {
                WEEKENDS
            }
            else -> throw IllegalArgumentException()
        }
    }
}
