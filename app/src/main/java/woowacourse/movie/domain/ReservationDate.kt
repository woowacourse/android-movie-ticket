package woowacourse.movie.domain

import java.time.format.DateTimeFormatter

class ReservationDate(private val runningDate: RunningDate) {

    fun getScreeningDays(): List<String> {
        val startDay = runningDate.startDate
        val endDay = runningDate.endDate
        var day = startDay

        val list = mutableListOf<String>()
        while (!day.isEqual(endDay.plusDays(1))) {
            list.add(day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            day = day.plusDays(1)
        }

        return list
    }
}
