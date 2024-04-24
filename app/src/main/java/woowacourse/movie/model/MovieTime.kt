package woowacourse.movie.model

import java.time.LocalTime

class MovieTime(private val isWeekend: Boolean) {
    fun generateTimes(): List<LocalTime> {
        val times = mutableListOf<LocalTime>()
        var currentTime = if (isWeekend) WEEKEND_BASE_LOCAL_TIME else WEEKDAY_BASE_LOCAL_TIME
        repeat(TIMES_SIZE) {
            times.add(currentTime)
            currentTime = currentTime.plusHours(OFFSET_HOUR)
        }
        return times
    }

    companion object {
        private val WEEKDAY_BASE_LOCAL_TIME = LocalTime.of(9, 0)
        private val WEEKEND_BASE_LOCAL_TIME = LocalTime.of(10, 0)
        private const val TIMES_SIZE = 8
        private const val OFFSET_HOUR = 2L
    }
}
