package woowacourse.movie.model

import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieTime(private val isWeekend: Boolean) {
    fun generateTimes(): List<String> {
        val times = mutableListOf<String>()
        var currentTime = if (isWeekend) WEEKEND_BASE_LOCAL_TIME else WEEKDAY_BASE_LOCAL_TIME
        repeat(TIMES_SIZE) {
            times.add(formatLocalTime(currentTime))
            currentTime = currentTime.plusHours(OFFSET_HOUR)
        }
        return times
    }

    private fun formatLocalTime(currentTime: LocalTime): String {
        val formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        val hour = formattedTime.substring(0, 2).toInt() + 1
        val minute = formattedTime.substring(3).toInt()
        return String.format("%02d:%02d", hour, minute)
    }

    companion object {
        private val WEEKDAY_BASE_LOCAL_TIME = LocalTime.of(8, 0)
        private val WEEKEND_BASE_LOCAL_TIME = LocalTime.of(9, 0)
        private const val TIMES_SIZE = 8
        private const val OFFSET_HOUR = 2L
    }
}
