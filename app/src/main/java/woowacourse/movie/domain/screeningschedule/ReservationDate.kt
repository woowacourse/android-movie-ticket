package woowacourse.movie.domain.screeningschedule

import woowacourse.movie.domain.movieinfo.RunningDate

class ReservationDate(private val runningDate: RunningDate) {

    fun getIntervalDays(): List<String> {
        val startDay = runningDate.startDate
        val endDay = runningDate.endDate

        return generateSequence(startDay) { it.plusDays(1) }
            .takeWhile { !it.isAfter(endDay) }
            .map { it.toString() }
            .toList()
    }
}
