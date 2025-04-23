package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTime(
    private val nowDateTime: LocalDateTime,
    private val selectedDate: LocalDate,
) : Serializable {
    fun getAvailableScreeningTimes(): List<LocalTime> {
        val times = getScreeningTimes(DayType.of(selectedDate))
        return if (isToday()) {
            times.filter { it.isAfter(nowDateTime.toLocalTime()) }
        } else {
            times
        }
    }

    fun hasAvailableScreeningTime(): Boolean {
        return getAvailableScreeningTimes().isNotEmpty()
    }

    private fun getScreeningTimes(dayType: DayType): List<LocalTime> {
        val startTime = dayType.startTime
        return (startTime.hour until MIDNIGHT step dayType.interval)
            .map {
                LocalTime.of(it, startTime.minute)
            }
    }

    private fun isToday(): Boolean = selectedDate.isEqual(nowDateTime.toLocalDate())

    companion object {
        private const val MIDNIGHT = 24
    }
}
