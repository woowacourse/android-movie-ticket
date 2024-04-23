package woowacourse.movie.model

class MovieTime(private val isWeekend: Boolean) {
    fun generateTimes(): List<String> {
        val times = mutableListOf<String>()
        var currentTime = if (isWeekend) WEEKEND_MIN_HOUR else WEEKDAY_MIN_HOUR
        while (currentTime <= MAX_HOUR) {
            val formatTime = String.format("%02d:00", currentTime)
            times.add(formatTime)
            currentTime += OFFSET_HOUR
        }
        return times
    }

    companion object {
        private const val WEEKDAY_MIN_HOUR = 9
        private const val WEEKEND_MIN_HOUR = 10
        private const val MAX_HOUR = 24
        private const val OFFSET_HOUR = 2
    }
}
