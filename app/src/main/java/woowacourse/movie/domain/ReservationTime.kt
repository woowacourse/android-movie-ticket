package woowacourse.movie.domain

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationTime(private val dayOfWeek: DayOfWeek) {

    fun getScreeningTimes(): List<String> {
        var startTime: LocalTime = when (dayOfWeek) {
            DayOfWeek.WEEKDAY -> LocalTime.of(9, 0)
            DayOfWeek.WEEKEND -> LocalTime.of(10, 0)
        }
        val list = mutableListOf<String>()
        while (startTime.hour in (9..23)) {
            list.add(startTime.format(DateTimeFormatter.ofPattern("HH:mm")))
            startTime = startTime.plusHours(2)
        }
        return list
    }
}
