package woowacourse.movie.domain

import java.time.format.DateTimeFormatter

class ReservationDate(private val runningDate: RunningDate) {

    fun getScreeningDays(): List<String> {
        val startDay = runningDate.startDate
        val endDay = runningDate.endDate

        val dates = generateSequence(startDay) { it.plusDays(1) }
            .takeWhile { !it.isAfter(endDay) }
            .map { it.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) }
            .toList()

        return dates
    }
}
