package woowacourse.movie.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS

class RunningDateSetter {
    fun getRunningDates(start: LocalDate, end: LocalDate): List<LocalDate> {
        val betweenDays = DAYS.between(start, end)
        val allDays = mutableListOf<LocalDate>()
        for (i in 0..betweenDays) {
            allDays.add(start.plusDays(i))
        }
        return allDays.toList()
    }
}
