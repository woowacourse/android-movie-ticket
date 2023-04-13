package woowacourse.movie.domain

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationTime(private val dayOfWeek: DayOfWeek) {

    private fun getStartTime(): LocalTime = when (dayOfWeek) {
        DayOfWeek.WEEKDAY -> LocalTime.of(9, 0)
        DayOfWeek.WEEKEND -> LocalTime.of(10, 0)
    }

    fun getScreeningTimes(): List<String> {
        var startTime: LocalTime = getStartTime()
        val endTime = LocalTime.of(23, 0)

        return generateSequence(startTime) { it.plusMinutes(1) }
            .takeWhile { !it.isAfter(endTime) }
            .map { it.format(DateTimeFormatter.ofPattern("HH:mm")) }
            .toList()
    }
}
