package woowacourse.movie.domain.screeningschedule

import java.time.LocalDate

class ReservationDate(private val startDay: LocalDate, private val endDay: LocalDate) {

    fun getIntervalDays(): List<String> {

        return generateSequence(startDay) { it.plusDays(1) }
            .takeWhile { !it.isAfter(endDay) }
            .map { it.toString() }
            .toList()
    }
}
