package domain.movieTimePolicy

import java.time.LocalDate
import java.time.LocalTime

object WeekendMovieTime : MovieTimePolicy {
    private val WEEKEND = 6..7
    private val SCREEN_TIME = 9..24
    private const val SCREEN_TIME_INTERVAL = 2

    override fun generateTime(date: LocalDate): List<LocalTime>? {
        if (date.dayOfWeek.value in WEEKEND) {
            return SCREEN_TIME.step(SCREEN_TIME_INTERVAL).map { LocalTime.of(it, 0) }
        }
        return null
    }
}
