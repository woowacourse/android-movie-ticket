package woowacourse.movie.domain

import java.time.LocalDate

object TimesGenerator {
    private val WEEKDAYS = (9..23 step 2).map(::Time)
    private val WEEKENDS = (10..24 step 2).map(::Time)

    fun getTimesByDate(date: LocalDate): List<Time> {
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
