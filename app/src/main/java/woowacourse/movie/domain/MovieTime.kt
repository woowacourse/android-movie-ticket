package woowacourse.movie.domain

class MovieTime private constructor(val hour: Int, val min: Int = DEFAULT_MIN) {

    companion object {
        private const val WEEKEND_MIN_TIME = 9
        private const val WEEKEND_MAX_TIME = 24
        private const val WEEKEND_MOVIE_TIME_INTERVAL = 2

        private const val WEEKDAY_MIN_TIME = 10
        private const val WEEKDAY_MAX_TIME = 24
        private const val WEEKDAY_MOVIE_TIME_INTERVAL = 2

        private const val DEFAULT_HOUR = 0
        private const val DEFAULT_MIN = 0
        private const val HOUR_TO_MIN = 60

        private val weekendTimes: List<MovieTime> =
            (WEEKEND_MIN_TIME until WEEKEND_MAX_TIME step WEEKEND_MOVIE_TIME_INTERVAL).map {
                MovieTime(it)
            }
        private val weekdayTimes: List<MovieTime> =
            (WEEKDAY_MIN_TIME until WEEKDAY_MAX_TIME step WEEKDAY_MOVIE_TIME_INTERVAL).map {
                MovieTime(it)
            }

        @JvmOverloads
        fun of(
            isWeekday: Boolean,
            hour: Int = DEFAULT_HOUR,
            min: Int = DEFAULT_MIN,
        ): List<MovieTime> {
            if (isWeekday) return weekdayTimes.filter { it.hour * HOUR_TO_MIN + it.min > hour * HOUR_TO_MIN + min }
            return weekendTimes.filter { it.hour * HOUR_TO_MIN + it.min > hour * HOUR_TO_MIN + min }
        }
    }
}
