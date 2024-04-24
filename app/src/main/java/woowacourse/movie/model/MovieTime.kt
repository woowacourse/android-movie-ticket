package woowacourse.movie.model

import java.time.LocalTime

class MovieTime(private val isWeekend: Boolean) {
    fun generateTimes(): List<LocalTime> {
        val times = mutableListOf<LocalTime>()
        var currentTime = if (isWeekend) WEEKDAY_BASE_LOCAL_TIME else WEEKEND_BASE_LOCAL_TIME
        val prohibitionTime = if (isWeekend) WEEKDAY_PROHIBITION_LOCAL_TIME else WEEKEND_PROHIBITION_LOCAL_TIME
        while (currentTime != prohibitionTime) {
            times.add(currentTime)
            currentTime = currentTime.plusHours(OFFSET_HOUR)
        }
        return times
    }

    companion object {
        private val WEEKDAY_BASE_LOCAL_TIME = LocalTime.of(9, 0)
        private val WEEKEND_BASE_LOCAL_TIME = LocalTime.of(10, 0)
        private val WEEKDAY_PROHIBITION_LOCAL_TIME = LocalTime.of(1, 0)
        private val WEEKEND_PROHIBITION_LOCAL_TIME = LocalTime.of(2, 0)
        private const val OFFSET_HOUR = 2L
    }
}
