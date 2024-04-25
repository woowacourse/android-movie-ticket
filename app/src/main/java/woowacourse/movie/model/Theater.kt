package woowacourse.movie.model

import woowacourse.movie.model.screenTime.Weekday
import woowacourse.movie.model.screenTime.Weekend
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class Theater(private val movie: Movie) {

    fun screenTimes(date: LocalDate): List<LocalTime> {
        return when (date.dayOfWeek) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> Weekend.screenTimes()
            else -> Weekday.screenTimes()
        }
    }

    fun screenDates(): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var startDate = movie.screenDate[START_DATE_INDEX]
        val endDate = movie.screenDate[END_DATE_INDEX]
        while (startDate <= endDate) {
            dates.add(startDate)
            startDate = startDate.plusDays(INTERVAL_DAY.toLong())
        }
        return dates
    }

    companion object {
        const val START_DATE_INDEX: Int = 0
        const val END_DATE_INDEX: Int = 1
        const val INTERVAL_DAY: Int = 1
    }
}
