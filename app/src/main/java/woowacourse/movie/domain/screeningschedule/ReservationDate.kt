package woowacourse.movie.domain.screeningschedule

import java.time.LocalDate

class ReservationDate(private val startDate: LocalDate, private val endDate: LocalDate) {

    fun getIntervalDays(): List<String> {
        return generateSequence(startDate) { it.plusDays(1) }
            .takeWhile { !it.isAfter(endDate) }
            .map { it.toString() }
            .toList()
    }
}
