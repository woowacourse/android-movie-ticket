package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTime : Serializable {
    fun getAvailableScreeningTimes(
        nowDateTime: LocalDateTime,
        selectedDate: LocalDate,
    ): List<LocalTime> {
        val times = getScreeningTimes(DayType.of(selectedDate))
        return if (isToday(nowDateTime, selectedDate)) {
            times.filter { it.isAfter(nowDateTime.toLocalTime()) }
        } else {
            times
        }
    }

    private fun getScreeningTimes(dayType: DayType): List<LocalTime> {
        val startTime = dayType.startTime
        return (startTime.hour until MIDNIGHT step dayType.interval)
            .map {
                LocalTime.of(it, startTime.minute)
            }
    }

    private fun isToday(
        nowDateTime: LocalDateTime,
        selectedDate: LocalDate,
    ): Boolean {
        return selectedDate.isEqual(nowDateTime.toLocalDate())
    }

    companion object {
        private const val MIDNIGHT = 24
    }
}
