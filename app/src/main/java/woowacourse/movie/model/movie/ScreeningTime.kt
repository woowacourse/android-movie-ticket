package woowacourse.movie.model.movie

import java.time.LocalTime

object ScreeningTime {
    private const val WEEKDAY_START_TIME = 10
    private const val WEEKEND_START_TIME = 9
    private const val SCREENING_END_TIME = 24
    private const val INTERVAL = 2
    private const val DEFAULT_MINUTE = 0

    fun weekdayTimes(): List<LocalTime> = screeningTimes(WEEKDAY_START_TIME)

    fun weekendTimes(): List<LocalTime> = screeningTimes(WEEKEND_START_TIME)

    private fun screeningTimes(startingTime: Int): List<LocalTime> {
        return (startingTime until SCREENING_END_TIME step (INTERVAL)).map {
            LocalTime.of(
                it,
                DEFAULT_MINUTE,
            )
        }
    }
}
