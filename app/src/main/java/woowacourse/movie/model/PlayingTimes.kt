package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class PlayingTimes(val startDate: LocalDate, val endDate: LocalDate) : java.io.Serializable {
    private val _times: MutableMap<LocalDate, List<LocalTime>> = mutableMapOf()
    val times: Map<LocalDate, List<LocalTime>>
        get() = _times.toMap()

    init {
        var date = startDate
        while (!date.isEqual(endDate.plusDays(1))) {
            _times[date] = makeTimes(date)
            date = date.plusDays(1)
        }
    }

    private fun makeTimes(date: LocalDate) = buildList<LocalTime> {
        val startHour = if (isWeekends(date.dayOfWeek)) 9 else 10
        for (hour in startHour until 24 step 2) {
            add(LocalTime.of(hour, 0))
        }
    }

    private fun isWeekends(dayOfWeek: DayOfWeek): Boolean =
        dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
}
