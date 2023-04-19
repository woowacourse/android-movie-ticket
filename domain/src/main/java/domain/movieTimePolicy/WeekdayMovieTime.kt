package domain.movieTimePolicy

import java.time.LocalDate
import java.time.LocalTime

object WeekdayMovieTime : MovieTimePolicy {
    private val WEEKDAY = 1..5
    private val SCREEN_TIME = 10 until 24
    private const val SCREEN_TIME_INTERVAL = 2

    override fun generateTime(date: LocalDate): List<LocalTime>? {
        if (date.dayOfWeek.value in WEEKDAY) {
            return SCREEN_TIME.step(SCREEN_TIME_INTERVAL).map { LocalTime.of(it, 0) }
        }
        return null
    }
}
