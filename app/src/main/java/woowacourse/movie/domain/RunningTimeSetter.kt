package woowacourse.movie.domain

import java.time.LocalDate
import java.time.LocalTime

class RunningTimeSetter {
    fun getRunningTimes(date: LocalDate): List<LocalTime> {
        return when (date.dayOfWeek.value) {
            in weekDays -> weekendTimes.toList()
            else -> weekDayTimes.toList()
        }
    }

    companion object {
        private val weekDayTimes = mutableListOf<LocalTime>().apply {
            for (hour in 10 until 24 step 2) {
                add(LocalTime.of(hour, 0, 0))
            }
            add(LocalTime.of(0, 0, 0))
        }

        private val weekendTimes = mutableListOf<LocalTime>().apply {
            for (hour in 9 until 24 step 2) {
                add(LocalTime.of(hour, 0, 0))
            }
        }

        private val weekDays = listOf(6, 7)
    }
}
