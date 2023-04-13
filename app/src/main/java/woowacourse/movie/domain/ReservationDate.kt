package woowacourse.movie.domain

class ReservationDate(private val runningDate: RunningDate) {

    fun getScreeningDays(): List<String> {
        val startDay = runningDate.startDate
        val endDay = runningDate.endDate

        return generateSequence(startDay) { it.plusDays(1) }
            .takeWhile { !it.isAfter(endDay) }
            .map { it.toString() }
            .toList()
    }
}
