package woowacourse.movie.domain.screeningschedule

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ReservationDate(private val startDay: LocalDate, private val endDay: LocalDate) {

    fun getIntervalDays(): List<String> {

        val days = ChronoUnit.DAYS.between(startDay, endDay.plusDays(1))
        return List(days.toInt()) { startDay.plusDays(it.toLong()).toString() }
    }
}
